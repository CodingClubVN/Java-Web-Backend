package com.se.codingclub.service;

import java.util.List;

import com.se.codingclub.entity.Order;

public interface OrderService {
	public Order createOrder(Order order);
	public Order updateStatusOrder(int id, String status);
	public Order getOrderById(int order_id);
	public List<Order> getOrders();
	public List<Order> getOrdersByUserId(int user_id);
}
