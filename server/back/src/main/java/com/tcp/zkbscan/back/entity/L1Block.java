package com.tcp.zkbscan.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class L1Block {

    @Id
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

    // List transactions;

    private String transactionsRoot;

    // List uncles;
}
