package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.OrderProductService;
import com.icodeap.ecommerce.application.service.OrderService;
import com.icodeap.ecommerce.application.service.UserService;
import com.icodeap.ecommerce.domain.Order;
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

    private final OrderProductService orderProductService;

    public ShoppingListController(OrderService orderService,
                                  UserService userService,
                                  OrderProductService orderProductService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderProductService = orderProductService;
    }

    @GetMapping
    public String showShoppingList(Model model, HttpSession httpSession) {
        var user = userService.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        var orders = orderService.getOrdersByUser(user);
        orders.forEach(this::getOrderProducts);
        model.addAttribute("orders", orders);
        model.addAttribute("id", user.getId());
        return "user/shoppinglist";
    }

    private void getOrderProducts(Order order) {
        var products = orderProductService.getOrderProductsByOrder(order);
        order.addOrdersProduct(products);
    }
}
