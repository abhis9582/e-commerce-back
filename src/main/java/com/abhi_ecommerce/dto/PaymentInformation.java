package com.abhi_ecommerce.dto;

import jakarta.persistence.Column;

public class PaymentInformation {
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiry_date")
    private String expiryDate;
    @Column(name = "cvv")
    private String cvv;
}
