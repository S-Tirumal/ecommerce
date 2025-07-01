package com.ecom.productcatalogue.service;

import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    ProductDto getProductById(Long id);
}
