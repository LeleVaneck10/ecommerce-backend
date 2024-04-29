package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CartItemDto {

    private long id;


    private CartDto cart;


    private ProductDto product;



}
