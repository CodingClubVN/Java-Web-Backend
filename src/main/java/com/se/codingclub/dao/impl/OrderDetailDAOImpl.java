package com.se.codingclub.dao.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.codingclub.dao.OrderDetailDAO;
import com.se.codingclub.entity.OrderDetail;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public OrderDetail createOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		orderDetail.setCreatedDate(new Date());
		orderDetail.setModifiedDate(new Date());
		Session session = sessionFactory.getCurrentSession();
		session.persist(orderDetail);
		return orderDetail;
	}

}
