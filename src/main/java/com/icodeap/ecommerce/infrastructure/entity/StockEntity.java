package com.icodeap.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer unitIn;

    private Integer unitOut;

    private Integer balance;

    private String description;

    private LocalDateTime dateCreated;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductEntity product;
}
