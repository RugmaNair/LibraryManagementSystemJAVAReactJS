package com.app.library;

import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.app.library.model.Book;
import com.app.library.model.Library;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class LibraryManagementApplicationTests {

	 @LocalServerPort
	    private int port;
	 
	TestRestTemplate restTemplate = new TestRestTemplate();
	

	ResponseEntity<String> response = null;
	String expected = null;

	HttpHeaders headers = new HttpHeaders();
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	@Order(1)
	public void addLibrary() throws JSONException {

		Library library = new Library("LibraryTest");

		HttpEntity<Library> entity = new HttpEntity<Library>(library, headers);

		response = restTemplate.exchange(
				createURLWithPort("/library/add"),
				HttpMethod.POST, entity, String.class);

		expected = "{id:6, name: LibraryTest, books:null}";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}
	
	@Test
	@Order(2)
	public void addBook() throws JSONException {
		Book book = new Book("BookTest","AuthorTest","PublisherTest",100,10);
		
		response = restTemplate.exchange(
				createURLWithPort("/book/add/6"),
				HttpMethod.POST, new HttpEntity<Book>(book, headers), String.class);
		
		expected = "{id:15,title:BookTest,author:AuthorTest,publisher:PublisherTest,pages:100,available:10}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(3)
	public void getLibrary() throws JSONException {
		response = restTemplate.exchange(
				createURLWithPort("/library/6"),
				HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);

		expected = "{id:6, name:LibraryTest,books:[{id:15,title:BookTest,author:AuthorTest,publisher:PublisherTest,pages:100,available:10}]}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
}


