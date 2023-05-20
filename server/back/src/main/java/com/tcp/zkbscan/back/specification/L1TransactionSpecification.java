package com.tcp.zkbscan.back.specification;

import com.tcp.zkbscan.back.entity.L1Transaction;
import org.springframework.data.jpa.domain.Specification;

public class L1TransactionSpecification {

    public static Specification<L1Transaction> equalStatus(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<L1Transaction> isNullStatus() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("status"));
    }

    public static Specification<L1Transaction> equalTo(String to) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("to"), to);
    }
}
