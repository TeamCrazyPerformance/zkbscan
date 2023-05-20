package com.tcp.zkbscan.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfoDTO {

    private Integer page;
    private Integer size;
    private Integer totalElements;
    private Integer totalPages;
}
