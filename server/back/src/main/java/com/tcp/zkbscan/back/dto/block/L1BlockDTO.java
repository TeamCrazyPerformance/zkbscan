package com.tcp.zkbscan.back.dto.block;

import com.tcp.zkbscan.back.dto.transaction.L1TransactionDTO;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

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

}
