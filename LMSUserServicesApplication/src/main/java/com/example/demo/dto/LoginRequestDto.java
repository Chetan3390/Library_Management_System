package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

	@NotBlank(message = "Username is required")
	private String userName;

	@NotBlank(message = "password is required")
	private String password;
}