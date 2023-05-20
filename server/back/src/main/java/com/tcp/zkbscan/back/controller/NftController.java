package com.tcp.zkbscan.back.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.zkbscan.back.dto.PageDTO;
import com.tcp.zkbscan.back.dto.PageInfoDTO;
import com.tcp.zkbscan.back.dto.nft.NftTransferDTO;
import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.service.L2TransactionService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NftController {

    private final L2TransactionService l2TransactionService;
    private final ObjectMapper objectMapper;

    @GetMapping("/nft/transfer")
    private PageDTO<List<NftTransferDTO>> getNftTransfer(@Positive @RequestParam int page,
                                                         @Positive @RequestParam int size) {
        List<BigInteger> types = new ArrayList<>();
        types.add(BigInteger.valueOf(3));
        types.add(BigInteger.valueOf(8));
        types.add(BigInteger.valueOf(11));
        List<L2Transaction> transactions = l2TransactionService.getTransactionByTypes(types);

        final int start = (page - 1) * size;
        final int end = Math.min((start + size), transactions.size());
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, transactions.size(), (int) Math.ceil((double) transactions.size() / size));

        return new PageDTO<>(transactions.subList(start, end).stream().map(i -> NftTransferDTO.builder()
                .txid(i.getHash())
                .method(convertType(i.getType()))
                .timestamp(i.getCreatedAt())
                .from(i.getL1Address())
                .to(i.getToL1Address())
                .nftIndex(i.getNftIndex())
                .build()).toList(), pageInfoDTO);
    }

    private String convertType(BigInteger type) {
        if (type.equals(BigInteger.valueOf(3))) {
            return "Deposit Nft";
        } else if (type.equals(BigInteger.valueOf(8))) {
            return "Transfer Nft";
        } else if (type.equals(BigInteger.valueOf(11))) {
            return "Withdraw Nft";
        }
        return "";
    }
}
