package com.training.ecommercebackend.service;

import com.training.ecommercebackend.controller.CategoryController.RequestCategory;
import com.training.ecommercebackend.controller.CategoryController.ResponseCategory;
import com.training.ecommercebackend.exceptions.CategoryNotFoundException;
import com.training.ecommercebackend.model.Category;
import com.training.ecommercebackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category categorySave(Category category) {
        return  categoryRepository.save(category);
    }

    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }


    public  void deleteCategory(Long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()){
            categoryRepository.delete(category.get());
        }else {
            throw  new CategoryNotFoundException(" category not found ! couldn't delete");
        }
    }


    public ResponseCategory updateCategory(RequestCategory request){

        ResponseCategory theCategory = new ResponseCategory();

        Optional<Category> category = categoryRepository.findById(request.getId());

        if(category.isPresent()){

            category.get().setName(request.getName());

            setResponseCategory(theCategory, category.get());

            categoryRepository.save(category.get());

        }else {
            throw new CategoryNotFoundException("category not found !!");
        }

        return theCategory;
    }

    public void setResponseCategory(ResponseCategory theCategory, Category category) {
        theCategory.setCategoryId(category.getId());
        theCategory.setName(category.getName());
    }


}
