package com.se.codingclub.service;

import com.se.codingclub.entity.User;

public interface AuthService {

	public String encode(String rawPassword);
	public boolean matches(String rawPassword, String encodedPassword);
	public String generateTokenLogin(String username, String role, int user_id);
	public User getUserByToken(String token);
}
