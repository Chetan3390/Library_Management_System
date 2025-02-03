package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Book;

@FeignClient(name = "book-service")
public interface BookFeignClient {

	@GetMapping("/all")
	List<Book> getAllBooks();
	
	@GetMapping("/accepted")
	List<Book> getAcceptedBooks();
	
	@GetMapping("/requested")
	List<Book> getRequestedBooks();

	@DeleteMapping("/delete/{bookId}")
	void deleteBook(@PathVariable Long bookId);
}