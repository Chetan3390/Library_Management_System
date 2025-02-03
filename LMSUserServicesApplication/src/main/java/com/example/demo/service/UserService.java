package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {
	User register(UserDto userDto);

	String login(String username, String password);
}