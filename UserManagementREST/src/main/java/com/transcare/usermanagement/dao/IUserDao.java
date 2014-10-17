package com.transcare.usermanagement.dao;

import java.util.List;

import com.transcare.usermanagement.dto.User;

public interface IUserDao {
	public User performLogin(String userName, String password);
	
	public User addNewUser(User user);

	public boolean update(User user);

	public List<User> getAllUsers();

	public int deleteUser(int userId);
}
