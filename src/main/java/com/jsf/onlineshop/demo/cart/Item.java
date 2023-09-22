package com.jsf.onlineshop.demo.cart;

import com.jsf.onlineshop.demo.models.Product;
import lombok.Data;

@Data
public class Item {
    private Product product;
    private int quantity;
    private double totalPrice;

    public double getTotalPrice() {
        totalPrice = quantity * product.getProductPrice();
        return totalPrice;
    }
}
