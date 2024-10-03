package com.abhi_ecommerce.repository;

import com.abhi_ecommerce.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT r from Rating r Where r.product.id = :productId")
    public List<Rating> getAllProductsRating(@Param("productId")int productId);
}
