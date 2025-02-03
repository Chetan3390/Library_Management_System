package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

	@NotBlank(message = "Username is mandatory")
	private String userName;

	@NotBlank(message = "Password is mandatory")
	private String password;

}