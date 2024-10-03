package com.abhi_ecommerce.dto;

import lombok.Data;

@Data
public class RatingRequest {
    private int productId;
    private double rating;
}
