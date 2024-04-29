package com.training.ecommercebackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdressDto {

    private Long id;

    private String city;

    private String street;

    private String country;

    private UserDto user ;
}
