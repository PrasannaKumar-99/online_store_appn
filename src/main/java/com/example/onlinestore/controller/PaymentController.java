package com.example.onlinestore.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.onlinestore.entity.PaymentRequest;
import com.example.onlinestore.entity.User;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("paymentRequest", new PaymentRequest());
        return "payment/checkout";
    }

    @PostMapping("/process")
    public String processPayment(@Valid @ModelAttribute PaymentRequest paymentRequest, 
                                BindingResult result, 
                                @AuthenticationPrincipal User user) {
        if (result.hasErrors()) {
            return "payment/checkout";
        }
        // Integrate with a payment gateway (e.g., Stripe) here (placeholder)
        return "redirect:/payment/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "payment/confirmation";
    }
}
