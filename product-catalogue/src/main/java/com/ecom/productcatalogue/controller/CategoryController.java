package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.dto.CategoryDto;
import com.ecom.productcatalogue.service.CategoryService;
import com.ecom.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    final private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    public CategoryDto addCategory(@RequestBody CategoryDto newCategory){
        return categoryService.addCategory(newCategory);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(Long.parseLong(id));
    }

    @PatchMapping("/categories/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryDto CategoryDto) {
        return categoryService.updateCategory(CategoryDto);
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
