package com.transcare.usermanagement.service;

import java.util.List;

import com.transcare.usermanagement.dto.User;

public interface IUserService {
	public User performLogin(String userName, String password);

	public User performSignup(User user);
	
	public List<User> getAllUsers();
	
	public boolean updateUser(User user);
}
