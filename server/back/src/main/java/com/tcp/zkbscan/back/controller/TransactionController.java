package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.PageDTO;
import com.tcp.zkbscan.back.dto.PageInfoDTO;
import com.tcp.zkbscan.back.dto.transaction.DepositTransactionDTO;
import com.tcp.zkbscan.back.dto.transaction.L1TransactionDTO;
import com.tcp.zkbscan.back.dto.transaction.L2TransactionDTO;
import com.tcp.zkbscan.back.entity.L1Transaction;
import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.service.DepositService;
import com.tcp.zkbscan.back.service.L1TransactionService;
import com.tcp.zkbscan.back.service.L2TransactionService;
import com.tcp.zkbscan.back.service.WithdrawalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Transaction", description = "트랜젝션 정보")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final L1TransactionService l1TransactionService;
    private final L2TransactionService l2TransactionService;
    private final DepositService depositService;
    private final WithdrawalService withdrawalService;

    @Operation(summary = "L1 트랜젝션 단건 조회", description = "TXID로 L1 트랜젝션 데이터를 조회합니다.")
    @GetMapping("/tx/l1/{txid}")
    public L1TransactionDTO getL1BlockByNumber(@PathVariable("txid") String hash) {
        L1Transaction l1Transaction = l1TransactionService.getTransaction(hash);

        return new L1TransactionDTO(l1Transaction);
    }

    @Operation(summary = "L2 트랜젝션 단건 조회", description = "TXID로 L2 트랜젝션 데이터를 조회합니다.")
    @GetMapping("/tx/l2/{txid}")
    public L2TransactionDTO getL2BlockByNumber(@PathVariable("txid") String hash) {
        L2Transaction l2Transaction = l2TransactionService.getTransaction(hash);

        return new L2TransactionDTO(l2Transaction);
    }

    @Operation(summary = "L2 트랜젝션 조회", description = "L2 트랜젝션들을 조회합니다.")
    @GetMapping("/tx/l2")
    public PageDTO<List<L2TransactionDTO>> getL2BlockByNumber(@Positive @RequestParam int page,
                                               @Positive @RequestParam int size) {
        Page<L2Transaction> l2TransactionPage = l2TransactionService.getTransaction(page - 1, size);
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, (int) l2TransactionPage.getTotalElements(), l2TransactionPage.getTotalPages());

        List<L2Transaction> l2Transactions = l2TransactionPage.getContent();
        List<L2TransactionDTO> dtos = l2Transactions.stream().map(L2TransactionDTO::new).toList();

        return new PageDTO<>(dtos, pageInfoDTO);
    }

    @Operation(summary = "Deposit 트랜젝션 조회", description = "L1에서 L2로 Deposit된 트랜젝션 데이터를 조회합니다.")
    @GetMapping("/tx/deposit")
    public PageDTO<List<DepositTransactionDTO>> getDepositTransaction(@Positive @RequestParam int page,
                                                             @Positive @RequestParam int size) {
        List<DepositTransactionDTO> deposits = depositService.getDeposits();

        final int start = (page - 1) * size;
        final int end = Math.min((start + size), deposits.size());
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, deposits.size(), (int) Math.ceil((double)deposits.size() / size));

        return new PageDTO<>(deposits.subList(start, end), pageInfoDTO);
    }

    @Operation(summary = "Withdrawal 트랜젝션 조회", description = "L2에서 L1로 Withdrawal된 트랜젝션 데이터를 조회합니다.")
    @GetMapping("/tx/withdrawal")
    public List<DepositTransactionDTO> getWithdrawalTransaction() {
        return withdrawalService.getWithdrawals();
    }
}
