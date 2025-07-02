package com.ecom.productcatalogue.service;

import com.ecom.productcatalogue.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto addCategory(CategoryDto newCategory);
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);

}
