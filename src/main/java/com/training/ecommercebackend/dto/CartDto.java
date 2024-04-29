package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {

    private Long id;


    private List<CartItemDto> cartItemsDto;


    private BigDecimal totalPrice;


}
