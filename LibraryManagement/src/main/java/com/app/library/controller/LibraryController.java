package com.app.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.library.model.Library;
import com.app.library.repository.LibraryRepository;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	LibraryRepository repo;
	
	@PostMapping(path="/add",produces= {"application/json"})
	public Library addBook(@RequestBody Library library) {
		return repo.save(library);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path="/list")
	public List<Library> listLibraries(){
		return repo.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path="/{libraryId}")
	public Optional<Library> getLibrary(@PathVariable("libraryId") int libraryId){
		return repo.findById(libraryId);
	}
	
	@DeleteMapping("/{libraryId}")
	public String deleteLibrary(@PathVariable("libraryId") int bookId) {
		repo.delete(repo.getOne(bookId));
		return "deleted";
	}
	
	@PutMapping("/update")
	public Library updateLibrary(@RequestBody Library library) {
		return repo.save(library);
	}
}
