package com.training.ecommercebackend.exeptions;

public class ProductNotFoundExeption extends RuntimeException{

    public ProductNotFoundExeption() {
    }

    public ProductNotFoundExeption(String message) {
        super(message);
    }

    public ProductNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
