package com.trasky.gtin.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private LocalDate createdOn;
}
