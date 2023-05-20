package com.tcp.zkbscan.back.component;

import com.tcp.zkbscan.back.dto.zkbnb.BlockResponse;
import com.tcp.zkbscan.back.dto.zkbnb.CurrentHeightResponse;
import com.tcp.zkbscan.back.dto.zkbnb.Transaction;
import com.tcp.zkbscan.back.entity.L1Block;
import com.tcp.zkbscan.back.entity.L1Transaction;
import com.tcp.zkbscan.back.entity.L2Block;
import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.service.L1BlockService;
import com.tcp.zkbscan.back.service.L1TransactionService;
import com.tcp.zkbscan.back.service.L2BlockService;
import com.tcp.zkbscan.back.service.L2TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final Web3j bscL1Rpc;
    private final L1BlockService l1BlockService;
    private final L1TransactionService l1TransactionService;
    private final L2BlockService l2BlockService;
    private final L2TransactionService l2TransactionService;
    private final RestTemplate restTemplate;

    @Value("${bsc.l1.contract.block}")
    private BigInteger contractDeployedBlockNumber;

    @Value("${bsc.l1.contract.address}")
    private String l1ContractAddress;

    @Value("${bsc.l2.rpc.url}")
    private String bscL2RpcUrl;

    //@Scheduled(fixedDelay = 1000)
    public void fetchL1NewBlock() {
        EthBlock.Block l1NodeLatestBlock;
        L1Block l1DBLatestBlock;
        try {
            l1NodeLatestBlock = bscL1Rpc.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
                    .send()
                    .getBlock();
            l1DBLatestBlock = l1BlockService.getLatestBlock();
        } catch (Exception e) {
            log.warn("[L1] Cannot fetch new block");
            return;
        }

        BigInteger startBlockNumber = contractDeployedBlockNumber;
        if (l1DBLatestBlock != null)
            startBlockNumber = startBlockNumber.max(l1DBLatestBlock.getNumber().add(BigInteger.ONE));
        BigInteger endBlockNumber = l1NodeLatestBlock.getNumber();

        if (startBlockNumber.compareTo(endBlockNumber) <= 0) {
            log.info("[L1] Block Replaying From : {}, To : {}", startBlockNumber, endBlockNumber);

            BigInteger targetBlockNumber = startBlockNumber;
            while (targetBlockNumber.compareTo(endBlockNumber.add(BigInteger.ONE)) != 0) {
                try {
                    EthBlock.Block newBlock = bscL1Rpc.ethGetBlockByNumber(DefaultBlockParameter.valueOf(targetBlockNumber), true)
                            .send()
                            .getBlock();

                    L1Block l1Block = L1Block.builder()
                            .number(newBlock.getNumber())
                            .difficulty(newBlock.getDifficulty())
                            .extraData(newBlock.getExtraData())
                            .gasLimit(newBlock.getGasLimit())
                            .gasUsed(newBlock.getGasUsed())
                            .hash(newBlock.getHash())
                            .logsBloom(newBlock.getLogsBloom())
                            .miner(newBlock.getMiner())
                            .mixHash(newBlock.getMixHash())
                            .nonce(newBlock.getNonce())
                            .parentHash(newBlock.getParentHash())
                            .receiptsRoot(newBlock.getReceiptsRoot())
                            .sha3Uncles(newBlock.getSha3Uncles())
                            .size(newBlock.getSize())
                            .stateRoot(newBlock.getStateRoot())
                            .timestamp(newBlock.getTimestamp())
                            .totalDifficulty(newBlock.getTotalDifficulty())
                            .transactionsRoot(newBlock.getTransactionsRoot())
                            .build();

                    l1BlockService.saveBlock(l1Block);
                    log.info("[L1] Block Saved : {}", targetBlockNumber);

                    // Transaction
                    if(newBlock.getTransactions() != null) {
                        for (EthBlock.TransactionResult txr : newBlock.getTransactions()) {
                            EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txr.get();

                            if (!l1ContractAddress.equals(tx.getTo()))
                                continue;

                            L1Transaction l1Transaction = L1Transaction.builder()
                                    .blockHash(tx.getBlockHash())
                                    .block(l1Block)
                                    .from(tx.getFrom())
                                    .gas(tx.getGas())
                                    .gasPrice(tx.getGasPrice())
                                    .hash(tx.getHash())
                                    .input(tx.getInput())
                                    .nonce(tx.getNonce())
                                    .to(tx.getTo())
                                    .transactionIndex(tx.getTransactionIndex())
                                    .value(tx.getValue())
                                    .type(tx.getType())
                                    .v(tx.getV())
                                    .r(tx.getR())
                                    .s(tx.getS())
                                    .build();

                            l1TransactionService.saveTransaction(l1Transaction);
                            log.info("[L1] Transaction Saved : {}", l1Transaction.getHash());
                        }
                    }

                    targetBlockNumber = targetBlockNumber.add(BigInteger.ONE);
                } catch (IOException e) {
                    log.warn("[L1] Cannot fetch block : {}", targetBlockNumber);
                }
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void fetchL2NewBlock() {
        L2Block l2DBLatestBlock = l2BlockService.getLatestBlockVerifiedAndExecuted();

        BigInteger startBlockHeight = BigInteger.ZERO;
        if (l2DBLatestBlock != null)
            startBlockHeight = startBlockHeight.max(l2DBLatestBlock.getHeight().add(BigInteger.ONE));

        ResponseEntity<CurrentHeightResponse> currentHeightResponse = restTemplate
                .exchange(String.format("%s/api/v1/currentHeight", bscL2RpcUrl),
                        HttpMethod.GET,
                        null,
                        CurrentHeightResponse.class);

        BigInteger endBlockHeight = currentHeightResponse.getBody().getHeight();

        if (startBlockHeight.compareTo(endBlockHeight) <= 0) {
            log.info("[L2] Block Replaying From : {}, To : {}", startBlockHeight, endBlockHeight);

            BigInteger targetBlockNumber = startBlockHeight;
            while (targetBlockNumber.compareTo(endBlockHeight.add(BigInteger.ONE)) != 0) {
                ResponseEntity<BlockResponse> blockResponse = restTemplate
                        .exchange(String.format("%s/api/v1/block?by=height&value=%d", bscL2RpcUrl, targetBlockNumber),
                                HttpMethod.GET,
                                null,
                                BlockResponse.class);

                BlockResponse targetBlock = blockResponse.getBody();

                L2Block l2Block = L2Block.builder()
                        .height(targetBlock.getHeight())
                        .commitment(targetBlock.getCommitment())
                        .stateRoot(targetBlock.getState_root())
                        .priorityOperations(targetBlock.getPriority_operations())
                        .pendingOnChainOperationsHash(targetBlock.getPending_on_chain_operations_hash())
                        .pendingOnChainOperationsPubData(targetBlock.getPending_on_chain_operations_pub_data())
                        .committedTxHash(targetBlock.getCommitted_tx_hash())
                        .committedAt(targetBlock.getCommitted_at())
                        .verifiedTxHash(targetBlock.getVerified_tx_hash())
                        .verifiedAt(targetBlock.getVerified_at())
                        .status(targetBlock.getStatus())
                        .build();

                l2BlockService.saveBlock(l2Block);
                log.info("[L2] Block Saved : {}", targetBlock.getHeight());

                // Transaction
                if(targetBlock.getTxs() != null) {
                    for (Transaction tx : targetBlock.getTxs()) {
                        L2Transaction l2Transaction = L2Transaction.builder()
                                .hash(tx.getHash())
                                .type(tx.getType())
                                .amount(tx.getAmount())
                                .info(tx.getInfo())
                                .status(tx.getStatus())
                                .index(tx.getIndex())
                                .gasFeeAssetId(tx.getGas_fee_asset_id())
                                .gasFee(tx.getGas_fee())
                                .nftIndex(tx.getNft_index())
                                .collectionId(tx.getCollection_id())
                                .assetId(tx.getAsset_id())
                                .assetName(tx.getAsset_name())
                                .nativeAddress(tx.getNative_address())
                                .extraInfo(tx.getExtra_info())
                                .memo(tx.getMemo())
                                .accountIndex(tx.getAccount_index())
                                .l1Address(tx.getL1_address())
                                .nonce(tx.getNonce())
                                .expireAt(tx.getExpire_at())
                                .block(l2Block)
                                .createdAt(tx.getCreated_at())
                                .verifyAt(tx.getVerify_at())
                                .stateRoot(tx.getState_root())
                                .toAccountIndex(tx.getTo_account_index())
                                .toL1Address(tx.getTo_l1_address())
                                .build();

                        l2TransactionService.saveTransaction(l2Transaction);
                        log.info("[L2] Transaction Saved : {}", l2Transaction.getHash());
                    }
                }

                targetBlockNumber = targetBlockNumber.add(BigInteger.ONE);
            }
        }
    }
}
