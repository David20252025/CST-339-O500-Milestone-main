package com.gcu.ecommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.ecommerce.business.ProductBusinessService;
import com.gcu.ecommerce.models.ProductModel;

@RestController
public class ProductRestController {

    private final ProductBusinessService productBusinessService;

    public ProductRestController(ProductBusinessService productBusinessService) {
        this.productBusinessService = productBusinessService;
    }

    // Milestone 7 API endpoint to get all products
    @GetMapping("/api/products")
    public List<ProductModel> getAllProducts() {
        return productBusinessService.getProducts();
    }

    // Milestone 7 API endpoint to get one product by ID
    @GetMapping("/api/products/{id}")
    public ProductModel getProductById(@PathVariable int id) {
        return productBusinessService.getProductById(id);
    }
}