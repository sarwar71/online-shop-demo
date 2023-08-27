package com.jsf.onlineshop2.cart;

import com.jsf.onlineshop2.models.Product;
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
