package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts_RanSuccessfully() throws Exception {
        ProductDto product = ProductDto.builder()
                .id(10L).title("Test Product")
                .description("This is a test product.")
                .amount(99.99)
                .imageUrl("http://example.com/image.jpg")
                .build();
        when(productService.getAllProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(10L))
                .andExpect(jsonPath("$[0].title").value("Test Product"))
                .andExpect(jsonPath("$[0].description").value("This is a test product."))
                .andExpect(jsonPath("$[0].amount").value(99.99))
                .andExpect(jsonPath("$[0].imageUrl").value("http://example.com/image.jpg"));

    }

    @Test
    @DisplayName("Test Add Product")
    void testAddProduct() throws Exception {
        ProductDto newProduct = ProductDto.builder()
                .title("New Product")
                .description("This is a new product.")
                .amount(49.99)
                .imageUrl("http://example.com/new-image.jpg")
                .build();

        when(productService.addProduct(any(ProductDto.class))).thenReturn(newProduct);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Product"))
                .andExpect(jsonPath("$.description").value("This is a new product."))
                .andExpect(jsonPath("$.amount").value(49.99))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/new-image.jpg"));

        verify(productService).addProduct(any(ProductDto.class));
    }
    @Test
    @DisplayName("Test Get Product By ID")
    void testGetProductById() throws Exception {
        ProductDto product = ProductDto.builder()
                .id(1L)
                .title("Product 1")
                .description("Description of Product 1")
                .amount(19.99)
                .imageUrl("http://example.com/product1.jpg")
                .build();

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Product 1"))
                .andExpect(jsonPath("$.description").value("Description of Product 1"))
                .andExpect(jsonPath("$.amount").value(19.99))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/product1.jpg"));
    }
    @Test
    @DisplayName("Test Update Product")
    void testUpdateProduct() throws Exception {
        ProductDto updatedProduct = ProductDto.builder()
                .id(1L)
                .title("Updated Product")
                .description("This is an updated product.")
                .amount(59.99)
                .imageUrl("http://example.com/updated-image.jpg")
                .build();

        when(productService.updateProduct(any(ProductDto.class))).thenReturn(updatedProduct);

        mockMvc.perform(patch("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(updatedProduct)))

                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Product"))
                .andExpect(jsonPath("$.description").value("This is an updated product."))
                .andExpect(jsonPath("$.amount").value(59.99))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/updated-image.jpg"));

        verify(productService).updateProduct(any(ProductDto.class));
    }
    @Test
    @DisplayName("Test Delete Product")
    void testDeleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc.perform(delete("/api/products/" + productId))
                .andExpect(status().isOk());

        verify(productService).deleteProduct(productId);
    }
}
