package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AdminServiceImpl;

class LmsAdminServicesApplicationTests {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private AdminServiceImpl adminService;

	private Book book;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		book = new Book(1L, "Title", "Author", false, false);
	}

	@Test
	void testAcceptBookRequest() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		book.setRequested(true);

		adminService.acceptBookRequest(1L);

		assertTrue(book.isAccepted());
		assertFalse(book.isRequested());
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testRejectBookRequest() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		book.setRequested(true);

		adminService.rejectBookRequest(1L);

		assertFalse(book.isRequested());
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testRevokeBook() {
		book.setAccepted(true);
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		adminService.revokeBook(1L);

		assertFalse(book.isAccepted());
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testAddNewBook() {
		BookDto bookDto = new BookDto();
		bookDto.setTitle("New Title");
		bookDto.setAuthor("New Author");

		adminService.addNewBook(bookDto);

		verify(bookRepository, times(1)).save(any(Book.class));
	}

	@Test
	void testDeleteBook() {
		when(bookRepository.existsById(1L)).thenReturn(true);

		adminService.deleteBook(1L);

		verify(bookRepository, times(1)).deleteById(1L);
	}

	@Test
	void testGetAllBooks() {
		adminService.getAllBooks();

		verify(bookRepository, times(1)).findAll();
	}

	@Test
	void testRequestBook() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		adminService.requestBook(1L);

		assertTrue(book.isRequested());
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testReturnBook() {
		book.setAccepted(true);
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		String result = adminService.returnBook(1L);

		assertFalse(book.isAccepted());
		assertFalse(book.isRequested());
		verify(bookRepository, times(1)).save(book);
		assertEquals("Book returned successfully", result);
	}
}
