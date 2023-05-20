package com.tcp.zkbscan.back.dto.nft;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class NftTransferDTO {

    private String txid;
    private String method;
    private BigInteger timestamp;
    private String from;
    private String to;
    private BigInteger nftIndex;
}
