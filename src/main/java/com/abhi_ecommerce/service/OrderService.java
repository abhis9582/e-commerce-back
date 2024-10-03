package com.abhi_ecommerce.service;

import com.abhi_ecommerce.entity.Address;
import com.abhi_ecommerce.entity.Orders;
import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.OrderException;
import com.abhi_ecommerce.exception.OrdersException;

import java.util.List;

public interface OrderService{
    public Orders createOrder(User user, Address shippingAddress);
    public Orders findOrderById(int orderId) throws OrderException;
    public List<Orders> userOrdersHistory(int userId);
    public Orders placedOrder(int orderId) throws OrdersException;
    public Orders confirmedOrder(int orderId) throws OrdersException;
    public Orders shippedOrder(int orderId) throws OrdersException;
    public Orders deliveredOrder(int orderId) throws OrdersException;
    public Orders canceledOrder(int orderId) throws OrdersException;
    public List<Orders> getAllOrders();
    public void deleteOrder(int orderId) throws OrdersException;
}
