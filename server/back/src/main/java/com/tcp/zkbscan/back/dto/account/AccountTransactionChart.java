package com.tcp.zkbscan.back.dto.account;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AccountTransactionChart {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date;

    private Integer transactionCount;
}
