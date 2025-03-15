package com.example.onlinestore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.onlinestore.entity.User;
import com.example.onlinestore.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create admin user only if it doesn't exist
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin")); // BCrypt-encoded
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }

        // Create regular user only if it doesn't exist
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user")); // BCrypt-encoded
            user.setRole("USER");
            userRepository.save(user);
        }
    }
}