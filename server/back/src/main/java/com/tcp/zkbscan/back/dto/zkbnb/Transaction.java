package com.tcp.zkbscan.back.dto.zkbnb;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Transaction {
    private String hash;
    private BigInteger type;
    private String amount;
    private String info;
    private BigInteger status;
    private BigInteger index;
    private BigInteger gas_fee_asset_id;
    private String gas_fee;
    private BigInteger nft_index;
    private BigInteger collection_id;
    private BigInteger asset_id;
    private String asset_name;
    private String native_address;
    private String extra_info;
    private String memo;
    private BigInteger account_index;
    private String l1_address;
    private BigInteger nonce;
    private BigInteger expire_at;
    private BigInteger block_height;
    private BigInteger created_at;
    private BigInteger verify_at;
    private String state_root;
    private BigInteger to_account_index;
    private String to_l1_address;
}
