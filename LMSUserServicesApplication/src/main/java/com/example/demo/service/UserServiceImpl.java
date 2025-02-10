package com.example.demo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public User register(User user) {

		return userRepository.save(user);
	}

	@Override
	public String loginUser(UserDto user) throws InvalidCredentialsException {
		Optional<User> foundUser = userRepository.findByUserName(user.getUserName());
		
		if(!foundUser.isPresent()) {
			throw new ResourceNotFoundException("User Not found!!");
		}
		
		User existingUser = foundUser.get();
		
		if(!Objects.equals(user.getPassword(), existingUser.getPassword())) {
			throw new InvalidCredentialsException("Password is Incorrect!!");
		}
		
		return "User Logged In Successfully!!";
	}

}