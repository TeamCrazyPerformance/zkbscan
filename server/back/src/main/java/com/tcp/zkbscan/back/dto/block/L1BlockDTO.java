package com.tcp.zkbscan.back.dto.block;

import com.tcp.zkbscan.back.dto.transaction.L1TransactionDTO;
import com.tcp.zkbscan.back.entity.L1Block;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class L1BlockDTO {

    private BigInteger number;
    private BigInteger difficulty;
    private String extraData;
    private BigInteger gasLimit;
    private BigInteger gasUsed;
    private String hash;
    private String logsBloom;
    private String miner;
    private String mixHash;
    private BigInteger nonce;
    private String parentHash;
    private String receiptsRoot;
    private String sha3Uncles;
    private BigInteger size;
    private String stateRoot;
    private BigInteger timestamp;
    private BigInteger totalDifficulty;
    private List<L1TransactionDTO> transactions;
    private String transactionsRoot;

    public L1BlockDTO(L1Block l1Block) {
        this.setNumber(l1Block.getNumber());
        this.setDifficulty(l1Block.getDifficulty());
        this.setExtraData(l1Block.getExtraData());
        this.setGasLimit(l1Block.getGasLimit());
        this.setGasUsed(l1Block.getGasUsed());
        this.setHash(l1Block.getHash());
        this.setLogsBloom(l1Block.getLogsBloom());
        this.setMiner(l1Block.getMiner());
        this.setMixHash(l1Block.getMixHash());
        this.setNonce(l1Block.getNonce());
        this.setParentHash(l1Block.getParentHash());
        this.setReceiptsRoot(l1Block.getReceiptsRoot());
        this.setSha3Uncles(l1Block.getSha3Uncles());
        this.setSize(l1Block.getSize());
        this.setStateRoot(l1Block.getStateRoot());
        this.setTimestamp(l1Block.getTimestamp());
        this.setTotalDifficulty(l1Block.getTotalDifficulty());
        this.setTransactions(l1Block.getTransactions().stream().map(L1TransactionDTO::new).collect(Collectors.toList()));
        this.setTransactionsRoot(l1Block.getTransactionsRoot());
    }
}
