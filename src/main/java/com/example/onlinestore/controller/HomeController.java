package com.example.onlinestore.controller;

import com.example.onlinestore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    // Home page for users (index.html)
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home/index"; // Path: src/main/resources/templates/home/index.html
    }
}