package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.transaction.L1TransactionDTO;
import com.tcp.zkbscan.back.entity.L1Transaction;
import com.tcp.zkbscan.back.service.L1TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transaction", description = "트랜젝션 정보")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final L1TransactionService l1TransactionService;

    @Operation(summary = "L1 트랜젝션 조회", description = "TXID로 L1 트랜젝션 데이터를 조회합니다.")
    @GetMapping("/tx/l1/{txid}")
    public L1TransactionDTO getL1BlockByNumber(@PathVariable("txid") String hash) {
        L1Transaction l1Transaction = l1TransactionService.getTransaction(hash);

        return new L1TransactionDTO(l1Transaction);
    }
}
