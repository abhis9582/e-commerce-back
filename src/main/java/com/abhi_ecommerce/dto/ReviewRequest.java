package com.abhi_ecommerce.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private int productId;
    private String review;
}
