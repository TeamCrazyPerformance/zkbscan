package com.tcp.zkbscan.back.dto.transaction;

import com.tcp.zkbscan.back.entity.L1Transaction;
import lombok.Data;

import java.math.BigInteger;

@Data
public class L1TransactionDTO {

    private String hash;
    private String blockHash;
    private BigInteger blockNumber;
    private String from;
    private BigInteger gas;
    private BigInteger gasPrice;
    private String input;
    private BigInteger nonce;
    private String to;
    private BigInteger transactionIndex;
    private BigInteger value;
    private String type;
    private Long v;
    private String r;
    private String s;

    public L1TransactionDTO(L1Transaction l1Transaction) {
        this.setHash(l1Transaction.getHash());
        this.setBlockHash(l1Transaction.getBlockHash());
        this.setBlockNumber(l1Transaction.getBlock().getNumber());
        this.setFrom(l1Transaction.getFrom());
        this.setGas(l1Transaction.getGas());
        this.setGasPrice(l1Transaction.getGasPrice());
        this.setInput(l1Transaction.getInput());
        this.setNonce(l1Transaction.getNonce());
        this.setTo(l1Transaction.getTo());
        this.setTransactionIndex(l1Transaction.getTransactionIndex());
        this.setValue(l1Transaction.getValue());
        this.setType(l1Transaction.getType());
        this.setV(l1Transaction.getV());
        this.setR(l1Transaction.getR());
        this.setS(l1Transaction.getS());
    }

}
