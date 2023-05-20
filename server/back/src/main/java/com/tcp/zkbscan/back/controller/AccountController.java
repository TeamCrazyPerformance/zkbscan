package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.PageDTO;
import com.tcp.zkbscan.back.dto.PageInfoDTO;
import com.tcp.zkbscan.back.dto.account.AccountDTO;
import com.tcp.zkbscan.back.dto.account.AccountTransactionChart;
import com.tcp.zkbscan.back.dto.transaction.L2TransactionDTO;
import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.service.AccountService;
import com.tcp.zkbscan.back.service.L2TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Account", description = "어카운트 정보")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final L2TransactionService l2TransactionService;

    @Operation(summary = "어카운트 정보 조회", description = "어카운트의 정보를 조회합니다.")
    @GetMapping("/account/{address}")
    public AccountDTO getAccountDetail(@PathVariable("address") String address) {
        return accountService.getAccountDetail(address);
    }

    @Operation(summary = "어카운트 관련 트랜젝션 조회", description = "어카운트와 관련하여 발생된 트랜젝션 정보들을 조회합니다.")
    @GetMapping("/account/{address}/transaction")
    public PageDTO<List<L2TransactionDTO>> getTransactionByAddress(@PathVariable("address") String address,
                                                                   @Positive @RequestParam int page,
                                                                   @Positive @RequestParam int size) {
        List<L2Transaction> transactions = l2TransactionService.getTransactionByAddress(address);
        Collections.reverse(transactions);

        final int start = (page - 1) * size;
        final int end = Math.min((start + size), transactions.size());
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, transactions.size(), (int) Math.ceil((double)transactions.size() / size));

        return new PageDTO<>(transactions.subList(start, end).stream().map(L2TransactionDTO::new).collect(Collectors.toList()), pageInfoDTO);
    }

    @Operation(summary = "어카운트 관련 트랜젝션 차트 조회", description = "어카운트에서 일간 발생한 트랜젝션 수를 조회합니다.")
    @GetMapping("/account/{address}/transaction/chart")
    public List<AccountTransactionChart> getTransactionChart(@PathVariable("address") String address) {
        return accountService.getTransactionChart(address);
    }
}
