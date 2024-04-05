package com.training.ecommercebackend.controller.CategoryController;

import com.training.ecommercebackend.model.Category;
import com.training.ecommercebackend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){

        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("the category with id :"+id+" well deleted !");

    }

    @PostMapping("/updateCategory")
    public ResponseEntity<ResponseCategory>  updateCategory(@RequestBody RequestCategory request){

        ResponseCategory response = categoryService.updateCategory(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
