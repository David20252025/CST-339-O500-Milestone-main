package com.gcu.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.ecommerce.business.ProductBusinessService;
import com.gcu.ecommerce.models.ProductModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Rest Controller for handling REST api calls related to products.
 */
@Tag(name = "Products", description = "Product APIs")
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    ProductBusinessService service;

    /**
     * Business service dependency injected through the constructor. This allows the controller to utilize the business service's methods to perform operations related to products.
     *
     * @param productBusinessService The business service to inject.
     */
    public ProductRestController(ProductBusinessService productBusinessService) {
        this.service = productBusinessService;
    }

        /**
         * Handles GET requests made to the "/api/products" url. Returns a JSON list of all products.
         *
         * @return A JSON list of all products.
         */
        @Operation(
            summary = "Retrieve all Products",
            description = "Fetch a list of all available products. Requires login/auth.")
    @GetMapping
    public List<ProductModel> getAllProducts() {
        return service.getProducts();
    }

        /**
         * Handles GET requests made to the "/api/products/{id}" url. Returns a JSON representation of the specified product if found, or a 404 error if not found.

         * @param id The id of the product to retrieve
         * @return A JSON representation of the product if found, or a 404 error if not found.
         */
        @Operation(
            summary = "Retrieve a specific Product using its ID",
            description = "Fetch the information of the product matching the given ID. Will return a 404 error if a matching product is not found. Requires login/auth.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable int id) {
        try {
            ProductModel product = service.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}