package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.block.BlockStatistics;
import com.tcp.zkbscan.back.service.L1BlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Block", description = "블록 정보")
@RestController
@RequiredArgsConstructor
public class BlockController {

    private final L1BlockService l1BlockService;

    @Operation(summary = "블록 통계 조회", description = "L1, L2의 최근 블록 넘버와 같은 블록 통계를 조회합니다.")
    @GetMapping("/block")
    public BlockStatistics getBlockStatistics() {
        return BlockStatistics.builder()
                .latestL1BlockNumber(l1BlockService.getLatestBlock().getNumber())
                .latestL2BlockHeight(l2BlockService.getLatestBlock().getHeight())
                .build();
    }

}
