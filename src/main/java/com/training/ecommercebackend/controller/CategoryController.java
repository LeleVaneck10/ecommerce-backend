package com.training.ecommercebackend.controller;

import com.training.ecommercebackend.model.Category;
import com.training.ecommercebackend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/saveCategory")
    public Category saveCategory(@RequestBody Category category){

        return categoryService.categorySave(category);
    }
}
