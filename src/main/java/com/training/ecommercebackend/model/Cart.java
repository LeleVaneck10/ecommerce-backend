package com.training.ecommercebackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer quantity;

    public Cart() {
    }

    public Cart(Long id, Date createdDate, Product product, User user, Integer quantity) {
        this.id = id;
        this.createdDate = createdDate;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public Cart(Date createdDate, Product product, User user, Integer quantity) {
        this.createdDate = createdDate;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
