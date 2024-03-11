package com.training.ecommercebackend.repository;

import com.training.ecommercebackend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
