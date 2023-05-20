package com.tcp.zkbscan.back.controller;

import com.tcp.zkbscan.back.dto.statistics.BasicStatistics;
import com.tcp.zkbscan.back.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Statistics", description = "통계")
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "기본 통계 정보 조회", description = "zkBNB 관련 기본적인 통계 정보를 조회합니다.")
    @GetMapping("/statistics")
    public BasicStatistics getBasicStatistics() {
        BasicStatistics basicStatistics = new BasicStatistics();
        basicStatistics.setBnbPrice(statisticsService.getBNBPrice());
        return basicStatistics;
    }
}
