package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.model.Product;
import com.ecom.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product newProduct){
        return newProduct;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id){

    }
}
