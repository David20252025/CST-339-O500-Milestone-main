package com.gcu.ecommerce.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Model class for product data
 */

public class ProductModel {

    private int id;

    @NotBlank(message = "Product name is required.")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Category is required.")
    private String category;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0.")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative.")
    private int quantityInStock;

    @NotBlank(message = "Description is required.")
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters.")
    private String description;
    
    /**
     * Default constructor for ProductModel.
  */

    public ProductModel() {
    }
    
    /**
     * Full constructor for creating a ProductModel with all product fields.
  *
     * @param id The product ID.
     * @param name The product name.
     * @param category The product category.
     * @param price The product price.
     * @param quantityInStock The quantity currently in stock.
     * @param description The product description.
  */

    public ProductModel(int id, String name, String category, double price, int quantityInStock, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.description = description;
    }
    
	/**
	 * Retrieves the product ID.
	 *
	 * @return The product ID value.
	 */

    public int getId() {
        return id;
    }
    
	/**
	 * Sets the product ID.
	 *
	 * @param id The product ID to store.
	 */

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Retrieves the product name.
     *
     * @return The product name value.
     */


    public String getName() {
        return name;
    }
    
    /**
     * Sets the product name.
     *
     * @param name The product name to store.
     */

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retrieves the product category.
     *
     * @return The product category value.
     */

    
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the product category.
     *
     * @param category The product category to store.
     */

    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Retrieves the product price.
     *
     * @return The product price value.
     */

    
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the product price.
     *
     * @param price The product price to store.
     */

    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Retrieves the quantity of the product currently in stock.
     *
     * @return The quantity in stock value.
     */

    
    public int getQuantityInStock() {
        return quantityInStock;
    }
    
    /**
     * Sets the quantity of the product currently in stock.
     *
     * @param quantityInStock The quantity in stock to store.
     */

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    
    /**
     * Retrieves the product description.
     *
     * @return The product description value.
     */

    
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the product description.
     *
     * @param description The product description to store.
     */

    public void setDescription(String description) {
        this.description = description;
    }
}