package com.tcp.zkbscan.back.dto.zkbnb;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CurrentHeightResponse {

    private Integer code;
    private String message;
    private BigInteger height;
}
