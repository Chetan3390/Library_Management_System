package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final BookRepository bookRepository;

    private static final String BOOK_NOT_FOUND = "Book not found";

	@Override
	public void acceptBookRequest(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		if (!book.isRequested()) {
			throw new IllegalStateException("Book is not requested");
		}
		book.setAccepted(true);
		book.setRequested(false);
		bookRepository.save(book);
	}

	@Override
	public void rejectBookRequest(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		if (!book.isRequested()) {
			throw new IllegalStateException("Book is not requested");
		}
		book.setRequested(false);
		bookRepository.save(book);
	}

	@Override
	public void revokeBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(
				() -> new ResourceNotFoundException("Book not found or cannot revoke a book which is not accepted"));
		book.setAccepted(false);
		bookRepository.save(book);
	}

	@Override
	public void addNewBook(BookDto bookDto) {
		Book newBook = new Book();
		newBook.setTitle(bookDto.getTitle());
		newBook.setAuthor(bookDto.getAuthor());
		newBook.setAccepted(false);
		newBook.setRequested(false);
		bookRepository.save(newBook);

	}

	@Override
	public void deleteBook(Long bookId) {
		if (!bookRepository.existsById(bookId)) {
			throw new ResourceNotFoundException(BOOK_NOT_FOUND);
		}
		bookRepository.deleteById(bookId);

	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void requestBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		book.setRequested(true);
		bookRepository.save(book);
	}

	@Override
	public String returnBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		if (!book.isAccepted()) {
			throw new IllegalStateException("Book has not been accepted or has already been returned.");
		}
		book.setAccepted(false);
		book.setRequested(false);
		bookRepository.save(book);
		return "Book returned successfully";
	}

}