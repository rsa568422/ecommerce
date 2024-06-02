package com.icodeap.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {

    private Integer id;
    private String code;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    private User user;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public Product() {
        this.setCode(UUID.randomUUID().toString());
    }
}
