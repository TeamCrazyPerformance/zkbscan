package com.tcp.zkbscan.back.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class NodeRpc {

    @Value("${bsc.l1.rpc.url}")
    private String bscL1RpcUrl;

    @Bean
    public Web3j bscL1Rpc() {
        return Web3j.build(new HttpService(bscL1RpcUrl));
    }
}
