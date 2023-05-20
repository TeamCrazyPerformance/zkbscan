package com.tcp.zkbscan.back.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
public class AccountAssetDTO {

    private BigInteger assetId;
    private String symbol;
    private BigDecimal amount;
    private BigDecimal value;
}
