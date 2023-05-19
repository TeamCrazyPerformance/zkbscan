package com.tcp.zkbscan.back.component;

import com.tcp.zkbscan.back.entity.L1Block;
import com.tcp.zkbscan.back.service.L1BlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    @Value("${bsc.l1.contract.block}")
    private BigInteger contractDeployedBlockNumber;

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
}
