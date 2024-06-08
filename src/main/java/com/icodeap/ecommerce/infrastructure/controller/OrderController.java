package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.CartService;
import com.icodeap.ecommerce.application.service.OrderProductService;
import com.icodeap.ecommerce.application.service.OrderService;
import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.application.service.StockService;
import com.icodeap.ecommerce.application.service.UserService;
import com.icodeap.ecommerce.application.service.ValidateStock;
import com.icodeap.ecommerce.domain.ItemCart;
import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.domain.OrderProduct;
import com.icodeap.ecommerce.domain.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/user/order")
public class OrderController {

    private final CartService cartService;

    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    private final OrderProductService orderProductService;

    private final StockService stockService;

    private final ValidateStock validateStock;

    public OrderController(CartService cartService, UserService userService, ProductService productService,
                           OrderService orderService, OrderProductService orderProductService,
                           StockService stockService, ValidateStock validateStock) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.stockService = stockService;
        this.validateStock = validateStock;
    }

    @GetMapping("/summary-order")
    public String showSummaryOrder(Model model) {
        model.addAttribute("user", userService.findById(1));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        return "user/sumaryorder";
    }

    @GetMapping("/create-order")
    public String createOrder(Model model) {
        log.info("create order...");
        final var user = userService.findById(1);
        final var order = new Order();
        order.setUser(user);
        order.setDateCreated(LocalDateTime.now());
        final var newOrder = orderService.createOrder(order);
        cartService.getItemCarts()
                .stream()
                .map(itemCart -> toOrderProduct(itemCart, newOrder))
                .forEach(orderProduct -> {
                    orderProductService.create(orderProduct);
                    final var stock = new Stock();
                    stock.setProduct(orderProduct.getProduct());
                    stock.setDescription("venta");
                    stock.setUnitOut(orderProduct.getQuantity());
                    stock.setDateCreated(LocalDateTime.now());
                    stockService.saveStock(validateStock.calculateBalance(stock));
                });
        cartService.removeAllItemsCart();
        return "redirect:/";
    }

    private OrderProduct toOrderProduct(ItemCart itemCart, Order order) {
        return new OrderProduct(productService.getProductById(itemCart.getIdProduct()), itemCart.getQuantity(), order);
    }
}
