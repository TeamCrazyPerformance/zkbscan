package com.tcp.zkbscan.back.dto.block;

import com.tcp.zkbscan.back.dto.transaction.L2TransactionDTO;
import com.tcp.zkbscan.back.entity.L2Block;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class L2BlockDTO {

    private BigInteger height;
    private String commitment;
    private String stateRoot;
    private BigInteger priorityOperations;
    private String pendingOnChainOperationsHash;
    private String pendingOnChainOperationsPubData;
    private String committedTxHash;
    private BigInteger committedAt;
    private String verifiedTxHash;
    private BigInteger verifiedAt;
    private List<L2TransactionDTO> transactions;
    private BigInteger status;

    public L2BlockDTO(L2Block block) {
        this.height = block.getHeight();
        this.commitment = block.getCommitment();
        this.stateRoot = block.getStateRoot();
        this.priorityOperations = block.getPriorityOperations();
        this.pendingOnChainOperationsHash = block.getPendingOnChainOperationsHash();
        this.pendingOnChainOperationsPubData = block.getPendingOnChainOperationsPubData();
        this.committedTxHash = block.getCommittedTxHash();
        this.committedAt = block.getCommittedAt();
        this.verifiedTxHash = block.getVerifiedTxHash();
        this.verifiedAt = block.getVerifiedAt();
        this.transactions = block.getTransactions().stream().map(L2TransactionDTO::new).collect(Collectors.toList());
        this.status = block.getStatus();
    }

}