package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.repository.L2TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class L2TransactionService {

    private final L2TransactionRepository l2TransactionRepository;

    public L2Transaction getTransaction(String hash) {
        Optional<L2Transaction> transaction = l2TransactionRepository.findById(hash);
        return transaction.get();
    }

    public void saveTransaction(L2Transaction l2Transaction) {
        l2TransactionRepository.save(l2Transaction);
    }
}
