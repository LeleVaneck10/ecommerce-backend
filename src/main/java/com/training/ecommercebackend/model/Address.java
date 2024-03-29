package com.training.ecommercebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private String street;
    private String city;
    private String zipCode;
    private String country;

    public Address() {
    }

    public Address(Long id, User user, String street, String city, String zipCode, String country) {
        this.id = id;
        this.user = user;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(User user, String street, String city, String zipCode, String country) {
        this.user = user;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
