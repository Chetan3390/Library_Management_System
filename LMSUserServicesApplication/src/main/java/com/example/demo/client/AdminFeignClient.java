package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Book;

@FeignClient(name = "admin-service", url = "http://localhost:8085/admin")
public interface AdminFeignClient {

	@GetMapping("/books/accepted")
	List<Book> getAcceptedBooks();

}