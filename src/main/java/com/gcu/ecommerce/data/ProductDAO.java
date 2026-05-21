package com.gcu.ecommerce.data;

import java.util.List;

import com.gcu.ecommerce.models.ProductModel;

public interface ProductDAO {
    List<ProductModel> findAll();
    int create(ProductModel product);
    
    // Milestone 5,find one product by id, update product and delete product
    ProductModel findById(int id);
    int update(ProductModel product);
    int deleteById(int id);
    
    
}