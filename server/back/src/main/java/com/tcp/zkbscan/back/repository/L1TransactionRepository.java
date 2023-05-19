package com.tcp.zkbscan.back.repository;

import com.tcp.zkbscan.back.entity.L1Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface L1TransactionRepository extends JpaRepository<L1Transaction, String> {
}
