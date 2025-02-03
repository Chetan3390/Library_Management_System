package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.client.AdminFeignClient;
import com.example.demo.client.BookFeignClient;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookFeignClient bookFeignClient;
	private final AdminFeignClient adminFeignClient;
	private final BookRepository bookRepository;

	@Override
	public List<Book> getRequestedBooks() {
		return adminFeignClient.getAllBooks().stream().filter(Book::isRequested).toList();
	}

	@Override
	public List<Book> getAcceptedBooks() {
		return adminFeignClient.getAllBooks().stream().filter(Book::isAccepted).toList();
	}

	@Override
	public void requestBook(Long bookId) {
		adminFeignClient.requestBook(bookId);
	}

	@Override
	public String returnBook(Long bookId) {
		return adminFeignClient.returnBook(bookId);
	}

	@Override
	public List<Book> getAllBooks() {
		return adminFeignClient.getAllBooks();
	}

	@Override
	public void acceptBookRequest(Long bookId) {
		adminFeignClient.acceptBookRequest(bookId);
	}

	@Override
	public void rejectBookRequest(Long bookId) {
		adminFeignClient.rejectBookRequest(bookId);
	}

	@Override
	public void revokeBook(Long bookId) {
		adminFeignClient.revokeBook(bookId);
	}

}