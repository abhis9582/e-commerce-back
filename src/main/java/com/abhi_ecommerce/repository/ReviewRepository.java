package com.abhi_ecommerce.repository;

import com.abhi_ecommerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r from Review r where r.product.id = :productId")
    public List<Review> getAllProductsReviews(@Param("productId")int productId);
}
