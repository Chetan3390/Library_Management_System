package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.service.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	// Dependency Injection of AdminService
	private final AdminService adminService;

    // Endpoint to accept a book request
	@PostMapping("/accept/{bookId}")
	public ResponseEntity<String> acceptBookRequest(@PathVariable Long bookId) {
		adminService.acceptBookRequest(bookId);
		return ResponseEntity.ok("Book request accepted successfully");
	}

	// Endpoint to reject a book request
	@PostMapping("/reject/{bookId}")
	public ResponseEntity<String> rejectBookRequest(@PathVariable Long bookId) {
		adminService.rejectBookRequest(bookId);
		return ResponseEntity.ok("Book request rejected successfully");
	}

	// Endpoint to revoke a book
	@PostMapping("/revoke/{bookId}")
	public ResponseEntity<String> revokeBook(@PathVariable Long bookId) {
		adminService.revokeBook(bookId);
		return ResponseEntity.ok("Book revoked successfully");
	}

    // Endpoint to add a new book
	@PostMapping("/add")
	public ResponseEntity<String> addNewBook(@Valid @RequestBody BookDto bookDto) {
		adminService.addNewBook(bookDto);
		return ResponseEntity.ok("New book added successfully");
	}

    // Endpoint to delete a book
	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
		adminService.deleteBook(bookId);
		return ResponseEntity.ok("Book deleted successfully");
	}

    // Endpoint to get all books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = adminService.getAllBooks();
		return ResponseEntity.ok(books);
	}
	
    // Endpoint to return a book (invoked by BookService)	
	@PutMapping("/return/{bookId}")
	public String returnBook(@PathVariable Long bookId) {
		return adminService.returnBook(bookId);
	}
	
    // Endpoint to request a book
	@PutMapping("/request/{bookId}")
	public void requestBook(@PathVariable Long bookId) {
		adminService.requestBook(bookId);
	}
	
}