package com.icodeap.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@ToString
@AllArgsConstructor
public class ItemCart {

    private Integer idProduct;
    private String nameProduct;
    private Integer quantity;
    private BigDecimal price;

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }
}
