package com.tew.business;
import com.tew.model.User;

public interface LoginService {
	
	public LoginService createLoginService();

	public User verify(String name, String password);	
}
