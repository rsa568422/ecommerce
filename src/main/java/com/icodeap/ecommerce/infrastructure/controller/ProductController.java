package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String create() {
        return "admin/products/create";
    }

    @PostMapping("/save-product")
    public String saveProduct(Product product, @RequestParam("img") MultipartFile multipartFile,
                              HttpSession httpSession) throws IOException {
        log.info("[SAVE] Producto: {}", product);
        productService.saveProduct(product, multipartFile, httpSession);
        return "redirect:/admin";
    }

    @GetMapping("/show")
    public String showProduct(Model model, HttpSession httpSession) {
        log.info("id user desde la variable de sesión: {}", httpSession.getAttribute("iduser"));
        var user = new User();
        user.setId(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        var products = productService.getProductsByUser(user);
        model.addAttribute("products", products);
        return "admin/products/show";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        var product = productService.getProductById(id);
        log.info("[EDIT] Producto: {}", product);
        model.addAttribute("product", product);
        return "admin/products/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products/show";
    }
}
