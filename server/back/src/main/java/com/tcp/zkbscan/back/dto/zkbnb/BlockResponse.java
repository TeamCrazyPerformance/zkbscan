package com.tcp.zkbscan.back.dto.zkbnb;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class BlockResponse {
    private BigInteger code;
    private String message;
    private String commitment;
    private BigInteger height;
    private String state_root;
    private BigInteger priority_operations;
    private String pending_on_chain_operations_hash;
    private String pending_on_chain_operations_pub_data;
    private String committed_tx_hash;
    private BigInteger committed_at;
    private String verified_tx_hash;
    private BigInteger verified_at;
    private List<Transaction> txs;
    private BigInteger status;
    private BigInteger size;
}
