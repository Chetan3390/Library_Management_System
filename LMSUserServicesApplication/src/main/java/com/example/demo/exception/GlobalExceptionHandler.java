package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		// Returning only the exception message
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> illegalStateException(IllegalStateException ex, WebRequest request) {
		// Returning only the exception message
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
		// Returning only the exception message
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalExceptionHandler(Exception ex, WebRequest request) {
		// Returning only the exception message
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
