package com.trasky.gtin.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BatchRequest {
    private Long productId;
    private Integer mrp;
    private Integer sp;
    private Integer purchasePrice;
    private Integer availableQuantity;
    private LocalDate inwardedOn;
}
