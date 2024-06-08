package com.icodeap.ecommerce.infrastructure.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class OrderProductPK {

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
}
