package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LmsAdminServicesApplicationTests {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private AdminServiceImpl adminService;

	private Book book;

	@BeforeEach
	void setUp() {
		book = new Book(1L, "Sample Title", "Sample Author", false, true);
	}

	@Test
	void contextLoads() {
		// The primary purpose of this test is to check if the Spring application
		// context loads successfully.
		// No specific logic or assertions are implemented in this test.
	}

	@Test
	void testAcceptBookRequest() {
		Book book = new Book(1L, "Sample Title", "Sample Author", false, true);
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
		adminService.acceptBookRequest(1L);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testRejectBookRequest() {
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
		adminService.rejectBookRequest(1L);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testRevokeBook() {
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
		adminService.revokeBook(1L);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testAddNewBook() {
		BookDto bookDto = new BookDto();
		bookDto.setTitle("New Book Title");
		bookDto.setAuthor("New Author");

		adminService.addNewBook(bookDto);
		verify(bookRepository, times(1)).save(any(Book.class));
	}

	@Test
	void testDeleteBook() {
		when(bookRepository.existsById(anyLong())).thenReturn(true);
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
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
		adminService.requestBook(1L);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testReturnBook() {
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
		adminService.returnBook(1L);
		verify(bookRepository, times(1)).save(book);
	}

}
