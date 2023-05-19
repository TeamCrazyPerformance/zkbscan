package com.tcp.zkbscan.back.dto.transaction;

import lombok.*;

import java.math.BigInteger;

@Data
public class L1TransactionDTO {

    private String hash;
    private String blockHash;
    private BigInteger blockNumber;
    private String from;
    private BigInteger gas;
    private BigInteger gasPrice;
    private String input;
    private BigInteger nonce;
    private String to;
    private BigInteger transactionIndex;
    private BigInteger value;
    private String type;
    private Long v;
    private String r;
    private String s;

}
