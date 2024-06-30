package com.icodeap.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
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
