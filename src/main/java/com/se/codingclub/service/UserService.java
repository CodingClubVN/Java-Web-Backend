package com.se.codingclub.service;

import com.se.codingclub.entity.User;

public interface UserService {

	public boolean createUser(User user);
	public User getUserByUsername(String username);
}
