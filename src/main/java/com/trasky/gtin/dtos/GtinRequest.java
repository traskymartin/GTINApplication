package com.trasky.gtin.dtos;

import lombok.Data;

@Data
public class GtinRequest {
    private String gtin;
    private Long productId;
}
