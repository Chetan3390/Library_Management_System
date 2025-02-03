package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;

public interface AdminService {
    void acceptBookRequest(Long bookId);
    void rejectBookRequest(Long bookId);
    void revokeBook(Long bookId);
    void addNewBook(BookDto bookDto);
    void deleteBook(Long bookId);
	List<Book> getAllBooks();
	void requestBook(Long bookId);
	String returnBook(Long bookId);
}