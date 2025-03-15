package com.example.onlinestore.controller;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // Restrict access to ADMIN only
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // Admin product management page (products.html)
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product()); // For forms
        return "admin/create_product"; // Path: src/main/resources/templates/admin/products.html
    }

    // Show form to create a new product
    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/products"; // create_product.html
    }

    // Save new product
    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products"; // Redirect back to product list
    }

    // Show form to edit a product
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "admin/edit_product"; // edit_product.html
    }

    // Update existing product
    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    // Delete a product
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}