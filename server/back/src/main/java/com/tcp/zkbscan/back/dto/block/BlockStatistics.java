package com.tcp.zkbscan.back.dto.block;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class BlockStatistics {

    private BigInteger latestL1BlockNumber;
    private BigInteger latestL2BlockHeight;

}
