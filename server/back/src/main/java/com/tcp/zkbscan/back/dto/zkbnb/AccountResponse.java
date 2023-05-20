package com.tcp.zkbscan.back.dto.zkbnb;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class AccountResponse {

    private BigInteger code;
    private String message;
    private BigInteger status;
    private BigInteger index;
    private String l1_address;
    private String pk;
    private BigInteger nonce;
    private List<Asset> assets;
    private BigDecimal total_asset_value;
}
