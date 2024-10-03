package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.entity.Address;
import com.abhi_ecommerce.entity.Orders;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.OrderException;
import com.abhi_ecommerce.exception.OrdersException;
import com.abhi_ecommerce.repository.CartRepository;
import com.abhi_ecommerce.service.CartService;
import com.abhi_ecommerce.service.OrderService;
import com.abhi_ecommerce.service.ProductService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;
    public OrderServiceImpl(
            CartRepository cartRepository,
            CartService cartService,
            ProductService productService
    ){
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.productService = productService;
    }
    @Override
    public Orders createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Orders findOrderById(int orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Orders> userOrdersHistory(int userId) {
        return null;
    }

    @Override
    public Orders placedOrder(int orderId) throws OrdersException {
        return null;
    }

    @Override
    public Orders confirmedOrder(int orderId) throws OrdersException {
        return null;
    }

    @Override
    public Orders shippedOrder(int orderId) throws OrdersException {
        return null;
    }

    @Override
    public Orders deliveredOrder(int orderId) throws OrdersException {
        return null;
    }

    @Override
    public Orders canceledOrder(int orderId) throws OrdersException {
        return null;
    }

    @Override
    public List<Orders> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(int orderId) throws OrdersException {

    }
}
