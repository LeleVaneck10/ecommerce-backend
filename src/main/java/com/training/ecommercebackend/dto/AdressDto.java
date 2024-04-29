package com.training.ecommercebackend.dto;

import com.training.ecommercebackend.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdressDto {

    private Long id;

    private String city;

    private String street;

    private String country;

    private UserDto userDto ;



    /*********************************** add mapping  entity object from dto object ********************************/

    public static AdressDto  fromEntity(Address address){

        if(address == null){
            return  null;
            //todo throw an exception
        }

        AdressDto adressDto = new AdressDto();
        adressDto.setId(address.getId());
        adressDto.setCity(address.getCity());
        adressDto.setStreet(address.getStreet());
        adressDto.setCountry(address.getCountry());

        return adressDto;
    }

    public static Address toEntity(AdressDto adressDto){

        if(adressDto == null){
            return null;
            //todo throw an exception
        }

        Address address = new Address();
        address.setId(adressDto.getId());
        address.setCity(adressDto.getCity());
        address.setStreet(adressDto.getStreet());
        address.setCountry(adressDto.getCountry());

        return  address;
    }


}
