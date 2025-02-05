package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Book;

@FeignClient(name = "admin-service")
public interface AdminFeignClient {

	@PutMapping("/admin/request/{bookId}")
	void requestBook(@PathVariable Long bookId);

	@PostMapping("/admin/accept/{bookId}")
	void acceptBookRequest(@PathVariable Long bookId);

	@PostMapping("/admin/reject/{bookId}")
	void rejectBookRequest(@PathVariable Long bookId);

	@PostMapping("/admin/revoke/{bookId}")
	void revokeBook(@PathVariable Long bookId);
	
	@PutMapping("/admin/return/{bookId}")
    String returnBook(@PathVariable Long bookId);

	@GetMapping("/admin/books")
	List<Book> getAllBooks();


}