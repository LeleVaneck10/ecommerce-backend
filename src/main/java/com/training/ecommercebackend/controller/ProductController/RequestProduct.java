package com.training.ecommercebackend.controller.ProductController;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class RequestProduct {
    private String name;
    private String desription;
    private Long category;
    private BigDecimal price;
    private MultipartFile file;

    public RequestProduct() {
    }

    public RequestProduct(String name, String desription, Long category, BigDecimal price, MultipartFile file) {
        this.name = name;
        this.desription = desription;
        this.category = category;
        this.price = price;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
