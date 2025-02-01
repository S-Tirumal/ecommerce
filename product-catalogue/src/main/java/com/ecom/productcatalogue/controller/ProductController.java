package com.ecom.productcatalogue.controller;

import com.ecom.productcatalogue.model.Product;
import com.ecom.productcatalogue.service.ProductService;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Path("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @POST
    public Product addProduct(@RequestBody Product newProduct){
        return newProduct;
    }

    @DELETE
    @Path("/{id}")
    public void deleteProduct(@PathParam("id") String id){

    }
}
