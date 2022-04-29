package com.se.codingclub.service;

import com.se.codingclub.entity.ShoppingSession;

public interface ShoppingSessionService {

	public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession);

	public ShoppingSession getShoppingSessionByUserId(int id);

	public void deleteShoppingSession(int id);
}
