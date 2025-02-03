package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {
	
	@DeleteMapping("/books/{bookId}")
	void deleteBook(@PathVariable("bookId") Long bookId);
}