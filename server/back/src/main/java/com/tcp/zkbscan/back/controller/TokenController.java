package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.PageDTO;
import com.tcp.zkbscan.back.dto.PageInfoDTO;
import com.tcp.zkbscan.back.dto.block.L2BlockDTO;
import com.tcp.zkbscan.back.dto.token.TokenTransferDTO;
import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.service.L2TransactionService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final L2TransactionService l2TransactionService;

    @GetMapping("/token/transfer")
    private PageDTO<List<TokenTransferDTO>> getTokenTransfer(@Positive @RequestParam int page,
                                                             @Positive @RequestParam int size) {
        Page<L2Transaction> transactions = l2TransactionService.getTransactionContainAsset(page - 1, size);
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, (int) transactions.getTotalElements(), transactions.getTotalPages());

        List<L2Transaction> l2Blocks = transactions.getContent();
        List<TokenTransferDTO> dtos = l2Blocks.stream().map(i -> TokenTransferDTO.builder()
                .txid(i.getHash())
                .method(convertType(i.getType()))
                .timestamp(i.getCreatedAt())
                .from(i.getL1Address())
                .to(i.getToL1Address())
                .amount(new BigDecimal(new BigInteger(i.getAmount()), 18))
                .tokenSymbol(i.getAssetName())
                .assetId(i.getAssetId())
                .build()).toList();

        return new PageDTO<>(dtos, pageInfoDTO);
    }

    private String convertType(BigInteger type) {
        if(type.equals(BigInteger.valueOf(2))) {
            return "Deposit";
        } else if(type.equals(BigInteger.valueOf(4))) {
            return "Transfer";
        } else if(type.equals(BigInteger.valueOf(5))) {
            return "Withdraw";
        } else if(type.equals(BigInteger.valueOf(9))) {
            return "Atomic Match";
        }
        return "";
    }
}
