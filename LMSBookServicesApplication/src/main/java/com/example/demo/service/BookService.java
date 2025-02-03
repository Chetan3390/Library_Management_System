package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Book;

public interface BookService {
	List<Book> getAllBooks();

	List<Book> getRequestedBooks();

	List<Book> getAcceptedBooks();

	void requestBook(Long bookId);

	String returnBook(Long bookId);

	void acceptBookRequest(Long bookId);

	void rejectBookRequest(Long bookId);

	void revokeBook(Long bookId);
}