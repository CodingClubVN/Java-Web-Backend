package com.se.codingclub.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.ShoppingSessionDAO;
import com.se.codingclub.entity.ShoppingSession;

@Repository
public class ShoppingSessionImpl implements ShoppingSessionDAO{

	@Override
	@Transactional
	public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession) {
		
		
		
		return null;
	}

}
