package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L1Transaction;
import com.tcp.zkbscan.back.repository.L1TransactionRepository;
import com.tcp.zkbscan.back.specification.L1TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class L1TransactionService {

    private final L1TransactionRepository l1TransactionRepository;

    public L1Transaction getTransaction(String hash) {
        Optional<L1Transaction> transaction = l1TransactionRepository.findById(hash);
        return transaction.get();
    }

    public void saveTransaction(L1Transaction l1Transaction) {
        l1TransactionRepository.save(l1Transaction);
    }

    public List<L1Transaction> getTransactionByToAndStatus(String to, String status) {
        Specification<L1Transaction> spec = Specification.where(L1TransactionSpecification.equalTo(to));
        if(status == null)
            spec = spec.and(L1TransactionSpecification.isNullStatus());
        else
            spec = spec.and(L1TransactionSpecification.equalStatus(status));

        return l1TransactionRepository.findAll(spec);
    }

}
