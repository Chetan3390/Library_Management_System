package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.client.AdminFeignClient;
import com.example.demo.entity.Book;
import com.example.demo.service.BookServiceImpl;

class LmsBookServicesApplicationTests {

	@Mock
	private AdminFeignClient adminFeignClient;

	@InjectMocks
	private BookServiceImpl bookService;

	private Book book;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		book = new Book(1L, "Title", "Author", false, false);
	}

	@Test
	void testGetRequestedBooks() {
		book.setRequested(true);
		when(adminFeignClient.getAllBooks()).thenReturn(List.of(book));

		List<Book> requestedBooks = bookService.getRequestedBooks();

		assertEquals(1, requestedBooks.size());
		assertTrue(requestedBooks.get(0).isRequested());
	}

	@Test
	void testGetAcceptedBooks() {
		book.setAccepted(true);
		when(adminFeignClient.getAllBooks()).thenReturn(List.of(book));

		List<Book> acceptedBooks = bookService.getAcceptedBooks();

		assertEquals(1, acceptedBooks.size());
		assertTrue(acceptedBooks.get(0).isAccepted());
	}

	@Test
	void testRequestBook() {
		doNothing().when(adminFeignClient).requestBook(1L);

		bookService.requestBook(1L);

		verify(adminFeignClient, times(1)).requestBook(1L);
	}

	@Test
	void testReturnBook() {
		when(adminFeignClient.returnBook(1L)).thenReturn("Book returned successfully");

		String result = bookService.returnBook(1L);

		verify(adminFeignClient, times(1)).returnBook(1L);
		assertEquals("Book returned successfully", result);
	}

	@Test
	void testGetAllBooks() {
		when(adminFeignClient.getAllBooks()).thenReturn(List.of(book));

		List<Book> books = bookService.getAllBooks();

		assertEquals(1, books.size());
		assertEquals(book.getId(), books.get(0).getId());
	}

	@Test
	void testAcceptBookRequest() {
		doNothing().when(adminFeignClient).acceptBookRequest(1L);

		bookService.acceptBookRequest(1L);

		verify(adminFeignClient, times(1)).acceptBookRequest(1L);
	}

	@Test
	void testRejectBookRequest() {
		doNothing().when(adminFeignClient).rejectBookRequest(1L);

		bookService.rejectBookRequest(1L);

		verify(adminFeignClient, times(1)).rejectBookRequest(1L);
	}

	@Test
	void testRevokeBook() {
		doNothing().when(adminFeignClient).revokeBook(1L);

		bookService.revokeBook(1L);

		verify(adminFeignClient, times(1)).revokeBook(1L);
	}
}
