package com.ecom.productcatalogue.service;

import com.ecom.productcatalogue.model.Product;
import com.ecom.productcatalogue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
}
