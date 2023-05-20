package com.tcp.zkbscan.back.dto.account;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class AccountDTO {

    private String address;
    private BigInteger accountIndex;
    private String pk;
    private List<AccountAssetDTO> assets;
    private BigDecimal totalAssetValue;

}
