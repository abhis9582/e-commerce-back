package com.abhi_ecommerce.service;

import com.abhi_ecommerce.dto.ReviewRequest;
import com.abhi_ecommerce.entity.Review;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.ProductException;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest reviewRequest, User userId) throws ProductException;
    public List<Review> getAllReview(int productId);
}
