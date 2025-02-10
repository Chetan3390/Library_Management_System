package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.BookFeignClient;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;

	private BookFeignClient bookFeignClient;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {

		userService.register(user);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
    public String loginUser(@RequestBody UserDto user) throws InvalidCredentialsException {
    	return userService.loginUser(user);
    }

	
}