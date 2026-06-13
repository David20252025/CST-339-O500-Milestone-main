package com.gcu.ecommerce.data;

import java.util.List;

import com.gcu.ecommerce.models.ProductModel;

/**
 * Data access object interface for product persistence. Defines the contract for all database operations related to products, including retrieval, creation, updating, and deletion of product records.
 */

public interface ProductDAO {
    /**
     * Retrieves all products from the database. This method queries the product database table and returns a complete list of all product records currently stored.
     *
     * @return A list of ProductModel objects representing all products in the database
     */
    List<ProductModel> findAll();
    /**
     * Inserts a new product record into the database
     *
     * @param product The ProductModel object to be inserted
     * @return The number of rows affected by the insert operation
     */
    int create(ProductModel product);
    /**
     * Retrieves a single product from the database by its product ID
     *
     * @param id The product ID used to identify which product record to retrieve
     * @return A ProductModel object representing the specified product
     */
    
    ProductModel findById(int id);
    /**
     * Updates an existing product record in the database. Takes a ProductModel object with updated information and persists the changes to the corresponding database record.
     *
     * @param product The ProductModel object containing the updated product information
     * @return The number of rows affected by the update operation
     */
    int update(ProductModel product);
    /**
     * Deletes a specified product from the database based on its ID.
     *
     * @param id The product ID identifying which product record to delete.
     * @return The number of rows affected by the delete operation.
     */
    int deleteById(int id);
    
    
}