package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.dto.CategoryDto;
import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.model.Category;
import com.ecom.productcatalogue.service.CategoryService;
import com.ecom.productcatalogue.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testGetAllCategories() {
        // This test will check if the application context loads successfully
        // and the CategoryController bean is created.
        CategoryDto category = CategoryDto.builder()
                .id(1L)
                .title("Test Category")
                .description("This is a test category.")
                .build();
        when(categoryService.getAllCategories()).thenReturn(List.of(category));

        List<CategoryDto> categories = categoryController.getAllCategories();
        assert !categories.isEmpty();
        assert categories.get(0).getId().equals(1L);
        assert categories.get(0).getTitle().equals("Test Category");
        assert categories.get(0).getDescription().equals("This is a test category.");
    }

    @Test
    void testAddCategory() {
        CategoryDto newCategory = CategoryDto.builder()
                .title("New Category")
                .description("This is a new category.")
                .build();

        when(categoryService.addCategory(newCategory)).thenReturn(newCategory);

        CategoryDto addedCategory = categoryController.addCategory(newCategory);
        assert addedCategory.getTitle().equals("New Category");
        assert addedCategory.getDescription().equals("This is a new category.");
    }

    @Test
    void testGetCategoryById() {
        CategoryDto category = CategoryDto.builder()
                .id(1L)
                .title("Test Category")
                .description("This is a test category.")
                .build();
        when(categoryService.getCategoryById(1L)).thenReturn(category);

        CategoryDto foundCategory = categoryController.getCategoryById(1L);
        assert foundCategory != null;
        assert foundCategory.getId().equals(1L);
        assert foundCategory.getTitle().equals("Test Category");
        assert foundCategory.getDescription().equals("This is a test category.");
    }
    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        categoryController.deleteCategory(String.valueOf(categoryId));
    }

    @Test
    void testUpdateCategory() {
        CategoryDto updatedCategory = CategoryDto.builder()
                .id(1L)
                .title("Updated Category")
                .description("This is an updated category.")
                .build();

        when(categoryService.updateCategory(updatedCategory)).thenReturn(updatedCategory);

        CategoryDto result = categoryController.updateCategory(updatedCategory);
        assert result.getId().equals(1L);
        assert result.getTitle().equals("Updated Category");
        assert result.getDescription().equals("This is an updated category.");
    }

}
