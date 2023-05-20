package com.tcp.zkbscan.back.dto.nft;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

@Data
public class NftTradeInfo {

    @JsonProperty("AccountIndex")
    private Integer accountIndex;

    @Data
    public static class Offer {
        @JsonProperty("Type")
        private Integer type;

        @JsonProperty("OfferId")
        private Integer offerId;

        @JsonProperty("AccountIndex")
        private Integer accountIndex;

        @JsonProperty("NftIndex")
        private Integer nftIndex;

        @JsonProperty("AssetId")
        private Integer assetId;

        @JsonProperty("AssetAmount")
        private Long assetAmount;

        @JsonProperty("ListedAt")
        private Long listedAt;

        @JsonProperty("ExpiredAt")
        private Long expiredAt;

        @JsonProperty("RoyaltyRate")
        private Integer royaltyRate;

        @JsonProperty("ChannelAccountIndex")
        private Integer channelAccountIndex;

        @JsonProperty("ChannelRate")
        private Integer channelRate;

        @JsonProperty("ProtocolRate")
        private Integer protocolRate;

        @JsonProperty("ProtocolAmount")
        private Long protocolAmount;

        @JsonProperty("Sig")
        private String sig;

        @JsonProperty("L1Sig")
        private String l1Sig;
    }

    @JsonProperty("BuyOffer")
    private Offer buyOffer;

    @JsonProperty("SellOffer")
    private Offer sellOffer;

    @JsonProperty("GasAccountIndex")
    private Integer gasAccountIndex;

    @JsonProperty("GasFeeAssetId")
    private Integer gasFeeAssetId;

    @JsonProperty("GasFeeAssetAmount")
    private Long gasFeeAssetAmount;

    @JsonProperty("RoyaltyAmount")
    private Long royaltyAmount;

    @JsonProperty("BuyChannelAmount")
    private BigInteger buyChannelAmount;

    @JsonProperty("SellChannelAmount")
    private BigInteger sellChannelAmount;

    @JsonProperty("Nonce")
    private Integer nonce;

    @JsonProperty("ExpiredAt")
    private Long expiredAt;

    @JsonProperty("Sig")
    private String sig;
}
