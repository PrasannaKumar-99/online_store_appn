package com.example.onlinestore.service;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.repository.CartRepository;
import com.example.onlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    public ProductService(ProductRepository productRepository,   CartRepository cartRepository) {
    	
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
    	
        cartRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }
}