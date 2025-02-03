package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
class LmsAdminServicesApplicationTests {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private AdminServiceImpl adminService;

	private Book book;

	@BeforeEach
	void setUp() {
		// Initialize the book and set it as requested
		book = new Book(30L, "Title", "Author", false, true);
		System.out.println("Setup book: " + book);
	}

	@Test
	void testAcceptBookRequest() {
		// Ensure the book is requested and exists in the repository
		when(bookRepository.findById(30L)).thenReturn(Optional.of(book));
		System.out.println("Mocked bookRepository.findById: " + bookRepository.findById(30L).orElse(null));

		// Verify the mock setup before calling the service method
		Optional<Book> foundBook = bookRepository.findById(30L);
		System.out.println("Found book: " + foundBook.orElse(null));

		// Call the acceptBookRequest method
		adminService.acceptBookRequest(30L);

		// Verify the expected behavior
		assertTrue(book.isAccepted(), "Book should be accepted");
		assertFalse(book.isRequested(), "Book should not be requested");
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testAcceptBookRequestWhenBookNotFound() {
		// Mock the repository to return an empty Optional
		when(bookRepository.findById(30L)).thenReturn(Optional.empty());
		System.out.println("Mocked bookRepository.findById (not found): " + bookRepository.findById(30L).orElse(null));

		// Verify the mock setup before calling the service method
		Optional<Book> foundBook = bookRepository.findById(30L);
		System.out.println("Found book (not found case): " + foundBook.orElse(null));

		// Assert that the ResourceNotFoundException is thrown
		assertThrows(ResourceNotFoundException.class, () -> adminService.acceptBookRequest(30L));
	}
}
