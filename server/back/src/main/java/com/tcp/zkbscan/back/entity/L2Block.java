package com.tcp.zkbscan.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

    @Column(columnDefinition = "TEXT")
    private String pendingOnChainOperationsPubData;

    private String committedTxHash;

    private BigInteger committedAt;

    private String verifiedTxHash;

    private BigInteger verifiedAt;

    @OneToMany(mappedBy = "block")
    private List<L2Transaction> transactions = new ArrayList<>();

    private BigInteger status;

}
