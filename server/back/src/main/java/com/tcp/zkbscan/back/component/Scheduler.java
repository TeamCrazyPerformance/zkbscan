package com.tcp.zkbscan.back.component;

import com.tcp.zkbscan.back.dto.zkbnb.BlockResponse;
import com.tcp.zkbscan.back.dto.zkbnb.CurrentHeightResponse;
import com.tcp.zkbscan.back.entity.L1Block;
import com.tcp.zkbscan.back.entity.L2Block;
import com.tcp.zkbscan.back.service.L1BlockService;
import com.tcp.zkbscan.back.service.L2BlockService;
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
    private final L2BlockService l2BlockService;
    private final RestTemplate restTemplate;

    @Value("${bsc.l1.contract.block}")
    private BigInteger contractDeployedBlockNumber;

    @Value("${bsc.l2.rpc.url}")
    private String bscL2RpcUrl;

    @Scheduled(fixedDelay = 1000)
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
        if(l1DBLatestBlock != null)
            startBlockNumber = startBlockNumber.max(l1DBLatestBlock.getNumber().add(BigInteger.ONE));
        BigInteger endBlockNumber = l1NodeLatestBlock.getNumber();

        if(startBlockNumber.compareTo(endBlockNumber) < 0) {
            log.info("[L1] Block Replaying From : {}, To : {}", startBlockNumber, endBlockNumber);

            BigInteger targetBlockNumber = startBlockNumber;
            while(targetBlockNumber.compareTo(endBlockNumber) != 0) {
                try {
                    EthBlock.Block newBlock = bscL1Rpc.ethGetBlockByNumber(DefaultBlockParameter.valueOf(targetBlockNumber), false)
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

                    targetBlockNumber = targetBlockNumber.add(BigInteger.ONE);
                } catch (IOException e) {
                    log.warn("[L1] Cannot fetch block : {}", targetBlockNumber);
                }
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void fetchL2NewBlock() {
        L2Block l2DBLatestBlock = l2BlockService.getLatestBlock();

        BigInteger startBlockHeight = BigInteger.ZERO;
        if(l2DBLatestBlock != null)
            startBlockHeight = startBlockHeight.max(l2DBLatestBlock.getHeight().add(BigInteger.ONE));

        ResponseEntity<CurrentHeightResponse> currentHeightResponse = restTemplate
                .exchange(String.format("%s/api/v1/currentHeight", bscL2RpcUrl),
                        HttpMethod.GET,
                        null,
                        CurrentHeightResponse.class);

        BigInteger endBlockHeight = currentHeightResponse.getBody().getHeight();

        if(startBlockHeight.compareTo(endBlockHeight) < 0) {
            log.info("[L2] Block Replaying From : {}, To : {}", startBlockHeight, endBlockHeight);

            BigInteger targetBlockNumber = startBlockHeight;
            while(targetBlockNumber.compareTo(endBlockHeight) != 0) {
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

                targetBlockNumber = targetBlockNumber.add(BigInteger.ONE);
            }
        }
    }
}
