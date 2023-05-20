package com.tcp.zkbscan.back.dto.token;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class TokenTransferDTO {

    private String txid;
    private String method;
    private BigInteger timestamp;
    private String from;
    private String to;
    private BigDecimal amount;
    private String tokenSymbol;
    private BigInteger assetId;
}
