package com.tcp.zkbscan.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDTO<T> {

    private T data;
    private PageInfoDTO pagination;
}
