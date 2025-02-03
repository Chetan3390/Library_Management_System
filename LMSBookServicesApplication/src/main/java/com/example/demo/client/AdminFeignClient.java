package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Book;

@FeignClient(name = "admin-service", url = "http://localhost:8085/admin")
public interface AdminFeignClient {

	@PutMapping("/request/{bookId}")
	void requestBook(@PathVariable Long bookId);

	@PostMapping("/accept/{bookId}")
	void acceptBookRequest(@PathVariable Long bookId);

	@PostMapping("/reject/{bookId}")
	void rejectBookRequest(@PathVariable Long bookId);

	@PostMapping("/revoke/{bookId}")
	void revokeBook(@PathVariable Long bookId);
	
	@PutMapping("/return/{bookId}")
    String returnBook(@PathVariable Long bookId);

	@GetMapping("/books")
	List<Book> getAllBooks();


}