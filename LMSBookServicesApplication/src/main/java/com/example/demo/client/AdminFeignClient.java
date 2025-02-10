package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Book;

//Feign client interface to communicate with the Admin Service
@FeignClient(name = "admin-service")
public interface AdminFeignClient {

    // Endpoint to request a book
	@PutMapping("/admin/request/{bookId}")
	void requestBook(@PathVariable Long bookId);

    // Endpoint to accept a book request
	@PostMapping("/admin/accept/{bookId}")
	void acceptBookRequest(@PathVariable Long bookId);

    // Endpoint to reject a book request
	@PostMapping("/admin/reject/{bookId}")
	void rejectBookRequest(@PathVariable Long bookId);

    // Endpoint to revoke a book
	@PostMapping("/admin/revoke/{bookId}")
	void revokeBook(@PathVariable Long bookId);
	
    // Endpoint to return a book
	@PutMapping("/admin/return/{bookId}")
    String returnBook(@PathVariable Long bookId);

    // Endpoint to get all books
	@GetMapping("/admin/books")
	List<Book> getAllBooks();


}