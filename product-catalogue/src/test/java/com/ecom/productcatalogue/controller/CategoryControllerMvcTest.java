package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.dto.CategoryDto;
import com.ecom.productcatalogue.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCategorys_RanSuccessfully() throws Exception {
        CategoryDto category = CategoryDto.builder()
                .id(10L).title("Test Category")
                .description("This is a test category.")
                .build();
        when(categoryService.getAllCategories()).thenReturn(List.of(category));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(10L))
                .andExpect(jsonPath("$[0].title").value("Test Category"))
                .andExpect(jsonPath("$[0].description").value("This is a test category."));

    }

    @Test
    @DisplayName("Test Add Category")
    void testAddCategory() throws Exception {
        CategoryDto newCategory = CategoryDto.builder()
                .title("New Category")
                .description("This is a new category.")
                .build();

        when(categoryService.addCategory(any(CategoryDto.class))).thenReturn(newCategory);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCategory)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Category"))
                .andExpect(jsonPath("$.description").value("This is a new category."));

        verify(categoryService).addCategory(any(CategoryDto.class));
    }
    @Test
    @DisplayName("Test Get Category By ID")
    void testGetCategoryById() throws Exception {
        CategoryDto category = CategoryDto.builder()
                .id(1L)
                .title("Category 1")
                .description("Description of Category 1")
                .build();

        when(categoryService.getCategoryById(1L)).thenReturn(category);

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Category 1"))
                .andExpect(jsonPath("$.description").value("Description of Category 1"));
    }
    @Test
    @DisplayName("Test Update Category")
    void testUpdateCategory() throws Exception {
        CategoryDto updatedCategory = CategoryDto.builder()
                .id(1L)
                .title("Updated Category")
                .description("This is an updated category.")
                .build();

        when(categoryService.updateCategory(any(CategoryDto.class))).thenReturn(updatedCategory);

        mockMvc.perform(patch("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCategory)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(updatedCategory)))

                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Category"))
                .andExpect(jsonPath("$.description").value("This is an updated category."));

        verify(categoryService).updateCategory(any(CategoryDto.class));
    }
    @Test
    @DisplayName("Test Delete Category")
    void testDeleteCategory() throws Exception {
        Long categoryId = 1L;

        mockMvc.perform(delete("/api/categories/" + categoryId))
                .andExpect(status().isOk());

        verify(categoryService).deleteCategory(categoryId);
    }
}
