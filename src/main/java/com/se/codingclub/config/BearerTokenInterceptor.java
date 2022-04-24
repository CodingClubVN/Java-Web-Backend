package com.se.codingclub.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.se.codingclub.dto.Auth;

public class BearerTokenInterceptor extends HandlerInterceptorAdapter{
	  private Auth tokenWrapper;

	  public BearerTokenInterceptor(Auth tokenWrapper) {
	    this.tokenWrapper = tokenWrapper;
	  }

	  @Override
	  public boolean preHandle(HttpServletRequest request,
	          HttpServletResponse response, Object handler) throws Exception {
	    final String authorizationHeaderValue = request.getHeader("Authorization");
	    if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
	      String token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length());
	      tokenWrapper.setToken(token);
	    }
	    
	    return true;
	  }
}
