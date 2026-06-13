package com.gcu.ecommerce.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.ecommerce.models.ProductModel;
/**
 * JDBC implementation of the ProductDAO interface. Uses Spring's JdbcTemplate to execute SQL queries and manage product database operations
 */
@Repository
public class ProductDataService implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;
    
    /**
     * Spring JdbcTemplate class injected through the constructor
     *
     * @param jdbcTemplate The Spring JdbcTemplate object used to execute SQL queries against the database.
     */

    public ProductDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * Executes a SELECT query to retrieve all products from the database. Returns a complete list of all product records currently stored in the products table.
     *
     * @return A list of ProductModel objects representing all products in the database.
     */

    @Override
    public List<ProductModel> findAll() {
        String sql = "SELECT id, name, category, price, quantity_in_stock, description FROM products";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }

    /**
     * Executes an INSERT query to add a new product record to the database. Uses parameterized queries to safely insert product information including name, category, price, quantity in stock, and description.
     *
     * @param product The ProductModel object containing the product information to be inserted into the database.
     * @return The number of rows affected by the insert operation
     */
    
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
    
    /**
     * Executes a SELECT query to retrieve a single product from the database by its product ID
     * @param id The product ID used to identify and retrieve the specific product record.
     * @return A ProductModel object representing the product with the specified ID.
     */
    
    @Override
    public ProductModel findById(int id) {
        String sql = "SELECT id, name, category, price, quantity_in_stock, description FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToProduct, id);
    }

    /**
     * Executes an UPDATE query to modify an existing product record in the database. Uses the product ID to locate the correct record and updates all product fields including name, category, price, quantity in stock, and description.
     *
     * @param product The ProductModel object containing the updated product information.
     * @return The number of rows affected by the update operation
     */
    
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

    /**
     * Executes a DELETE query to remove a product record from the database based on its product ID. Permanently deletes the product record matching the specified ID from the products table.
     *
     * @param id The product ID identifying which product record to delete.
     * @return The number of rows affected by the delete operation
     */
    
    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
    /**
     * Private method that maps a single database result set row to a ProductModel object. Extracts product data from the result set columns and constructs a ProductModel instance with the retrieved values.
     *
     * @param rs The ResultSet object containing the database query result row to be mapped.
     * @param rowNum The row number index, used internally by JdbcTemplate
     * @return A ProductModel object populated with data from the database result set row.
     * @throws SQLException If an error occurs while accessing the result set columns.
     */
    

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