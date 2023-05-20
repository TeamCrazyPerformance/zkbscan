package com.tcp.zkbscan.back.dto.transaction;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DepositTransactionDTO {

    private String l1Txid;
    private String l2Txid;
    private BigInteger l1BlockNumber;
    private BigInteger l2BlockHeight;
    private String l1Address;
    private String l2Address;
    private BigInteger l1Timestamp;
    private BigInteger l2CreatedAt;
    private BigInteger l2VerifyAt;
}
