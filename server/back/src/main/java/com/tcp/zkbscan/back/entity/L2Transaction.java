package com.tcp.zkbscan.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class L2Transaction {

    @Id
    private String hash;
    private BigInteger type;
    private String amount;

    @Column(columnDefinition = "TEXT")
    private String info;
    private BigInteger status;

    @Column(name="\"index\"")
    private BigInteger index;
    private BigInteger gasFeeAssetId;
    private String gasFee;
    private BigInteger nftIndex;
    private BigInteger collectionId;
    private BigInteger assetId;
    private String assetName;
    private String nativeAddress;
    private String extraInfo;
    private String memo;
    private BigInteger accountIndex;
    private String l1Address;
    private BigInteger nonce;
    private BigInteger expireAt;
    private BigInteger blockHeight;
    private BigInteger createdAt;
    private BigInteger verifyAt;
    private String stateRoot;
    private BigInteger toAccountIndex;
    private String toL1Address;
}
