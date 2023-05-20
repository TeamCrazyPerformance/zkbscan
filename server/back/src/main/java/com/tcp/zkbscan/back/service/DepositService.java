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
        List<L1TransactionDTO> l1Deposits = getDepositsOnL1().stream()
                .filter(i -> i.getInput().startsWith("0x684a5843") /* depositBNB */
                        || i.getInput().startsWith("0x10ff3764") /* depositBEP20 */
                        || i.getInput().startsWith("0x82a5b1aa")) /* depositNft */
                .sorted(Comparator.comparing(L1TransactionDTO::getBlockNumber).reversed())
                .toList();

        List<L2TransactionDTO> l2Deposits = getDepositsOnL2().stream()
                .sorted(Comparator.comparing(L2TransactionDTO::getBlockHeight).reversed())
                .toList();

        List<DepositTransactionDTO> result = new ArrayList<>();
        for (int i = 0; i < l1Deposits.size(); i++) {
            L1TransactionDTO l1TransactionDTO = l1Deposits.get(i);
            L2TransactionDTO l2TransactionDTO = l2Deposits.get(i);

            DepositTransactionDTO depositTransaction = new DepositTransactionDTO();
            depositTransaction.setL1BlockNumber(l1TransactionDTO.getBlockNumber());
            depositTransaction.setL1Txid(l1TransactionDTO.getHash());
            depositTransaction.setL2BlockHeight(l2TransactionDTO.getBlockHeight());
            depositTransaction.setL2Txid(l2TransactionDTO.getHash());
            depositTransaction.setL1Address(l1TransactionDTO.getFrom());
            depositTransaction.setL2Address(l2TransactionDTO.getToL1Address());
            depositTransaction.setL1Timestamp(l1BlockService.getBlockByNumber(l1TransactionDTO.getBlockNumber()).getTimestamp());
            depositTransaction.setL2CreatedAt(l2TransactionDTO.getCreatedAt());
            depositTransaction.setL2VerifyAt(l2TransactionDTO.getVerifyAt());

            result.add(depositTransaction);
        }

        return result;
    }

    private List<L1TransactionDTO> getDepositsOnL1() {
        List<L1Transaction> l1Transactions = l1TransactionService.getTransactionByToAndStatus(l1ContractAddress, "0x1");

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
