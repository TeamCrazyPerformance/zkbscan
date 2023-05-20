package com.tcp.zkbscan.back.dto.statistics;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class BasicStatistics {

    private BigDecimal bnbPrice;
    private BigInteger latestL1BlockNumber;
    private BigInteger latestl2BlockNumber;
}
