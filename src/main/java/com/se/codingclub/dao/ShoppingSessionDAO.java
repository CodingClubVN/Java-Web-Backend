package com.se.codingclub.dao;

import com.se.codingclub.entity.ShoppingSession;

public interface ShoppingSessionDAO {

	public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession);

	public ShoppingSession getShoppingSessionByUserId(int id);

	public void deleteShoppingSession(int id);
}
