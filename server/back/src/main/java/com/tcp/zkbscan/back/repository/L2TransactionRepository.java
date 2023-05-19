package com.tcp.zkbscan.back.repository;

import com.tcp.zkbscan.back.entity.L2Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface L2TransactionRepository extends JpaRepository<L2Transaction, String>, JpaSpecificationExecutor<L2Transaction> {

    Page<L2Transaction> findAllByOrderByCreatedAt(Pageable pageable);

}

