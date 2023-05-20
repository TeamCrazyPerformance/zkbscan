package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.dto.binance.BinanceTicker;
import com.tcp.zkbscan.back.dto.statistics.TVL;
import com.tcp.zkbscan.back.dto.statistics.TVLResponseDTO;
import com.tcp.zkbscan.back.dto.statistics.TransactionChart;
import com.tcp.zkbscan.back.entity.L2Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final RestTemplate restTemplate;
    private final L2TransactionService l2TransactionService;

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

    private List<List<Object>> getBNBPriceChart() {
        return restTemplate.exchange("http://api.binance.com/api/v3/klines?symbol=BNBUSDT&interval=1d&limit=30",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<List<Object>>>() {
                }).getBody();
    }

    public List<TransactionChart> getTransactionChart() {
        List<L2Transaction> txs = l2TransactionService.getAllTransaction();
        Map<LocalDate, List<L2Transaction>> transactionGroup = txs.stream()
                .collect(Collectors.groupingBy(
                        o -> Instant.ofEpochSecond(o.getCreatedAt().longValue())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                ));

        Map<LocalDate, BigDecimal> price = new HashMap<>();
        for(List<Object> l : getBNBPriceChart()) {
            price.put(Instant.ofEpochMilli((long)l.get(0))
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(), new BigDecimal((String) l.get(4)));
        }

        List<TransactionChart> result = new ArrayList<>();
        for(LocalDate d : transactionGroup.keySet()) {
            TransactionChart chart = new TransactionChart();
            chart.setDate(d);
            chart.setTransactionCount(transactionGroup.get(d).size());
            chart.setPrice(price.get(d));
            result.add(chart);
        }

        return result;
    }

    public TVLResponseDTO getTvl() {
        TVLResponseDTO result = new TVLResponseDTO();
        result.setTokens(new ArrayList<>());

        Map<String, BigDecimal> tvl = new HashMap<>();

        List<L2Transaction> depositTokenTxs = l2TransactionService.getTransactionContainAssetByType(BigInteger.valueOf(2));
        List<L2Transaction> withdrawalTokenTxs = l2TransactionService.getTransactionContainAssetByType(BigInteger.valueOf(5));

        for(L2Transaction depositTokenTx : depositTokenTxs) {
            if(!tvl.containsKey(depositTokenTx.getAssetName()))
                tvl.put(depositTokenTx.getAssetName(), new BigDecimal(new BigInteger(depositTokenTx.getAmount()), 18));
            else {
                BigDecimal sum = tvl.get(depositTokenTx.getAssetName());
                tvl.put(depositTokenTx.getAssetName(), sum.add(new BigDecimal(new BigInteger(depositTokenTx.getAmount()), 18)));
            }
        }

        for(L2Transaction withdrawalTokenTx : withdrawalTokenTxs) {
            BigDecimal sum = tvl.get(withdrawalTokenTx.getAssetName());
            tvl.put(withdrawalTokenTx.getAssetName(), sum.subtract(new BigDecimal(new BigInteger(withdrawalTokenTx.getAmount()), 18)));
        }

        List<BinanceTicker> binanceTickers = restTemplate.exchange("https://api.binance.com/api/v3/ticker/price",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BinanceTicker>>() {
                }).getBody();
        Map<String, BigDecimal> price = binanceTickers.stream().collect(
                Collectors.toMap(BinanceTicker::getSymbol, BinanceTicker::getPrice));

        BigDecimal totalSum = BigDecimal.ZERO;
        for(String symbol : tvl.keySet()) {
            TVL t = new TVL();
            t.setSymbol(symbol);
            t.setAmount(tvl.get(symbol));
            t.setValue(price.get(symbol + "USDT").multiply(t.getAmount()));
            result.getTokens().add(t);
            totalSum = totalSum.add(t.getValue());
        }

        result.setValue(totalSum);

        return result;
    }
}
