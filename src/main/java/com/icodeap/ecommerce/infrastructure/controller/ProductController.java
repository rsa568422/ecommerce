package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @GetMapping("/create")
    public String create() {
        return "admin/products/create";
    }

    @PostMapping("/save-product")
    public String saveProduct(Product product) {
        log.info("Producto: {}", product.toString());
        return "admin/products/create";
    }
}
