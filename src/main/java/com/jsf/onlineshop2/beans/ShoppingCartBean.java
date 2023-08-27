package com.jsf.onlineshop2.beans;

import com.jsf.onlineshop2.cart.Item;
import com.jsf.onlineshop2.models.Product;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@SessionScoped
public class ShoppingCartBean implements Serializable {

    private List<Item> cart = new ArrayList<>();
    private int cartSize;
    private String item = "item";
    private double finalTotalPrice;

    public double getFinalTotalPrice() {
        finalTotalPrice = 0.0;
        for (Item item : cart) {
            finalTotalPrice += (item.getQuantity() * item.getProduct().getProductPrice());
        }
        return finalTotalPrice;
    }

    public int getCartSize() {
        cartSize = cart.size();
        return cartSize;
    }

    public String getItem() {
        if (cartSize > 1) {
            item = "items";
        }
        return item;
    }

    public String addToCart(Product product) {
        if (!cart.isEmpty()) {
            for (Item item : cart) {
                if (item.getProduct().getProductId() == product.getProductId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    return "viewCart";
                }
            }
        }
        Item i = new Item();
        i.setQuantity(1);
        i.setProduct(product);
        cart.add(i);

        return "viewCart";
    }

    public void removeCart(Item cartItem) {
        for (Item item : cart) {
            if (item.equals(cartItem)) {
                cart.remove(cartItem);
                break;
            }
        }
    }

    public void updateCart() {
    }
}
