package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.BookFeignClient;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private UserService userService;

	private BookFeignClient bookFeignClient;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto userdto) {

		userService.register(userdto);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest) {
		String response = userService.login(loginRequest.getUserName(), loginRequest.getPassword());
		return ResponseEntity.ok(response);
	}

	
}