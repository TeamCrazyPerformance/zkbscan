package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.statistics.BasicStatistics;
import com.tcp.zkbscan.back.dto.statistics.TVLResponseDTO;
import com.tcp.zkbscan.back.dto.statistics.TransactionChart;
import com.tcp.zkbscan.back.service.L1BlockService;
import com.tcp.zkbscan.back.service.L2BlockService;
import com.tcp.zkbscan.back.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Statistics", description = "통계")
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final L1BlockService l1BlockService;
    private final L2BlockService l2BlockService;

    @Operation(summary = "기본 통계 정보 조회", description = "zkBNB 관련 기본적인 통계 정보를 조회합니다.")
    @GetMapping("/statistics")
    public BasicStatistics getBasicStatistics() {
        BasicStatistics basicStatistics = new BasicStatistics();
        basicStatistics.setBnbPrice(statisticsService.getBNBPrice());
        basicStatistics.setLatestL1BlockNumber(l1BlockService.getLatestBlock().getNumber());
        basicStatistics.setLatestl2BlockNumber(l2BlockService.getLatestBlock().getHeight());
        basicStatistics.setTvl(statisticsService.getTvl().getValue());
        return basicStatistics;
    }

    @Operation(summary = "트랜젝션 차트 조회", description = "zkBNB에서 일간 발생한 트랜젝션 수를 조회합니다.")
    @GetMapping("/statistics/chart")
    public List<TransactionChart> getTransactionChart() {
        return statisticsService.getTransactionChart();
    }

    @Operation(summary = "TVL 조회", description = "zkBNB에 예치중인 토큰들의 가치를 조회합니다.")
    @GetMapping("/statistics/tvl")
    public TVLResponseDTO getTvl() {
        return statisticsService.getTvl();
    }
}
