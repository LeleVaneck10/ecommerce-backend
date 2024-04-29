package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class OrdersDto {

    private long id;

    private List<OrderItemDto> orderItemDto;

    private BigDecimal total;

    private UserDto userDto;
}
