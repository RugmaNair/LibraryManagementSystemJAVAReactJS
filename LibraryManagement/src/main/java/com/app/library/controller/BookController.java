package com.app.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import com.app.library.repository.LibraryRepository;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookRepository repo;
	
	@Autowired
	LibraryRepository libRepo;
	
	@PostMapping(path="/add/{libraryId}",produces= {"application/json"})
	public Book addBook(@PathVariable (value = "libraryId") int libraryId, @RequestBody Book book) {
		return libRepo.findById(libraryId).map(library -> {
			book.setLibrary(library);
            return repo.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Library  " + libraryId + " not found"));
	}
	
	@GetMapping(path="/list")
	public List<Book> listBooks(){
		return repo.findAll();
	}
	
	@GetMapping(path="/{bookId}")
	public Optional<Book> getBook(@PathVariable("bookId") int bookId){
		return repo.findById(bookId);
	}
	
	@DeleteMapping("/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId) {
		repo.delete(repo.getOne(bookId));
		return "deleted";
	}
	
	@PutMapping("/update")
	public Book updateBook(@RequestBody Book book) {
		return repo.save(book);
	}
}
