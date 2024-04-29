package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private  long id;

    private ProductDto productDto;

    private  int quantity;

    private BigDecimal price;

    private OrdersDto ordersDto;
}
