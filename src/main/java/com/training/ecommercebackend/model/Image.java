package com.training.ecommercebackend.model;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String url;
    private String description;

    public Image() {
    }

    public Image(Long id, Product product, String url, String description) {
        this.id = id;
        this.product = product;
        this.url = url;
        this.description = description;
    }

    public Image(Product product, String url, String description) {
        this.product = product;
        this.url = url;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
