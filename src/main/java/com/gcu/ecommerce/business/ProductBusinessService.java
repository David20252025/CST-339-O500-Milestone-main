package com.gcu.ecommerce.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.ecommerce.data.ProductDAO;
import com.gcu.ecommerce.models.ProductModel;

/**
 * Service class responsible for handling product-related business logic, including CRUD operations.
 */
@Service
public class ProductBusinessService {

    private final ProductDAO productDAO;

    /**
     * Constructs a new ProductBusinessService with the specified ProductDAO dependency.
     *
     * @param productDAO The ProductDAO instance used for accessing product data.
     */
    public ProductBusinessService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Retrieves a list of all products.

     * @return A list of ProductModel instances representing all products.
     */
    public List<ProductModel> getProducts() {
        return productDAO.findAll();
    }

    /**
     * Adds a new product

     * @param productModel The product data to be persisted.
     */
    public void addProduct(ProductModel productModel) {
        productDAO.create(productModel);
    }
    
    /**
     * Retrieves a single product by its ID.

     * @param id The ID of the product.
     * @return The ProductModel instance representing the requested product.
     */
    public ProductModel getProductById(int id) {
        return productDAO.findById(id);
    }

    /**
     * Updates an existing product in the system.

     * @param productModel The updated product data.
     */
    public void updateProduct(ProductModel productModel) {
        productDAO.update(productModel);
    }

    /**
     * Deletes a product from the system by ID.

     * @param id The product ID
     */
    public void deleteProduct(int id) {
        productDAO.deleteById(id);
    }
    
}