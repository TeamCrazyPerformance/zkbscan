package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.dto.binance.BinanceTicker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final RestTemplate restTemplate;

    public BigDecimal getBNBPrice() {
        List<BinanceTicker> binanceTickers = restTemplate.exchange("https://api.binance.com/api/v3/ticker/price",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BinanceTicker>>() {
                }).getBody();

        return binanceTickers.stream()
                .filter(i -> i.getSymbol().equals("BNBUSDT"))
                .collect(Collectors.toList())
                .get(0).getPrice();
    }


}
