package com.se.codingclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.ShoppingSessionDAO;
import com.se.codingclub.entity.ShoppingSession;
import com.se.codingclub.service.ShoppingSessionService;

@Service
public class ShoppingSessionServiceImpl implements ShoppingSessionService {

	@Autowired
	private ShoppingSessionDAO shoppingSessionDAO;

	@Override
	public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession) {
		// TODO Auto-generated method stub

		return shoppingSessionDAO.saveShoppingSession(shoppingSession);
	}

	@Override
	public ShoppingSession getShoppingSessionByUserId(int id) {
		// TODO Auto-generated method stub
		return shoppingSessionDAO.getShoppingSessionByUserId(id);
	}

	@Override
	public void deleteShoppingSession(int id) {
		shoppingSessionDAO.deleteShoppingSession(id);

	}

}
