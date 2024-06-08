package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.ItemCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private final List<ItemCart> itemCarts;
    private final Map<Integer, ItemCart> itemCartMap;

    public CartService() {
        this.itemCarts = new ArrayList<>();
        this.itemCartMap = new HashMap<>();
    }

    public void addItemCart(Integer quantity, Integer id, String nameProduct, BigDecimal price) {
        var itemCart = new ItemCart(id, nameProduct, quantity, price);
        this.itemCartMap.put(itemCart.getIdProduct(), itemCart);
        fillList();
    }

    public BigDecimal getTotalCart() {
        return itemCarts.stream().map(ItemCart::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removeItemCart(Integer idProduct) {
        this.itemCartMap.remove(idProduct);
        fillList();
    }

    public void removeAllItemsCart() {
        this.itemCartMap.clear();
        this.itemCarts.clear();
    }

    private void fillList() {
        this.itemCarts.clear();
        this.itemCarts.addAll(this.itemCartMap.values());
    }
}
