package com.icodeap.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
public class Stock {

    private Integer id;
    private Integer unitIn;
    private Integer unitOut;
    private Integer balance;
    private String description;

    private Product product;

    private LocalDateTime dateCreated;
}
