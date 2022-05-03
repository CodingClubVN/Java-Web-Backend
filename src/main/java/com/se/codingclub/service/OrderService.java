package com.se.codingclub.service;

import com.se.codingclub.entity.Order;

public interface OrderService {
	public Order createOrder(Order order);
	public Order updateStatusOrder(int id, String status);
}
