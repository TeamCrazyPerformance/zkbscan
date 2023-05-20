package com.tcp.zkbscan.back.dto.binance;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BinanceTicker {

    private String symbol;
    private BigDecimal price;
}
