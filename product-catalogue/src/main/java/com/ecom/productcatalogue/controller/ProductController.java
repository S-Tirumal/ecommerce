package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.service.ProductService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/api")
public class ProductController {

    final private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto newProduct){
        return productService.addProduct(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(Long.parseLong(id));
    }

    @PatchMapping("/products/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
