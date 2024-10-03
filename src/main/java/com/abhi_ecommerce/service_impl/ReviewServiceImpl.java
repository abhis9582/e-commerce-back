package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.dto.ReviewRequest;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.entity.Review;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.ProductException;
import com.abhi_ecommerce.repository.ReviewRepository;
import com.abhi_ecommerce.response.ProductRepository;
import com.abhi_ecommerce.service.ProductService;
import com.abhi_ecommerce.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private ProductService productService;
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private ReviewServiceImpl(ProductService productService,
                              ReviewRepository reviewRepository,
                              ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }
    @Override
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException {
        Product product = productService.findProductById(reviewRequest.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setReview(reviewRequest.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(int productId) {
        return reviewRepository.getAllProductsReviews(productId);
    }
}
