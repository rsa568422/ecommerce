package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.StockService;
import com.icodeap.ecommerce.application.service.ValidateStock;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/products/stock")
public class StockController {

    private final StockService stockService;

    private final ValidateStock validateStock;

    public StockController(StockService stockService, ValidateStock validateStock) {
        this.stockService = stockService;
        this.validateStock = validateStock;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        var product = new Product();
        product.setId(id);
        var stocks = stockService.getStockByProduct(product);
        model.addAttribute("stocks", stocks);
        model.addAttribute("idproduct", id);
        return "admin/stock/show";
    }

    @GetMapping("/create-unit-product/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("idproduct", id);
        return "admin/stock/create";
    }

    @PostMapping("/save-unit-product")
    public String save(Stock stock, @RequestParam("idproduct") Integer idproduct) {
        var product = new Product();
        product.setId(idproduct);
        stock.setProduct(product);
        stock.setDateCreated(LocalDateTime.now());
        stock.setDescription("inventario");
        stockService.saveStock(validateStock.calculateBalance(stock));
        return "redirect:/admin/products/show";
    }
}
