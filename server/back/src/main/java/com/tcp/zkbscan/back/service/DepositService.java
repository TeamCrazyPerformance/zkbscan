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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositService {

    private final L1BlockService l1BlockService;
    private final L1TransactionService l1TransactionService;
    private final L2TransactionService l2TransactionService;

    @Value("${bsc.l1.contract.address}")
    private String l1ContractAddress;

    public List<DepositTransactionDTO> getDeposits() {
        List<L1TransactionDTO> l1Deposits = getDepositsOnL1();
        List<L2TransactionDTO> l2Deposits = getDepositsOnL2();

        List<L1TransactionDTO> l1DepositsByDepositBNB = l1Deposits.stream()
                .filter(i -> i.getInput().startsWith("0x684a5843"))
                .sorted(Comparator.comparing(L1TransactionDTO::getBlockNumber).reversed())
                .toList();

        List<L2TransactionDTO> l2DepositsByDepositBNB = l2Deposits.stream()
                .filter(i -> i.getType().equals(BigInteger.valueOf(2)) && i.getAssetName().equals("BNB"))
                .sorted(Comparator.comparing(L2TransactionDTO::getBlockHeight).reversed())
                .toList();

        log.info(l1DepositsByDepositBNB.toString());
        log.info(l2DepositsByDepositBNB.toString());

        log.info("{} {}", l1DepositsByDepositBNB.size(), l2DepositsByDepositBNB.size());

        List<DepositTransactionDTO> result = new ArrayList<>();
        for(int i = 0; i < l1DepositsByDepositBNB.size(); i++) {
            L1TransactionDTO l1TransactionDTO = l1DepositsByDepositBNB.get(i);
            L2TransactionDTO l2TransactionDTO = l2DepositsByDepositBNB.get(i);

            DepositTransactionDTO depositTransaction = new DepositTransactionDTO();
            depositTransaction.setL1BlockNumber(l1TransactionDTO.getBlockNumber());
            depositTransaction.setL1Txid(l1TransactionDTO.getHash());
            depositTransaction.setL2BlockHeight(l2TransactionDTO.getBlockHeight());
            depositTransaction.setL2Txid(l2TransactionDTO.getHash());
            depositTransaction.setL1Address(l1TransactionDTO.getFrom());
            depositTransaction.setL2Address(l2TransactionDTO.getToL1Address());
            //depositTransaction.setL1Timestamp(l1BlockService.getBlockByNumber(l1TransactionDTO.getBlockNumber()).getTimestamp());
            depositTransaction.setL2CreatedAt(l2TransactionDTO.getCreatedAt());
            depositTransaction.setL2VerifyAt(l2TransactionDTO.getVerifyAt());

            result.add(depositTransaction);
        }

        return result;
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
