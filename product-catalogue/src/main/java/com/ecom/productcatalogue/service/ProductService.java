package com.ecom.productcatalogue.service;

import com.ecom.productcatalogue.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto addProduct(ProductDto newProduct);
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Long id);

}
