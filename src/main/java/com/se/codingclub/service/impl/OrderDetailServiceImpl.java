package com.se.codingclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.OrderDetailDAO;
import com.se.codingclub.entity.OrderDetail;
import com.se.codingclub.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public OrderDetail createOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return orderDetailDAO.createOrderDetail(orderDetail);
	}

}
