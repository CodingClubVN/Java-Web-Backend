package com.se.codingclub.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.codingclub.dao.OrderDAO;
import com.se.codingclub.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		order.setCreateDate(new Date());
		order.setModifiedDate(new Date());
		Session session = sessionFactory.getCurrentSession();
		session.persist(order);
		return order;
	}

	@Override
	@Transactional
	public Order updateStatusOrder(int order_id, String status) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Order order = session.find(Order.class, order_id);
		order.setStatus(status);
		session.merge(order);
		return order;
	}

	@Override
	@Transactional
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "select * from orders";
		List<Order> list = session.createNativeQuery(sql, Order.class).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Order> getOrderOfUser(int user_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "select * from orders where user_id = " + user_id;
		List<Order> list = session.createNativeQuery(sql, Order.class).getResultList();
		return list;
	}

	@Override
	@Transactional
	public Order getOrderById(int order_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Order order = session.find(Order.class, order_id);
		return order;
	}

}
