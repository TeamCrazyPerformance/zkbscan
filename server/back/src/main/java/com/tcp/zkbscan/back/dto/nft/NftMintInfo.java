package com.tcp.zkbscan.back.dto.nft;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NftMintInfo {

    private Integer creatorAccountIndex;
    private Integer toAccountIndex;
    private String toL1Address;
    private Integer nftIndex;
    private String nftContentHash;
    private Integer nftContentType;
    private Integer nftCollectionId;
    private Integer royaltyRate;
    private Integer gasAccountIndex;
    private Integer gasFeeAssetId;
    private Long gasFeeAssetAmount;
    private Long expiredAt;
    private Integer nonce;
    private String sig;
    @JsonProperty("MetaData")
    private String metaData;
    private String mutableAttributes;
    private String ipnsName;
    private String ipnsId;
    private String l1Sig;

}
