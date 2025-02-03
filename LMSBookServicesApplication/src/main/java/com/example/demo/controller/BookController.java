package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping("/requested")
	public ResponseEntity<List<Book>> getRequestedBooks() {
		List<Book> books = bookService.getRequestedBooks();
		return ResponseEntity.ok(books);
	}

	@GetMapping("/accepted")
	public ResponseEntity<List<Book>> getAcceptedBooks() {
		List<Book> books = bookService.getAcceptedBooks();
		return ResponseEntity.ok(books);
	}

	@PutMapping("/request/{bookId}")
	public ResponseEntity<String> requestBook(@PathVariable Long bookId) {
		bookService.requestBook(bookId);
		return ResponseEntity.ok("Book requested successfully");
	}

	@PutMapping("/return/{bookId}")
	public String returnBook(@PathVariable Long bookId) {
		return bookService.returnBook(bookId);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

}
