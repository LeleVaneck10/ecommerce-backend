package com.training.ecommercebackend.controller;

import com.training.ecommercebackend.exeptions.ProductNotFoundExeption;
import com.training.ecommercebackend.model.Category;
import com.training.ecommercebackend.model.Product;
import com.training.ecommercebackend.security.userauth.AuthService;
import com.training.ecommercebackend.service.CategoryService;
import com.training.ecommercebackend.service.ProductService;
import com.training.ecommercebackend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;


    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/saveProduct")
    public Product saveProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("category") Long categoryId,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("file") MultipartFile imageFile) throws IOException
    {
        String imagePath = null;

        Product product = new Product();

        Optional<Category> category ;

        category = categoryService.findById(categoryId);


        if(category.isPresent()){

            if (imageFile != null && !imageFile.isEmpty()) {
                imagePath = ImageUtil.saveImage(imageFile);
            }

            product.setName(name);
            product.setDescription(description);
            product.setCategory(category.get());
            product.setPrice(price);
            product.setImagePath(imagePath);

        }

        Category theCategory = category.get();
        theCategory.addProduct(product);

        return  productService.saveProduct(product);
    }

    @GetMapping("/findProduct/{id}")
    public Product findProductById(@PathVariable Long id){

        Optional<Product> product = Optional.ofNullable(productService.findById(id));
        Product theProduct = product.get();

        if(product.isEmpty()){
            throw  new ProductNotFoundExeption("NOT FOUND PRODUCT : "+theProduct);
        }
        return theProduct;
    }


}
