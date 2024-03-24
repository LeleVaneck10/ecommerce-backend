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
    public ResponseEntity<String> saveProduct(
//                              @RequestParam("name") String name,
//                              @RequestParam("description") String description,
//                              @RequestParam("category") Long categoryId,
//                              @RequestParam("price") BigDecimal price,
//                              @RequestParam("file") MultipartFile imageFile
                                @RequestBody RequestProduct request
                            ) throws IOException
    {
        String imagePath = null;
        MultipartFile imageFile = request.getFile();

        Product product = new Product();

        Optional<Category> category ;

        category = categoryService.findById(request.getCategory());


        if(category.isPresent()){

            if ((imageFile != null) && (!imageFile.isEmpty())) {

                imagePath = ImageUtil.saveImage(imageFile);
            }

            product.setName(request.getName());
            product.setDescription(request.getDesription());
            product.setCategory(category.get());
            product.setPrice(request.getPrice());
            product.setImagePath(imagePath);

        }

        category.get().addProduct(product);

        productService.saveProduct(product);

        return  ResponseEntity.status(HttpStatus.CREATED).body("product inserted successfully");
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<ResponseProduct> findProductById(@PathVariable Long id){

        return ResponseEntity.ok(productService.findById(id));

    }



}
