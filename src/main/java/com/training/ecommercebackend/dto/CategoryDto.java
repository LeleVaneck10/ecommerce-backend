package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {

    private long id;

    private String name;

    private List<ProductDto> productList;
}
