package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User register(UserDto userDto) {

		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole("USER");
		return userRepository.save(user);
	}

	@Override
	public String login(String username, String password) {

		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ResourceNotFoundException("Invalid username or password");
		}
		return "Login successful";
	}

}