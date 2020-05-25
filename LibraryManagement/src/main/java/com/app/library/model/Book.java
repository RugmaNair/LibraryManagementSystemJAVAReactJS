package com.app.library.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Book")
public class Book {
	 @Id
	 @GeneratedValue
	 @Column(name = "id")
	 private int id;
	 @Column(name = "title")
	 private String title;
	 @Column(name = "author")
	 private String author;
	 @Column(name = "publisher")
	 private String publisher;
	 @Column(name = "pages")
	 private int pages;
	 @Column(name = "available")
	 private int available;
	 @ManyToOne
	 @JoinColumn(name="library_id")
	 @JsonIgnore
	 private Library library;
	 
	@Override
	 public String toString() {
	 return "Book [Id=" + id + ", title=" + title + ", author=" + author + ", publisher="
	 + publisher + ", pages=" + pages + ", available=" + available + "]";
	 }

	public Book(String title, String author, String publisher, int pages, int available) {
	 super();
	 this.title = title;
	 this.author = author;
	 this.publisher = publisher;
	 this.pages = pages;
	 this.available = available;
	 }
	
	public Book() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
	 return title;
	 }

	public void setTitle(String title) {
	 this.title = title;
	 }

	public String getAuthor() {
	 return author;
	 }

	public void setAuthor(String author) {
	 this.author = author;
	 }

	public String getPublisher() {
	 return publisher;
	 }

	public void setPublisher(String publisher) {
	 this.publisher = publisher;
	 }

	public int getPages() {
	 return pages;
	 }

	public void setPages(int pages) {
	 this.pages = pages;
	 }

	public int getAvailable() {
	 return available;
	 }

	public void setAvailable(int available) {
	 this.available = available;
	 }
	
	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

}
