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
        System.out.println("DATABASE INSERT RUNNING: " + product.getName());

        String sql = "INSERT INTO products (name, category, price, quantity_in_stock, description) VALUES (?, ?, ?, ?, ?)";

        int rows = jdbcTemplate.update(
                sql,
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantityInStock(),
                product.getDescription()
        );

        System.out.println("ROWS INSERTED: " + rows);

        return rows;
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