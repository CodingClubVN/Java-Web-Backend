package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.OrderDetail;

public interface OrderDetailDAO {
	public OrderDetail createOrderDetail(OrderDetail orderDetail);
	public List<OrderDetail> getOrderDetailByOrderId(int order_id);
}
