package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.dto.account.AccountAssetDTO;
import com.tcp.zkbscan.back.dto.account.AccountDTO;
import com.tcp.zkbscan.back.dto.account.AccountTransactionChart;
import com.tcp.zkbscan.back.dto.zkbnb.AccountResponse;
import com.tcp.zkbscan.back.entity.L2Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;
    private final L2TransactionService l2TransactionService;

    public AccountDTO getAccountDetail(String address) {
        AccountDTO accountDTO = new AccountDTO();

        AccountResponse account = restTemplate.exchange("https://api-testnet.zkbnbchain.org/api/v1/account?by=l1_address&value=" + address,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AccountResponse>() {
                }).getBody();

        accountDTO.setAddress(address);
        accountDTO.setAccountIndex(account.getIndex());
        accountDTO.setPk(account.getPk());
        accountDTO.setAssets(account.getAssets().stream()
                .map(i -> new AccountAssetDTO(i.getId(), i.getName(), new BigDecimal(i.getBalance(), 18), i.getPrice())).toList());
        accountDTO.setTotalAssetValue(account.getTotal_asset_value());

        return accountDTO;
    }

    public List<AccountTransactionChart> getTransactionChart(String address) {
        List<L2Transaction> txs = l2TransactionService.getTransactionByAddress(address);
        Map<LocalDate, List<L2Transaction>> transactionGroup = txs.stream()
                .collect(Collectors.groupingBy(
                        o -> Instant.ofEpochSecond(o.getCreatedAt().longValue())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                ));

        List<AccountTransactionChart> result = new ArrayList<>();
        for (LocalDate d : transactionGroup.keySet()) {
            AccountTransactionChart chart = new AccountTransactionChart();
            chart.setDate(d);
            chart.setTransactionCount(transactionGroup.get(d).size());
            result.add(chart);
        }

        return result;
    }

}
