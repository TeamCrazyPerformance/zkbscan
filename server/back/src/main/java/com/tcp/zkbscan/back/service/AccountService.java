package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.dto.account.AccountAssetDTO;
import com.tcp.zkbscan.back.dto.account.AccountDTO;
import com.tcp.zkbscan.back.dto.zkbnb.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;

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

}
