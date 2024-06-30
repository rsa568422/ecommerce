package com.icodeap.ecommerce.infrastructure.adapter;

import com.icodeap.ecommerce.infrastructure.entity.ProductEntity;
import com.icodeap.ecommerce.infrastructure.entity.StockEntity;
import org.springframework.data.repository.CrudRepository;

public interface StockCrudRepository extends CrudRepository<StockEntity, Integer> {

    Iterable<StockEntity> findByProduct(ProductEntity product);
}
