package com.se.codingclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.OrderDAO;
import com.se.codingclub.entity.Order;
import com.se.codingclub.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDAO.createOrder(order);
	}

	@Override
	public Order updateStatusOrder(int id, String status) {
		// TODO Auto-generated method stub
		return orderDAO.updateStatusOrder(id, status);
	}

}