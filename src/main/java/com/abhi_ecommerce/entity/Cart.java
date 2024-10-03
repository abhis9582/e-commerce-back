package com.abhi_ecommerce.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "total_items")
    private int totalItems;
    private int totalDiscountedPrice;
    private int discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }

    public void setTotalDiscountedPrice(int totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Cart(int id, User user, Set<CartItem> cartItems, double totalPrice, int totalItems, int totalDiscountedPrice, int discount) {
        this.id = id;
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.discount = discount;
    }

    public Cart() {
    }
}
