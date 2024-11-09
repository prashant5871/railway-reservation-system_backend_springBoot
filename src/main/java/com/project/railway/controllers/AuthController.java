package com.project.railway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.railway.entities.User;
import com.project.railway.payloads.LoginRequest;
import com.project.railway.repositories.UserRepository;
import com.project.railway.services.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private UserRepository userRepository;

	@Autowired
	public AuthController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/register")
	public User addUser(@RequestBody User user) {
		this.userRepository.save(user);
		return user;
	}

	@PostMapping("/login")
	public User findUserByEmail(@RequestBody LoginRequest loginRequest) {
		User u = userRepository.findByEmail(loginRequest.getEmail());
		if(u != null)
		{
			System.out.println("user is not null");
			System.out.println("u.getPassword() = " + u.getPassword() + " loginRequest.getPassword() = " + loginRequest.getPassword());
			if(u.getPassword().compareTo(loginRequest.getPassword()) == 0)
			{
				return u;
			}
		}
		
		System.out.println("User is null");
		
		return null;
	}

}
