package com.icodeap.ecommerce.application.repository;

import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.Stock;

public interface StockRepository {

    Stock saveStock(Stock stock);

    Iterable<Stock> getStockByProduct(Product product);
}
