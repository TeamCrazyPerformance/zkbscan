package com.tcp.zkbscan.back.specification;

import com.tcp.zkbscan.back.entity.L2Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;

public class L2TransactionSpecification {

    public static Specification<L2Transaction> equalType(BigInteger type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }
}
