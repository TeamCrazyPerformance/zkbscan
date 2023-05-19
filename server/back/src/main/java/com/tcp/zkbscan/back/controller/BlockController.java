package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.block.BlockStatistics;
import com.tcp.zkbscan.back.dto.block.L1BlockDTO;
import com.tcp.zkbscan.back.dto.transaction.L1TransactionDTO;
import com.tcp.zkbscan.back.entity.L1Block;
import com.tcp.zkbscan.back.entity.L2Block;
import com.tcp.zkbscan.back.service.L1BlockService;
import com.tcp.zkbscan.back.service.L2BlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.stream.Collectors;

@Tag(name = "Block", description = "블록 정보")
@RestController
@RequiredArgsConstructor
public class BlockController {

    private final L1BlockService l1BlockService;
    private final L2BlockService l2BlockService;

    @Operation(summary = "블록 통계 조회", description = "L1, L2의 최근 블록 넘버와 같은 블록 통계를 조회합니다.")
    @GetMapping("/block")
    public BlockStatistics getBlockStatistics() {
        return BlockStatistics.builder()
                .latestL1BlockNumber(l1BlockService.getLatestBlock().getNumber())
                .latestL2BlockHeight(l2BlockService.getLatestBlock().getHeight())
                .build();
    }

    @Operation(summary = "L1 블록 조회", description = "BlockNumber 기준의 L1 블록 데이터를 조회합니다.")
    @GetMapping("/block/l1/{blockNumber}")
    public L1BlockDTO getL1BlockByNumber(@PathVariable("blockNumber") BigInteger blockNumber) {
        L1Block l1Block = l1BlockService.getBlockByNumber(blockNumber);

        return new L1BlockDTO(l1Block);
    }

    @Operation(summary = "L2 블록 조회", description = "BlockHeight 기준의 L2 블록 데이터를 조회합니다.")
    @GetMapping("/block/l2/{blockHeight}")
    public L2Block getL2BlockByNumber(@PathVariable("blockHeight") BigInteger blockHeight) {
        return l2BlockService.getBlockByHeight(blockHeight);
    }

}
