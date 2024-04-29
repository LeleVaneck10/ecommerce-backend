package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;


    private String name;


    private String description;


    private BigDecimal price;


    private CategoryDto categoryDto;


    private String imagePath;


    private CartDto cartDto;
}
