package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.dto.AddItemRequest;
import com.abhi_ecommerce.entity.Cart;
import com.abhi_ecommerce.entity.CartItem;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.ProductException;
import com.abhi_ecommerce.repository.CartRepository;
import com.abhi_ecommerce.service.CartItemService;
import com.abhi_ecommerce.service.CartService;
import com.abhi_ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemService cartItemService,
                           ProductService productService){
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(int userId, AddItemRequest req) throws ProductException {
        Cart cart= cartRepository.findByUserId(userId);
        Product product= productService.findProductById(req.getProductId());
        CartItem isPresent = cartItemService.isCartItemExists(cart, product, req.getSize(), userId);
        if(isPresent == null){
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setCart(cart);
            item.setQuantity(req.getQuantity());
            item.setUserId(userId);
            int price = req.getQuantity()*product.getDiscountedPrice();
            item.setPrice(price);
            item.setSize(req.getSize());
            CartItem cartItem = cartItemService.createCartItem(item);
            cart.getCartItems().add(cartItem);
        }
        return "item added to cart.";
    }

    @Override
    public Cart findUserCart(int userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;
        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItems(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
