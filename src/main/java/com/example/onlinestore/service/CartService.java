package com.example.onlinestore.service;

import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.repository.CartRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(User user, Product product, int quantity) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public List<Cart> getCartItems(String username) {
        return cartRepository.findByUserUsername(username);
    }

    public double calculateTotal(List<Cart> cartItems) {
        return cartItems.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum();
    }
    

    public void deleteCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
