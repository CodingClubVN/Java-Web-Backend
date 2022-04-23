package com.se.codingclub.dto;

public class Token {
	private String token;

	public Token(String token) {
		super();
		this.token = token;
	}
	public Token() {
		// TODO Auto-generated constructor stub
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Token [token=" + token + "]";
	}
	
}
