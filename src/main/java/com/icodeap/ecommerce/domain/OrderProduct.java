package com.icodeap.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
public class OrderProduct {

    private Product product;
    private Integer quantity;
    private Order order;

    public BigDecimal getTotalPrice() {
        return this.product.getPrice()
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
