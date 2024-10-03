package com.abhi_ecommerce.service;

import com.abhi_ecommerce.entity.Cart;
import com.abhi_ecommerce.entity.CartItem;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.exception.CartItemException;
import com.abhi_ecommerce.exception.UserException;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(int userId, int id, CartItem cartItem) throws UserException, CartItemException;
    public CartItem isCartItemExists(Cart cart, Product product, String size, int userId);
    public void removeCartItem(int userId, int cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(int cartItemId) throws CartItemException;


}
