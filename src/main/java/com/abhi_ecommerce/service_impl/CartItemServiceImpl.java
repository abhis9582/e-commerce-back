package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.entity.Cart;
import com.abhi_ecommerce.entity.CartItem;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.CartItemException;
import com.abhi_ecommerce.exception.UserException;
import com.abhi_ecommerce.repository.CartItemRepository;
import com.abhi_ecommerce.repository.CartRepository;
import com.abhi_ecommerce.service.CartItemService;
import com.abhi_ecommerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;
    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               UserService userService,
                               CartRepository cartRepository){
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository =cartRepository;

    }
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice((int)cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(int userId, int id, CartItem cartItem) throws UserException, CartItemException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());
        if(user.getId() == userId){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*(int)item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());

        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExists(Cart cart, Product product, String size, int userId) {
        CartItem item = cartItemRepository.isCartItemExists(cart, product, size, userId);
        return item;
    }

    @Override
    public void removeCartItem(int userId, int cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);
        if(user.getId() == reqUser.getId()){
            this.cartItemRepository.deleteById(cartItemId);
        }else {
            throw new UserException("You cannot remove another users item.");
        }
    }

    @Override
    public CartItem findCartItemById(int cartItemId) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        throw new CartItemException("Cart item not found with id "+ cartItemId);
    }
}
