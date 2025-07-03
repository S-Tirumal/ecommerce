package com.ecom.productcatalogue.repository;

import com.ecom.productcatalogue.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testProductRepositoryIsNotNull() {
        // This test will check if the application context loads successfully
        // and the ProductRepository bean is created.
        assert productRepository != null;
    }

    @Test
    public void saveProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone");
        product1.setId(100L);
        product1.setDescription("latest Iphone");
        productRepository.save(product1);

        Product product2 = new Product();
        product1.setId(2L);
        product2.setName("Macbook");
        product2.setId(1021L);
        product2.setDescription("latest mac");
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        assert products.size() == 2 : "Expected 2 products, but found " + products.size();
        assert products.get(0).getName().equals("Iphone") : "First product should be Iphone";
        assert products.get(1).getName().equals("Macbook") : "Second product should be Macbook";

    }
}
