package com.training.ecommercebackend.dto;

import com.training.ecommercebackend.model.Product;

public class AddToCartDto {
    private Long id;
    private Long productId;
    private Integer quantity;

    public AddToCartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
