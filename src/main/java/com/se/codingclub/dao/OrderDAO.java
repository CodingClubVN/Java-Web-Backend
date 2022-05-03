package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.Order;

public interface OrderDAO {
	
	public Order createOrder(Order order);
	
	public Order updateStatusOrder(int order_id, String status);

	public List<Order> getOrders();
	
	public List<Order> getOrderOfUser(int user_id);
	
	public Order getOrderById(int order_id);
}
