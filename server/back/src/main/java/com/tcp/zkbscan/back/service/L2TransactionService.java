package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.repository.L2TransactionRepository;
import com.tcp.zkbscan.back.specification.L2TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
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

    public List<L2Transaction> getTransactionByTypes(List<BigInteger> types) {
        Specification<L2Transaction> spec = Specification.where(L2TransactionSpecification.equalType(types.get(0)));

        for (int i = 1; i < types.size(); i++) {
            spec = spec.or(L2TransactionSpecification.equalType(types.get(i)));
        }

        return l2TransactionRepository.findAll(spec);
    }

    public Page<L2Transaction> getTransaction(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return l2TransactionRepository.findAllByOrderByCreatedAtDesc(pageRequest);
    }

    public List<L2Transaction> getAllTransaction() {
        return l2TransactionRepository.findAll();
    }

    public List<L2Transaction> getTransactionContainAssetByType(BigInteger type) {
        Specification<L2Transaction> spec = Specification.where(L2TransactionSpecification.notEqualAssetId(BigInteger.valueOf(-1)));
        spec = spec.and(L2TransactionSpecification.equalType(type));

        return l2TransactionRepository.findAll(spec);
    }

    public Page<L2Transaction> getTransactionContainAsset(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return l2TransactionRepository.findAllByAssetIdIsNotOrderByCreatedAtDesc(BigInteger.valueOf(-1), pageRequest);
    }

    public List<L2Transaction> getTransactionByAddress(String address) {
        Specification<L2Transaction> spec = Specification.where(L2TransactionSpecification.equalL1Address(address));
        spec = spec.or(L2TransactionSpecification.equalToL1Address(address));

        return l2TransactionRepository.findAll(spec);
    }

    public Long getTotalTransactionCount() {
        return l2TransactionRepository.countBy();
    }

}
