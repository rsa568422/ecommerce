package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Slf4j
@Controller
@RequestMapping("/user/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProductCart(@RequestParam Integer quantity, @RequestParam Integer idProduct,
                                 @RequestParam String nameProduct, @RequestParam BigDecimal price) {
        cartService.addItemCart(quantity, idProduct, nameProduct, price);
        showCart();
        return "redirect:/home";
    }

    @GetMapping("/get-cart")
    public String getCart(Model model, HttpSession httpSession) {
        showCart();
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("id", httpSession.getAttribute("iduser"));
        return "user/cart/cart";
    }

    @GetMapping("/delete-item-cart/{id}")
    public String deleteItemCart(@PathVariable Integer id) {
        cartService.removeItemCart(id);
        return "redirect:/user/cart/get-cart";
    }

    private void showCart() {
        cartService.getItemCarts().forEach(itemCart -> log.info("Item cart: = {}", itemCart));
    }
}
