package com.se.codingclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.UserDAO;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.createUser(user);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.getUserByUsername(username);
	}

	@Override
	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(id, user);
	}

	@Override
	public boolean updatePassword(int id, String password) {
		// TODO Auto-generated method stub
		return userDAO.updatePassword(id, password);
	}

}
