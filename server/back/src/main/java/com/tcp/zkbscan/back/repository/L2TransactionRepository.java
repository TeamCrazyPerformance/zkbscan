package com.tcp.zkbscan.back.repository;

import com.tcp.zkbscan.back.entity.L2Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface L2TransactionRepository extends JpaRepository<L2Transaction, String>, JpaSpecificationExecutor<L2Transaction> {

    Page<L2Transaction> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Long countBy();

    Page<L2Transaction> findAllByAssetIdIsNotOrderByCreatedAtDesc(BigInteger assetId, Pageable pageable);
}

