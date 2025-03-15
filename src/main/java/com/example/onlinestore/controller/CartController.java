package com.example.onlinestore.controller;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.CartService;
import com.example.onlinestore.service.ProductService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam int quantity, @AuthenticationPrincipal User user) {
        Product product = productService.getProductById(productId);
        cartService.addToCart(user, product, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{cartId}")
    public String removeFromCart(@PathVariable Long cartId) {
        cartService.deleteCartItem(cartId);
        return "redirect:/cart";
    }
    
    @GetMapping
    public String viewCart(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("cartItems", cartService.getCartItems(user.getUsername()));
        model.addAttribute("total", cartService.calculateTotal(cartService.getCartItems(user.getUsername())));
        return "cart/view";
    }
}
