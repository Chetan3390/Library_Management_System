package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;

@SpringBootTest
public class LmsUserServicesApplicationTests {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRegister() {
		User user = new User();
		user.setUserName("testuser");
		user.setPassword("password");
		when(userRepository.save(any(User.class))).thenReturn(user);

		User registeredUser = userService.register(user);

		assertNotNull(registeredUser);
		assertEquals("testuser", registeredUser.getUserName());
	}

	@Test
	void testLoginUser_Success() {
		UserDto userDto = new UserDto();
		userDto.setUserName("testuser");
		userDto.setPassword("password");

		User user = new User();
		user.setUserName("testuser");
		user.setPassword("password");
		when(userRepository.findByUserName("testuser")).thenReturn(Optional.of(user));

		String result = userService.loginUser(userDto);

		assertEquals("User Logged In Successfully!!", result);
	}

	@Test
	void testLoginUser_UserNotFound() {
		UserDto userDto = new UserDto();
		userDto.setUserName("nonexistentuser");
		userDto.setPassword("password");

		when(userRepository.findByUserName("nonexistentuser")).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> userService.loginUser(userDto));
	}

	@Test
	void testLoginUser_InvalidPassword() {
		UserDto userDto = new UserDto();
		userDto.setUserName("testuser");
		userDto.setPassword("wrongpassword");

		User user = new User();
		user.setUserName("testuser");
		user.setPassword("correctpassword");
		when(userRepository.findByUserName("testuser")).thenReturn(Optional.of(user));

		assertThrows(InvalidCredentialsException.class, () -> userService.loginUser(userDto));
	}
}
