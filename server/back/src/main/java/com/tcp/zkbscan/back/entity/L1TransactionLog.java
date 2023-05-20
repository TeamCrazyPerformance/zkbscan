package com.tcp.zkbscan.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@IdClass(L1TransactionLogPK.class)
public class L1TransactionLog {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transactionHash", referencedColumnName = "hash", nullable = false)
    private L1Transaction transaction;

    @Id
    private BigInteger logIndex;

    @Id
    private String topic;
}
