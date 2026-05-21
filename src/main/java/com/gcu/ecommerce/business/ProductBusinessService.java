package com.gcu.ecommerce.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.ecommerce.data.ProductDAO;
import com.gcu.ecommerce.models.ProductModel;

@Service
public class ProductBusinessService {

    private final ProductDAO productDAO;

    public ProductBusinessService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductModel> getProducts() {
        return productDAO.findAll();
    }

    public void addProduct(ProductModel productModel) {
        productDAO.create(productModel);
    }
    
    // Milestone 5 get one product
    public ProductModel getProductById(int id) {
        return productDAO.findById(id);
    }

    // Milestone 5 update product
    public void updateProduct(ProductModel productModel) {
        productDAO.update(productModel);
    }

    // Milestone 5 delete product
    public void deleteProduct(int id) {
        productDAO.deleteById(id);
    }
    
}