package com.se.codingclub.service;

import com.se.codingclub.entity.User;

public interface UserService {

	public User createUser(User user);
	public User getUserByUsername(String username);
	public User updateUser(int id, User user);
	public boolean updatePassword(int id, String password);
	public User getUserById(int id);
}
