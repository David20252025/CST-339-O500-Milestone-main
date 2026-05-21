package com.gcu.ecommerce.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.ecommerce.models.ProductModel;

@Repository
public class ProductDataService implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductModel> findAll() {
        String sql = "SELECT id, name, category, price, quantity_in_stock, description FROM products";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }

    @Override
    public int create(ProductModel product) {
    
        String sql = "INSERT INTO products (name, category, price, quantity_in_stock, description) VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantityInStock(),
                product.getDescription()
        );  
        
    }
    
    // Milestone 5: display one product
    @Override
    public ProductModel findById(int id) {
        String sql = "SELECT id, name, category, price, quantity_in_stock, description FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToProduct, id);
    }

    // Milestone 5: update product in database
    @Override
    public int update(ProductModel product) {
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, quantity_in_stock = ?, description = ? WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantityInStock(),
                product.getDescription(),
                product.getId()
        );
    }

    // Milestone 5: delete product from database
    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    

    private ProductModel mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        return new ProductModel(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("quantity_in_stock"),
                rs.getString("description")
        );
    }
}