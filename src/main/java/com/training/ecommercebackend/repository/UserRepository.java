package com.training.ecommercebackend.repository;

import com.training.ecommercebackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
