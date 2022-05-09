package com.se.codingclub.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.CarDetailDAO;
import com.se.codingclub.entity.CartDetail;

@Repository
public class CartDetailDAOImpl implements CarDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public CartDetail saveCartDetail(CartDetail cartDetail) {

		Session session = sessionFactory.getCurrentSession();
		cartDetail.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		session.persist(cartDetail);

		return cartDetail;
	}

	@Override
	@Transactional
	public List<CartDetail> getListCartDetailBySessionId(int id) {
		Session session = sessionFactory.getCurrentSession();
		String query = "Select * from cart_detail where session_id=" + id;
		List<CartDetail> cartDetails = session.createNativeQuery(query, CartDetail.class).getResultList();

		return cartDetails;
	}

	@Override
	@Transactional
	public void deleteCartDetail(int id) {

		Session session = sessionFactory.getCurrentSession();
		CartDetail cartDetail = session.find(CartDetail.class, id);
		session.delete(cartDetail);
	}

	@Override
	@Transactional
	public CartDetail getCartDetailById(int id) {

		CartDetail cartDetail = sessionFactory.getCurrentSession().find(CartDetail.class, id);

		return cartDetail;
	}

	@Override
	@Transactional
	public CartDetail updateCartDetail(int id, CartDetail cartDetail) {
		Session session = sessionFactory.getCurrentSession();

		CartDetail cartDetailOld = session.find(CartDetail.class, id);
		if (cartDetail.getShoppingSession() != null)
			cartDetailOld.setShoppingSession(cartDetail.getShoppingSession());
		if (cartDetail.getQuantity() != 0)
			cartDetailOld.setQuantity(cartDetail.getQuantity());
		if (cartDetail.getProduct() != null)
			cartDetailOld.setProduct(cartDetail.getProduct());
		if (cartDetail.getCreatedDate() != null)
			cartDetailOld.setCreatedDate(cartDetail.getCreatedDate());
		cartDetailOld.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		session.merge(cartDetailOld);
		return cartDetailOld;
	}

	@Override
	@Transactional
	public CartDetail getCartDetailByProductId(int product_id, int cart_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "Select * from cart_detail where product_id=" + product_id + " and session_id = "+ cart_id;
		List<CartDetail> cartDetails = session.createNativeQuery(query, CartDetail.class).getResultList();
		if(cartDetails.size()>0) {
			return cartDetails.get(0);
		}else {
			return null;
		}
	}

}
