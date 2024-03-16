package com.training.ecommercebackend.controller;

import com.training.ecommercebackend.model.Product;
import com.training.ecommercebackend.service.ProductService;
import com.training.ecommercebackend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/saveProduct")
    public Product saveProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("file") MultipartFile imageFile) throws IOException
    {
        String imagePath = null;

        Product product = new Product();

        if (imageFile != null && !imageFile.isEmpty()) {
            imagePath = ImageUtil.saveImage(imageFile);
        }

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImagePath(imagePath);

        return  productService.saveProduct(product);
    }


}
