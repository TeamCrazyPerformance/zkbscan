package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.PageDTO;
import com.tcp.zkbscan.back.dto.PageInfoDTO;
import com.tcp.zkbscan.back.dto.block.BlockStatistics;
import com.tcp.zkbscan.back.dto.block.L1BlockDTO;
import com.tcp.zkbscan.back.dto.block.L2BlockDTO;
import com.tcp.zkbscan.back.entity.L1Block;
import com.tcp.zkbscan.back.entity.L2Block;
import com.tcp.zkbscan.back.service.L1BlockService;
import com.tcp.zkbscan.back.service.L2BlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

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

    @Operation(summary = "L1 블록 단건 조회", description = "BlockNumber 기준의 L1 블록 데이터를 조회합니다.")
    @GetMapping("/block/l1/{blockNumber}")
    public L1BlockDTO getL1BlockByNumber(@PathVariable("blockNumber") BigInteger blockNumber) {
        L1Block l1Block = l1BlockService.getBlockByNumber(blockNumber);

        return new L1BlockDTO(l1Block);
    }

    @Operation(summary = "L2 블록 단건 조회", description = "BlockHeight 기준의 L2 블록 데이터를 조회합니다.")
    @GetMapping("/block/l2/{blockHeight}")
    public L2BlockDTO getL2BlockByNumber(@PathVariable("blockHeight") BigInteger blockHeight) {
        L2Block l2Block = l2BlockService.getBlockByHeight(blockHeight);

        return new L2BlockDTO(l2Block);
    }

    @Operation(summary = "L2 블록 조회", description = "L2 블록 데이터들을 조회합니다.")
    @GetMapping("/block/l2")
    public PageDTO<List<L2BlockDTO>> getL2BlockByNumber(@Positive @RequestParam int page,
                                                        @Positive @RequestParam int size) {
        Page<L2Block> l2BlockPage = l2BlockService.getBlock(page - 1, size);
        PageInfoDTO pageInfoDTO = new PageInfoDTO(page, size, (int) l2BlockPage.getTotalElements(), l2BlockPage.getTotalPages());

        List<L2Block> l2Blocks = l2BlockPage.getContent();
        List<L2BlockDTO> dtos = l2Blocks.stream().map(L2BlockDTO::new).toList();

        return new PageDTO<>(dtos, pageInfoDTO);
    }

}
