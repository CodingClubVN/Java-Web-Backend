package com.se.codingclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.CarDetailDAO;
import com.se.codingclub.entity.CartDetail;
import com.se.codingclub.service.CartDetailService;

@Service
public class CartDetailServiceImpl implements CartDetailService {

	@Autowired
	private CarDetailDAO carDetailDAO;

	@Override
	public CartDetail saveCartDetail(CartDetail cartDetail) {
		// TODO Auto-generated method stub
		return carDetailDAO.saveCartDetail(cartDetail);
	}

	@Override
	public List<CartDetail> getListCartDetailBySessionId(int id) {
		// TODO Auto-generated method stub

		return carDetailDAO.getListCartDetailBySessionId(id);
	}

	@Override
	public void deleteCartDetail(int id) {
		// TODO Auto-generated method stub
		carDetailDAO.deleteCartDetail(id);
	}

	@Override
	public CartDetail getCartDetailById(int id) {
		// TODO Auto-generated method stub
		return carDetailDAO.getCartDetailById(id);
	}

	@Override
	public CartDetail updateCartDetail(int id, CartDetail cartDetail) {
		// TODO Auto-generated method stub
		return carDetailDAO.updateCartDetail(id, cartDetail);
	}

	@Override
	public CartDetail getCartDetailByProductId(int product_id, int cart_id) {
		// TODO Auto-generated method stub
		return  carDetailDAO.getCartDetailByProductId(product_id, cart_id);
	}

}
