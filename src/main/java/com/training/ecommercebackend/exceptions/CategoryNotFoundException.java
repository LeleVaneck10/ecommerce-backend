package com.training.ecommercebackend.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(){}
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
