package com.transcare.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transcare.usermanagement.dto.User;
import com.transcare.usermanagement.service.IUserService;
import com.transcare.usermanagement.utils.ResponseObject;

@RestController
@RequestMapping(value = "/service")
public class UserRESTController {

	@Autowired
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Object initService() {
		return "Service is running!";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseObject userLogin(@RequestParam("userName") String userName,
			@RequestParam("password") String password) {

		User user = userService.performLogin(userName, password);
		System.out.println("User result: " + user);

		ResponseObject response = new ResponseObject();

		if (user != null) {
			response.setResult(user);
			response.setInfo("Login successful!");
			return response;
		} else {
			response.setWarning("Invalid UserName or Password!");
		}

		return response;
	}

	@RequestMapping(value = "/user/signup", consumes = "application/json", method = RequestMethod.POST, produces = "application/json")
	public ResponseObject performSignUp(@RequestBody User user) {

		System.out.println("Incoming Object:" + user);

		user = userService.performSignup(user);

		ResponseObject response = new ResponseObject();

		if (user != null) {
			response.setResult(user);
			response.setInfo("Signup Successful!");
			return response;
		}

		response.setError("Error in signup!");
		return response;
	}

	@RequestMapping(value = "/user/getAllUsers", method = RequestMethod.GET, produces = "application/json")
	public ResponseObject getAllUsers() {

		System.out.println("Get all users service is called!");

		List<User> users = userService.getAllUsers();

		ResponseObject response = new ResponseObject();
		if (users != null) {

			response.setResult(users);
			response.setInfo("Users found!");
			return response;
		}

		response.setInfo("Users not found!");

		return response;
	}

	@RequestMapping(value = "/user/updateUser", consumes = "application/json", method = RequestMethod.POST, produces = "application/json")
	public ResponseObject updateUser(@RequestBody User user) {

		System.out.println("Incoming Object:" + user);

		ResponseObject response = new ResponseObject();
		if (user != null) {
			if (userService.updateUser(user)) {
				response.setSuccess("Profile is updated successfully!");
			} else {
				response.setWarning("User data is not updated!");
			}
		} else {
			response.setWarning("Invalid Input!");
		}

		return response;
	}

}
