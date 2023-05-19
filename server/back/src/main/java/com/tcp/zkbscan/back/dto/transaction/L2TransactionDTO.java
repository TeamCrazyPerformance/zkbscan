package com.tcp.zkbscan.back.dto.transaction;

import com.tcp.zkbscan.back.entity.L2Transaction;
import lombok.Data;

import java.math.BigInteger;

@Data
public class L2TransactionDTO {

    private String hash;
    private BigInteger type;
    private String amount;
    private String info;
    private BigInteger status;
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

    public L2TransactionDTO(L2Transaction transaction) {
        this.hash = transaction.getHash();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.info = transaction.getInfo();
        this.status = transaction.getStatus();
        this.index = transaction.getIndex();
        this.gasFeeAssetId = transaction.getGasFeeAssetId();
        this.gasFee = transaction.getGasFee();
        this.nftIndex = transaction.getNftIndex();
        this.collectionId = transaction.getCollectionId();
        this.assetId = transaction.getAssetId();
        this.assetName = transaction.getAssetName();
        this.nativeAddress = transaction.getNativeAddress();
        this.extraInfo = transaction.getExtraInfo();
        this.memo = transaction.getMemo();
        this.accountIndex = transaction.getAccountIndex();
        this.l1Address = transaction.getL1Address();
        this.nonce = transaction.getNonce();
        this.expireAt = transaction.getExpireAt();
        this.blockHeight = transaction.getBlock().getHeight();
        this.createdAt = transaction.getCreatedAt();
        this.verifyAt = transaction.getVerifyAt();
        this.stateRoot = transaction.getStateRoot();
        this.toAccountIndex = transaction.getToAccountIndex();
        this.toL1Address = transaction.getToL1Address();
    }
}
