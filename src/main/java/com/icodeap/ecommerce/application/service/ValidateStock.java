package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.Stock;
import org.apache.commons.collections4.IteratorUtils;

public class ValidateStock {

    private final StockService stockService;

    public ValidateStock(StockService stockService) {
        this.stockService = stockService;
    }

    public Stock calculateBalance(Stock stock) {
        if (stock.getUnitIn() != 0) {
            if (existBalance(stock.getProduct())) {
                var stocks = IteratorUtils.toList(stockService.getStockByProduct(stock.getProduct()).iterator());
                var balance = stocks.get(stocks.size() - 1).getBalance();
                stock.setBalance(balance + stock.getUnitIn());
            } else {
                stock.setBalance(stock.getUnitIn());
            }
        } else {
            var stocks = IteratorUtils.toList(stockService.getStockByProduct(stock.getProduct()).iterator());
            var balance = stocks.get(stocks.size() - 1).getBalance();
            stock.setBalance(balance - stock.getUnitOut());
        }
        return stock;
    }

    private boolean existBalance(Product product) {
        var stocks = stockService.getStockByProduct(product);
        return IteratorUtils.size(stocks.iterator()) > 0;
    }
}
