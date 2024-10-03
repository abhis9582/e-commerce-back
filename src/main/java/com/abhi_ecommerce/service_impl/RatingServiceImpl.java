package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.dto.RatingRequest;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.entity.Rating;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.ProductException;
import com.abhi_ecommerce.repository.RatingRepository;
import com.abhi_ecommerce.service.ProductService;
import com.abhi_ecommerce.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;
    private ProductService productService;
    public RatingServiceImpl(RatingRepository ratingRepository,
                             ProductService productService){
        this.ratingRepository = ratingRepository;
        this.productService = productService;

    }
    @Override
    public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException {
        Product product = productService.findProductById(ratingRequest.getProductId());
        Rating rating = new Rating();
        rating.setRating(rating.getRating());
        rating.setProduct(product);
        rating.setUser(user);
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(int productId) {

        return ratingRepository.getAllProductsRating(productId);
    }
}
