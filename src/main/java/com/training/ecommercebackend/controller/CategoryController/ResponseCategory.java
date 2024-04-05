package com.training.ecommercebackend.controller.CategoryController;

public class ResponseCategory {

    private String  name;
    private Long categoryId;

    public ResponseCategory() {
    }

    public ResponseCategory(String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
