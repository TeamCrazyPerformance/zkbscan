package com.tcp.zkbscan.back.dto.statistics;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TVLResponseDTO {
    private List<TVL> tokens;
    private BigDecimal value;
}
