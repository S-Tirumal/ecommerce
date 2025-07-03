package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {


    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        // This test will check if the application context loads successfully
        // and the ProductController bean is created.
        ProductDto product = ProductDto.builder()
                .id(10L).title("Test Product")
                .description("This is a test product.")
                .amount(99.99)
                .imageUrl("http://example.com/image.jpg")
                .build();
        when(productService.getAllProducts()).thenReturn(List.of(product));

        List<ProductDto> products = productController.getAllProducts();
        assert !products.isEmpty();
        assert products.get(0).getId().equals(10L);
        assert products.get(0).getTitle().equals("Test Product");
        assert products.get(0).getDescription().equals("This is a test product.");
        assert products.get(0).getAmount() == 99.99;
        assert products.get(0).getImageUrl().equals("http://example.com/image.jpg");
    }

    @Test
    void testAddProduct() {
        ProductDto newProduct = ProductDto.builder()
                .title("New Product")
                .description("This is a new product.")
                .amount(49.99)
                .imageUrl("http://example.com/new-image.jpg")
                .build();

        when(productService.addProduct(newProduct)).thenReturn(newProduct);

        ProductDto addedProduct = productController.addProduct(newProduct);
        assert addedProduct.getTitle().equals("New Product");
        assert addedProduct.getDescription().equals("This is a new product.");
        assert addedProduct.getAmount() == 49.99;
        assert addedProduct.getImageUrl().equals("http://example.com/new-image.jpg");
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        productController.deleteProduct(String.valueOf(productId));
    }

    @Test
    void testUpdateProduct() {
        ProductDto updatedProduct = ProductDto.builder()
                .id(1L)
                .title("Updated Product")
                .description("This is an updated product.")
                .amount(59.99)
                .imageUrl("http://example.com/updated-image.jpg")
                .build();

        when(productService.updateProduct(updatedProduct)).thenReturn(updatedProduct);

        ProductDto result = productController.updateProduct(updatedProduct);
        assert result.getId().equals(1L);
        assert result.getTitle().equals("Updated Product");
        assert result.getDescription().equals("This is an updated product.");
        assert result.getAmount() == 59.99;
        assert result.getImageUrl().equals("http://example.com/updated-image.jpg");
    }
    @Test
    void testGetProductById() {
        Long productId = 1L;
        ProductDto product = ProductDto.builder()
                .id(productId)
                .title("Product by ID")
                .description("This is a product fetched by ID.")
                .amount(29.99)
                .imageUrl("http://example.com/product-by-id.jpg")
                .build();

        when(productService.getProductById(productId)).thenReturn(product);

        ProductDto fetchedProduct = productController.getProductById(productId);
        assert fetchedProduct.getId().equals(productId);
        assert fetchedProduct.getTitle().equals("Product by ID");
        assert fetchedProduct.getDescription().equals("This is a product fetched by ID.");
        assert fetchedProduct.getAmount() == 29.99;
        assert fetchedProduct.getImageUrl().equals("http://example.com/product-by-id.jpg");
    }

}
