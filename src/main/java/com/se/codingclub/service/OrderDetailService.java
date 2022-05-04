package com.se.codingclub.service;

import java.util.List;

import com.se.codingclub.entity.OrderDetail;

public interface OrderDetailService {

	public OrderDetail createOrderDetail(OrderDetail orderDetail);
	public List<OrderDetail> getOrderDetailByOrderId(int order_id);
}
