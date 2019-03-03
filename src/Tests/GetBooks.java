package Tests;

import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import response.Books;

public class GetBooks {

	@Test
	public void books() {
		//Doesn't work anymore, code is correct!
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/getallbooks");
		System.out.println(response.asString());
		// First get the JsonPath object instance from the Response interface
		
		//1--------------------------
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		// Read all the books as a List of String. Each item in the list
		// represent a book node in the REST service Response
		List<String> allBooks = jsonPathEvaluator.getList("books.title");
		// Iterate over the list and print individual book item
		for (String t : allBooks) {
			System.out.println("Book: " + t);
		}
		System.out.println("------------------------");
		
		//2----------------------------
		
		// Read all the books as a List of String. Each item in the list
		// represent a book node in the REST service Response
		List<Books> allBooks2 = jsonPathEvaluator.getList("books", Books.class);
		// Iterate over the list and print individual book item
		// Note that every book entry in the list will be complete Json object
		// of book
		for (Books book : allBooks2) {
			System.out.println("Book: " + book.title);
		}

		System.out.println("------------------------");
		
		//3-----------------------------
		
		Books[] books = response.jsonPath().getObject("books", Books[].class);

		for (Books book : books) {
			System.out.println("Book title " + book.title);
		}

	}
}
