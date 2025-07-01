package com.ecom.productcatalogue.service;

import com.ecom.productcatalogue.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
}
