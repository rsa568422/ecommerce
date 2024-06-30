package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.OrderService;
import com.icodeap.ecommerce.application.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart/shopping")
public class ShoppingListController {

    private final OrderService orderService;

    private final UserService userService;

    public ShoppingListController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String showShoppingList(Model model, HttpSession httpSession) {
        var user = userService.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        return "user/shoppinglist";
    }
}
