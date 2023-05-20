package com.tcp.zkbscan.back.dto.nft;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class NftMintDTO {

    private String txid;
    private BigInteger timestamp;
    private String maker;
    private String nftImageUrl;
    private String nftName;
    private BigInteger nftIndex;
}
