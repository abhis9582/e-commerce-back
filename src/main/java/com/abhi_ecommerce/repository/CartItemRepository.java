package com.abhi_ecommerce.repository;

import com.abhi_ecommerce.entity.Cart;
import com.abhi_ecommerce.entity.CartItem;
import com.abhi_ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userId")
    public CartItem isCartItemExists(@Param("cart")Cart cart,
    @Param("product")Product product, @Param("size") String size, @Param("userId")int userId);
}
