package com.tcp.zkbscan.back.specification;

import com.tcp.zkbscan.back.entity.L2Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;

public class L2TransactionSpecification {

    public static Specification<L2Transaction> equalType(BigInteger type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<L2Transaction> notEqualAssetId(BigInteger assetId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("assetId"), assetId);
    }

    public static Specification<L2Transaction> equalL1Address(String l1Address) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("l1Address"), l1Address);
    }

    public static Specification<L2Transaction> equalToL1Address(String toL1Address) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("toL1Address"), toL1Address);
    }
}
