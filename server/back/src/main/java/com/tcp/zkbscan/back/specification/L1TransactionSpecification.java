package com.tcp.zkbscan.back.specification;

import com.tcp.zkbscan.back.entity.L1Transaction;
import org.springframework.data.jpa.domain.Specification;

public class L1TransactionSpecification {

    public static Specification<L1Transaction> equalType(String type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<L1Transaction> equalTo(String to) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("to"), to);
    }
}
