package com.gcu.ecommerce.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.ecommerce.business.ProductBusinessService;
import com.gcu.ecommerce.models.ProductModel;
import org.springframework.web.bind.annotation.PathVariable; // Milestone 5

/**
 * ProductController handles product catalog and product creation requests.
 */
@Controller
public class ProductController {

    private final ProductBusinessService productBusinessService;

    /**
     * Constructor injection for ProductBusinessService.
     *
     * @param productBusinessService injected product service bean
     */
    public ProductController(ProductBusinessService productBusinessService) {
        this.productBusinessService = productBusinessService;
    }

    @GetMapping("/products")
    public String products(Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("products", productBusinessService.getProducts());
        return "products";
    }

    @GetMapping("/products/create")
    public String createProductForm(Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("productModel", new ProductModel());
        return "create-product";
    }

    @PostMapping("/products/create")
    public String createProduct(@Valid ProductModel productModel, BindingResult result, Model model, HttpSession session) {

        System.out.println("POST /products/create reached.");

        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("User not logged in.");
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            System.out.println("Validation failed.");
            return "create-product";
        }

        System.out.println("Saving product: " + productModel.getName());

        productBusinessService.addProduct(productModel);
        return "redirect:/products";
    }
 // Milestone 5 product details page
    @GetMapping("/products/details/{id}")
    public String productDetails(@PathVariable int id, Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("product", productBusinessService.getProductById(id));
        return "product-details";
    }

    // Milestone 5 edit product form
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable int id, Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("productModel", productBusinessService.getProductById(id));
        return "edit-product";
    }

    // Milestone 5 update product
    @PostMapping("/products/update")
    public String updateProduct(@Valid ProductModel productModel, BindingResult result, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            return "edit-product";
        }

        productBusinessService.updateProduct(productModel);
        return "redirect:/products";
    }

    // Milestone 5 delete product
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        productBusinessService.deleteProduct(id);
        return "redirect:/products";
    }
}