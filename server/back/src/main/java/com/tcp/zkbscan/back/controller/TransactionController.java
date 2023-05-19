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

        L1TransactionDTO l1TransactionDTO = new L1TransactionDTO();
        l1TransactionDTO.setHash(l1Transaction.getHash());
        l1TransactionDTO.setBlockHash(l1Transaction.getBlockHash());
        l1TransactionDTO.setBlockNumber(l1Transaction.getBlock().getNumber());
        l1TransactionDTO.setFrom(l1Transaction.getFrom());
        l1TransactionDTO.setGas(l1Transaction.getGas());
        l1TransactionDTO.setGasPrice(l1Transaction.getGasPrice());
        l1TransactionDTO.setInput(l1Transaction.getInput());
        l1TransactionDTO.setNonce(l1Transaction.getNonce());
        l1TransactionDTO.setTo(l1Transaction.getTo());
        l1TransactionDTO.setTransactionIndex(l1Transaction.getTransactionIndex());
        l1TransactionDTO.setValue(l1Transaction.getValue());
        l1TransactionDTO.setType(l1Transaction.getType());
        l1TransactionDTO.setV(l1Transaction.getV());
        l1TransactionDTO.setR(l1Transaction.getR());
        l1TransactionDTO.setS(l1Transaction.getS());

        return l1TransactionDTO;
    }
}
