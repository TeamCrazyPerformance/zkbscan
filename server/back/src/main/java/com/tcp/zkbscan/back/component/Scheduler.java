package com.tcp.zkbscan.back.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final Web3j bscL1Rpc;

    @Scheduled(fixedRate = 1000)
    public void fethNewBlock() {
        try {
            EthBlock.Block block = bscL1Rpc.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
                    .send()
                    .getBlock();
            log.info(block.getNumber().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
