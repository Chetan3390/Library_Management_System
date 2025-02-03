package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.lenient;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.client.AdminFeignClient;
import com.example.demo.entity.Book;
import com.example.demo.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTests {

    @Mock
    private AdminFeignClient adminFeignClient;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book25;
    private Book book26;
    private Book book27;
    private Book book29;
    private Book book30;

    @BeforeEach
    void setUp() {
        book25 = new Book(25L, "wroom", "wroom-wroom", false, false);
        book26 = new Book(26L, "work?", "working??", false, false);
        book27 = new Book(27L, "new title", "new author", true, false);
        book29 = new Book(29L, "Title", "Author", true, false);
        book30 = new Book(30L, "Title", "Author", false, true); // Requested
        System.out.println("Setup books: " + Arrays.asList(book25, book26, book27, book29, book30));
    }

    @Test
    void testGetRequestedBooks() {
        // Mock the AdminFeignClient to return a list of all books
        lenient().when(adminFeignClient.getAllBooks()).thenAnswer(invocation -> {
            List<Book> books = Arrays.asList(book25, book26, book27, book29, book30);
            System.out.println("Mocked getAllBooks: " + books);
            return books;
        });

        // Call the getRequestedBooks method
        List<Book> requestedBooks = bookService.getRequestedBooks();
        System.out.println("Requested books: " + requestedBooks);

        // Verify the expected behavior
        assertEquals(1, requestedBooks.size(), "Expected one requested book");
        assertEquals(book30, requestedBooks.get(0), "Expected book30 to be requested");
    }

    @Test
    void testGetRequestedBooksFailure() {
        // Mock the AdminFeignClient to return a list of all books without the requested book
       lenient().when(adminFeignClient.getAllBooks()).thenAnswer(invocation -> {
            List<Book> books = Arrays.asList(book25, book26, book27, book29);
            System.out.println("Mocked getAllBooks (failure test): " + books);
            return books;
        });

        // Call the getRequestedBooks method
        List<Book> requestedBooks = bookService.getRequestedBooks();
        System.out.println("Requested books (failure test): " + requestedBooks);

        // Verify the expected behavior
        assertNotEquals(1, requestedBooks.size(), "Expected no requested books");
    }
}
