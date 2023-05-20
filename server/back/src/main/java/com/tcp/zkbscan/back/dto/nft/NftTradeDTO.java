package com.tcp.zkbscan.back.dto.nft;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class NftTradeDTO {

    private String txid;
    private BigInteger timestamp;
    private String side;
    private BigDecimal price;
    private BigInteger nftIndex;
}
