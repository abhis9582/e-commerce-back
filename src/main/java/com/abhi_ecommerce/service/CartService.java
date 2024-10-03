package com.abhi_ecommerce.service;

import com.abhi_ecommerce.dto.AddItemRequest;
import com.abhi_ecommerce.entity.Cart;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.ProductException;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(int userId, AddItemRequest req) throws ProductException;
    public Cart findUserCart(int userId);

}
