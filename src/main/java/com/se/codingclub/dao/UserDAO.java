package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.User;

public interface UserDAO {

	public List<User> getListUser();
	public User getUserById(int id);
	public boolean createUser(User user);
	public User getUserByUsername(String username);
	public boolean updatePassword(int id,String password);
	public User updateUser(int id,User user);
}
