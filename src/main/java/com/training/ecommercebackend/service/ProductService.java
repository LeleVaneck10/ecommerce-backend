package com.training.ecommercebackend.service;

import com.training.ecommercebackend.exceptions.ProductNotFoundExeption;
import com.training.ecommercebackend.model.Product;
import com.training.ecommercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product){

        return productRepository.save(product);
    }

    public Product findById(long id) {
        Product theProduct = null;
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            theProduct = product.get();
        }
        else {
            throw new ProductNotFoundExeption(" PRODUCT NOT FOUND ! : "+id);
        }
        return theProduct;
    }
}
