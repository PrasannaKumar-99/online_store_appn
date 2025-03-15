package com.example.onlinestore.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PaymentRequest {
    @NotBlank(message = "Card number is required")
    private String cardNumber;

    @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "Format: MM/YY")
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    @Size(min = 3, max = 4, message = "CVV must be 3 or 4 digits")
    private String cvv;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

    // Getters and Setters
}
