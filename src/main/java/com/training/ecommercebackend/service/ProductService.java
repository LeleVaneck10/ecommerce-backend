package com.training.ecommercebackend.service;

import com.training.ecommercebackend.controller.ProductController.ResponseProduct;
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

    public ResponseProduct findById(long id) {

        ResponseProduct theProduct = new ResponseProduct();
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            theProduct.setName(product.get().getName());
            theProduct.setDescription(product.get().getDescription());
            theProduct.setPrice(product.get().getPrice());
            theProduct.setImagePath(product.get().getImagePath());
            theProduct.setCategory(product.get().getCategory().getName());

        }
        else {
            throw new ProductNotFoundExeption(" PRODUCT witch the "+id+" NOT FOUND !");
        }

        return theProduct;
    }
}
