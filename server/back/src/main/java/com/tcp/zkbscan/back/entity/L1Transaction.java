package com.tcp.zkbscan.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class L1Transaction {

    @Id
    private String hash;

    private String blockHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blockNumber", referencedColumnName = "number", nullable = false)
    private L1Block block;

    @Column(name = "\"from\"")
    private String from;

    private BigInteger gas;

    private BigInteger gasPrice;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String input;

    private BigInteger nonce;

    @Column(name = "\"to\"")
    private String to;

    private BigInteger transactionIndex;

    private BigInteger value;

    private String type;

    private String status;

    private Long v;

    private String r;

    private String s;

}
