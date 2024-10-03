package com.abhi_ecommerce.service;

import com.abhi_ecommerce.dto.RatingRequest;
import com.abhi_ecommerce.entity.Rating;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException;
    public List<Rating> getProductsRating(int productId);


}
