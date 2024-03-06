package com.training.ecommercebackend.repository;

import com.training.ecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoUserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
