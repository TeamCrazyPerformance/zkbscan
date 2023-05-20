package com.tcp.zkbscan.back.repository;

import com.tcp.zkbscan.back.entity.L1TransactionLog;
import com.tcp.zkbscan.back.entity.L1TransactionLogPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface L1TransactionLogRepository extends JpaRepository<L1TransactionLog, L1TransactionLogPK> {
}
