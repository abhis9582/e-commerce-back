package com.abhi_ecommerce.repository;

import com.abhi_ecommerce.entity.Cart;
import com.abhi_ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c From Cart c Where c.user.id=:userId")
    Cart findByUserId(@Param("userId") int userId);
}
