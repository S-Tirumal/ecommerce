package com.ecom.productcatalogue.service.impl;

import com.ecom.productcatalogue.dto.CategoryDto;
import com.ecom.productcatalogue.model.Category;
import com.ecom.productcatalogue.repository.CategoryRepository;
import com.ecom.productcatalogue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(this::fromCategory).toList();
    }

    public CategoryDto getCategoryById(Long id) {
        return CategoryDto.builder().build();
    }

    @Override
    public CategoryDto addCategory(CategoryDto newCategory) {
        Category category = categoryRepository.save(toCategory(newCategory));
        return fromCategory(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(toCategory(categoryDto));
        return fromCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto fromCategory(Category category) {
        return CategoryDto.builder()
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    private Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
