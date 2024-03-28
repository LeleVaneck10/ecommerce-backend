package com.training.ecommercebackend.dto;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private Long totalCost;

    public CartDto() {
    }
    public List<CartItemDto> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
    public Long getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }
}
