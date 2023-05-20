package com.tcp.zkbscan.back.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class L1TransactionLogPK implements Serializable {
    private L1Transaction transaction;
    private BigInteger logIndex;
    private String topic;
}
