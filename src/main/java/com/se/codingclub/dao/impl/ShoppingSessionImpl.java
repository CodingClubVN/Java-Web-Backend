package com.se.codingclub.dao.impl;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.ShoppingSessionDAO;
import com.se.codingclub.entity.ShoppingSession;

@Repository
public class ShoppingSessionImpl implements ShoppingSessionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession) {
		Session session = sessionFactory.getCurrentSession();
		shoppingSession.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		session.persist(shoppingSession);

		return shoppingSession;
	}

	@Override
	@Transactional
	public ShoppingSession getShoppingSessionByUserId(int id) {
		Session session = sessionFactory.getCurrentSession();

		String query = "select * from shopping_session where user_id =" + id;

		ShoppingSession shoppingSession = session.createNativeQuery(query, ShoppingSession.class).getSingleResult();
		return shoppingSession;
	}

	@Override
	@Transactional
	public void deleteShoppingSession(int id) {
		Session session = sessionFactory.getCurrentSession();
		ShoppingSession shoppingSession = session.find(ShoppingSession.class, id);
		session.delete(shoppingSession);
	}

}
