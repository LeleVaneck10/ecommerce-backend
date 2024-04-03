package com.training.ecommercebackend.controller.ProductController;

import com.training.ecommercebackend.exceptions.ProductNotFoundExeption;
import com.training.ecommercebackend.model.Category;
import com.training.ecommercebackend.model.Product;
import com.training.ecommercebackend.service.CategoryService;
import com.training.ecommercebackend.service.ProductService;
import com.training.ecommercebackend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {

    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("category") Long categoryId,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("file") MultipartFile imageFile

                            ) throws IOException
    {

        productService.saveProduct(name,description,categoryId,price,imageFile);

        return  ResponseEntity.status(HttpStatus.CREATED).body("product inserted successfully");

    }


    @GetMapping("/findProduct/{id}")
    public ResponseEntity<ResponseProduct> findProductById(@PathVariable Long id){

        return ResponseEntity.ok(productService.findById(id));

    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws IOException {

        productService.deleteProductById(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(" product with "+id+" successfully DELETED ! ");

    }
    @GetMapping("/findAllProducts")
    public  ResponseEntity<List<ResponseProduct>> updateProduct(){

        List<ResponseProduct> products = productService.getAllProduct();


        return ResponseEntity.status(HttpStatus.FOUND).body(products);
    }



}
