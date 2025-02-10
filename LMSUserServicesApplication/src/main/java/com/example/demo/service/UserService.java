package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;

public interface UserService {
	User register(User user);

	public String loginUser(UserDto userDto) throws InvalidCredentialsException;
}