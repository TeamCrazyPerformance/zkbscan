package com.tcp.zkbscan.back.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.zkbscan.back.dto.PageDTO;
import com.tcp.zkbscan.back.dto.PageInfoDTO;
import com.tcp.zkbscan.back.dto.nft.*;
import com.tcp.zkbscan.back.entity.L2Transaction;
import com.tcp.zkbscan.back.service.L2TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "NFT", description = "NFT")
@RestController
@RequiredArgsConstructor
public class NftController {

    private final L2TransactionService l2TransactionService;
    private final ObjectMapper objectMapper;

    @Operation(summary = "NFT Transfer 정보 조회", description = "NFT 이동과 관련된 정보를 조회합니다.")
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

    @Operation(summary = "NFT 민팅 정보 조회", description = "NFT 발행과 관련된 정보를 조회합니다.")
    @GetMapping("/nft/mint")
    private PageDTO<List<NftMintDTO>> getNftMint(@Positive @RequestParam int page,
                                                 @Positive @RequestParam int size) {
        List<BigInteger> types = new ArrayList<>();
        types.add(BigInteger.valueOf(7));
        List<L2Transaction> transactions = l2TransactionService.getTransactionByTypes(types);

        final int start = (page - 1) * size;
        final int end = Math.min((start + size), transactions.size());
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, transactions.size(), (int) Math.ceil((double) transactions.size() / size));

        return new PageDTO<>(transactions.subList(start, end).stream().map(i -> {
            try {
                return NftMintDTO.builder()
                        .txid(i.getHash())
                        .timestamp(i.getCreatedAt())
                        .maker(i.getToL1Address())
                        .nftImageUrl(objectMapper.readValue(objectMapper.readValue(i.getInfo(), NftMintInfo.class).getMetaData(), NftMetadata.class).getImage())
                        .nftName(objectMapper.readValue(objectMapper.readValue(i.getInfo(), NftMintInfo.class).getMetaData(), NftMetadata.class).getName())
                        .nftIndex(i.getNftIndex())
                        .build();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).toList(), pageInfoDTO);
    }

    @Operation(summary = "NFT 거래 정보 조회", description = "NFT 거래와 관련된 정보를 조회합니다.")
    @GetMapping("/nft/trade")
    private PageDTO<List<NftTradeDTO>> getNftTrade(@Positive @RequestParam int page,
                                                   @Positive @RequestParam int size) {
        List<BigInteger> types = new ArrayList<>();
        types.add(BigInteger.valueOf(9));
        List<L2Transaction> transactions = l2TransactionService.getTransactionByTypes(types);

        final int start = (page - 1) * size;
        final int end = Math.min((start + size), transactions.size());
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, transactions.size(), (int) Math.ceil((double) transactions.size() / size));

        return new PageDTO<>(transactions.subList(start, end).stream().map(i -> {
                    try {
                        return NftTradeDTO.builder()
                                .txid(i.getHash())
                                .timestamp(i.getCreatedAt())
                                .side("buy")
                                .price(new BigDecimal(objectMapper.readValue(i.getInfo(), NftTradeInfo.class).getBuyChannelAmount(), 18))
                                .nftIndex(i.getNftIndex())
                                .build();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList(), pageInfoDTO);
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
