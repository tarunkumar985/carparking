package com.example.carparking.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.carparking.auth.dto.RegisterWithRolesRequest;
import com.example.carparking.auth.dto.UserRequestDTO;
import com.example.carparking.auth.entity.Users;
import com.example.carparking.auth.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public String register(@RequestBody UserRequestDTO requestDTO) {
		return service.register(requestDTO);

	}

	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return service.verify(user);
	}

	@PostMapping("/custom")
	public String custom(@RequestBody RegisterWithRolesRequest registerWithRolesRequest) {
		return service.customregisterUser(registerWithRolesRequest);
	}
}
