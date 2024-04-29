package com.training.ecommercebackend.model;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


@Data
@NoArgsConstructor
public class Cart {

    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

}
