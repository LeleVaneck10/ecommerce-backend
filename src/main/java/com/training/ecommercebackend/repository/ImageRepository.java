package com.training.ecommercebackend.repository;

import com.training.ecommercebackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
