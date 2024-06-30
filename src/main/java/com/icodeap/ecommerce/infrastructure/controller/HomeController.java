package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.application.service.StockService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;

    private final StockService stockService;

    public HomeController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    @GetMapping
    public String home(Model model, HttpSession httpSession){
        model.addAttribute("products", productService.getProducts());
        var id = httpSession.getAttribute("userid");
        if (Objects.nonNull(id))
            model.addAttribute("id", id);
        return "home";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id, Model model, HttpSession httpSession) {
        var product = productService.getProductById(id);
        var stocks = IteratorUtils.toList(stockService.getStockByProduct(product).iterator());
        var lastBalance = stocks.get(stocks.size() - 1).getBalance();
        model.addAttribute("product", product);
        model.addAttribute("stock", lastBalance);
        var userid = httpSession.getAttribute("userid");
        if (Objects.nonNull(id))
            model.addAttribute("id", userid);
        return "user/productdetail";
    }
}
