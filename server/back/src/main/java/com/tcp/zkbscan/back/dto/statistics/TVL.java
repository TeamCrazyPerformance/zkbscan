package com.tcp.zkbscan.back.dto.statistics;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TVL {

    private String symbol;
    private BigDecimal amount;
    private BigDecimal value;
}
