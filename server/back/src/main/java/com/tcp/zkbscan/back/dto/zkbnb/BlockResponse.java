package com.tcp.zkbscan.back.dto.zkbnb;

import lombok.Data;

import java.util.List;

@Data
public class BlockResponse {
    private Integer code;
    private String message;
    private Integer total;
    private List<Block> blocks;
}