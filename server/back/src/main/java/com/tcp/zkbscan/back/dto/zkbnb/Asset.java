package com.tcp.zkbscan.back.dto.zkbnb;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class Asset {

    private BigInteger id;
    private String name;
    private BigInteger balance;
    private BigDecimal price;
}
