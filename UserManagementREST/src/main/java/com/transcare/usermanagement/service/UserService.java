package com.transcare.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcare.usermanagement.dao.IUserDao;
import com.transcare.usermanagement.dto.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public User performLogin(String userName, String password) {
		return userDao.performLogin(userName, password);

	}

	public User performSignup(User user) {

		return userDao.addNewUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public boolean updateUser(User user) {
		
		return userDao.update(user);
	}

}
