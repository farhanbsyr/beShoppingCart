package com.storefarhan.dreams_shop.request;

import java.math.BigDecimal;

import com.storefarhan.dreams_shop.model.Category;

import lombok.Data;

@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
