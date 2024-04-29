package com.training.ecommercebackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class UserDto {

    private Integer id;


    private String firstName;


    private String lastName;


    private String email;


    private String password;


    private AdressDto addressDto;


    private List<OrdersDto> orders;
}
