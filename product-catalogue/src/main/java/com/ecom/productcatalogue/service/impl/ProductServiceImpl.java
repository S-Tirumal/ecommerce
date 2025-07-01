package com.ecom.productcatalogue.service.impl;

import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.model.Product;
import com.ecom.productcatalogue.repository.FakeStoreProductRepository;
import com.ecom.productcatalogue.repository.ProductRepository;
import com.ecom.productcatalogue.repository.dto.FakeStoreProductDto;
import com.ecom.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final FakeStoreProductRepository fakeStoreProductRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FakeStoreProductRepository fakeStoreProductRepository){
        this.productRepository = productRepository;
        this.fakeStoreProductRepository = fakeStoreProductRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(product -> ProductDto.builder()
                .id(product.getId())
                .title(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .amount(product.getPrice())
                .build()).toList();
    }

    public ProductDto getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductRepository.getProductById(id);
        return ProductDto.builder()
                .title(fakeStoreProductDto.getTitle())
                .amount(fakeStoreProductDto.getPrice())
                .description(fakeStoreProductDto.getDescription())
                .imageUrl(fakeStoreProductDto.getImage())
                .id(fakeStoreProductDto.getId())
                .build();
    }




}
