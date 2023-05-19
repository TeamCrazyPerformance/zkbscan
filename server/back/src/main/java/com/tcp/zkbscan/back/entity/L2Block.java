package com.tcp.zkbscan.back.entity;

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
public class L2Block {

    @Id
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

    //List : txs

    private BigInteger status;

}
