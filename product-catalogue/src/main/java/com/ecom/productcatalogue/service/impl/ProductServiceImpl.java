package com.ecom.productcatalogue.service.impl;

import com.ecom.productcatalogue.config.TokenHolder;
import com.ecom.productcatalogue.dto.ProductDto;
import com.ecom.productcatalogue.model.Product;
import com.ecom.productcatalogue.repository.FakeStoreProductRepository;
import com.ecom.productcatalogue.repository.ProductRepository;
import com.ecom.productcatalogue.repository.dto.FakeStoreProductDto;
import com.ecom.productcatalogue.service.ProductService;
import com.ecom.productcatalogue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final FakeStoreProductRepository fakeStoreProductRepository;

    private final UserService userService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FakeStoreProductRepository fakeStoreProductRepository, UserService userService){
        this.productRepository = productRepository;
        this.fakeStoreProductRepository = fakeStoreProductRepository;
        this.userService = userService;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        TokenHolder.getToken();
        boolean isValid = userService.validateToken(TokenHolder.getToken());
        if(!isValid){
            throw new RuntimeException("Invalid token");
        }

        List<Product> list = productRepository.findAll();
        return list.stream().map(this::fromProduct).toList();
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

    @Override
    public ProductDto addProduct(ProductDto newProduct) {
        Product product = productRepository.save(toProduct(newProduct));
        return fromProduct(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.save(toProduct(productDto));
        return fromProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDto fromProduct(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .amount(product.getPrice())
                .build();
    }

    private Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getTitle());
        product.setPrice(productDto.getAmount());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }
}
