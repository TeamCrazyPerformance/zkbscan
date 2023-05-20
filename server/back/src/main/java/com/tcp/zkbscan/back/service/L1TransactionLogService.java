package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L1TransactionLog;
import com.tcp.zkbscan.back.repository.L1TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class L1TransactionLogService {

    private final L1TransactionLogRepository l1TransactionLogRepository;

    public void saveTransactionLog(L1TransactionLog l1TransactionLog) {
        l1TransactionLogRepository.save(l1TransactionLog);
    }

}