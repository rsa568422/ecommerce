package com.icodeap.ecommerce.infrastructure.adapter;

import com.icodeap.ecommerce.application.repository.StockRepository;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.Stock;
import com.icodeap.ecommerce.infrastructure.mapper.ProductMapper;
import com.icodeap.ecommerce.infrastructure.mapper.StockMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepositoryImpl implements StockRepository {

    private final StockCrudRepository stockCrudRepository;

    private final StockMapper stockMapper;

    private final ProductMapper productMapper;

    public StockRepositoryImpl(StockCrudRepository stockCrudRepository, StockMapper stockMapper, ProductMapper productMapper) {
        this.stockCrudRepository = stockCrudRepository;
        this.stockMapper = stockMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockMapper.toStock(stockCrudRepository.save(stockMapper.toStockEntity(stock)));
    }

    @Override
    public Iterable<Stock> getStockByProduct(Product product) {
        return stockMapper.toStocks(stockCrudRepository.findByProduct(productMapper.toProductEntity(product)));
    }
}
