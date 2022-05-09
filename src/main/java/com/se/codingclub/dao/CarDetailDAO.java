package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.CartDetail;

public interface CarDetailDAO {

	public CartDetail saveCartDetail(CartDetail cartDetail);

	public List<CartDetail> getListCartDetailBySessionId(int id);

	public void deleteCartDetail(int id);

	public CartDetail getCartDetailById(int id);

	public CartDetail updateCartDetail(int id, CartDetail cartDetail);
	
	public CartDetail getCartDetailByProductId(int product_id, int cart_id);
}
