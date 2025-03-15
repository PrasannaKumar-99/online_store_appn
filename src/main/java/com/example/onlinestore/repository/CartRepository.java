package com.example.onlinestore.repository;

import com.example.onlinestore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserUsername(String username);
    
    void deleteByProductId(Long productId);
}