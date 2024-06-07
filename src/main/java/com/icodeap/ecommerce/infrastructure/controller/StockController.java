package com.icodeap.ecommerce.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products/stock")
public class StockController {

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        return "admin/stock/show";
    }
}
