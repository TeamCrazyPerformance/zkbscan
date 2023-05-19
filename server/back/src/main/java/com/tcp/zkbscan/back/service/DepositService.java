package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.dto.transaction.DepositTransactionDTO;
import com.tcp.zkbscan.back.dto.transaction.L1TransactionDTO;
import com.tcp.zkbscan.back.dto.transaction.L2TransactionDTO;
import com.tcp.zkbscan.back.entity.L1Transaction;
import com.tcp.zkbscan.back.entity.L2Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositService {

    private final L1TransactionService l1TransactionService;
    private final L2TransactionService l2TransactionService;

    @Value("${bsc.l1.contract.address}")
    private String l1ContractAddress;

    public List<DepositTransactionDTO> getDeposits() {
        List<L1TransactionDTO> l1Deposits = getDepositsOnL1();
        //List<L2TransactionDTO> l2Deposits = getDepositsOnL2();

        log.info(l1Deposits.toString());
        //log.info(l2Deposits.toString());
        return null;
    }

    private List<L1TransactionDTO> getDepositsOnL1() {
        List<L1Transaction> l1Transactions = l1TransactionService.getTransactionByToAndType(l1ContractAddress, "0x0");

        return l1Transactions.stream().map(L1TransactionDTO::new).collect(Collectors.toList());
    }

    private List<L2TransactionDTO> getDepositsOnL2() {
        List<BigInteger> types = new ArrayList<>();
        types.add(BigInteger.valueOf(2));
        types.add(BigInteger.valueOf(3));
        List<L2Transaction> l2Transactions = l2TransactionService.getTransactionByTypes(types);

        return l2Transactions.stream().map(L2TransactionDTO::new).collect(Collectors.toList());
    }

}
