package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetTest {
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@Test
	public void GetWeatherDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
		// Make a request to the server by specifying the method Type and the
		// method URL.
		// This will return the Response from the server. Store the response in
		// a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		// Now let us print the body of the message to see what response
		// we have received from the server
		String responseBody = response.getBody().asString();
		// response.asString()
		System.out.println("Response Body is =>  " + responseBody);
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200, "Correct status received");
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine /* actual value */,
				"HTTP/1.1 200 OK" /* expected value */, "Correct status code returned");

		// .getHeader will do the same thing
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType = response.header("Server");
		System.out.println("Server value: " + serverType);
		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);

		System.out.println("----------------------");
		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
		// Assert can be used to verify headers

		ResponseBody body = response.getBody();

		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		Assert.assertEquals(
				bodyAsString.contains("Hyderabad") /* Expected value */,
				true /* Actual Value */, "Response body contains Hyderabad");
		// convert the body into lower case and then do a comparison to ignore
		// casing.
		// Assert.assertEquals(bodyAsString.toLowerCase().contains("hyderabad")
		// /*Expected value*/, true /*Actual Value*/, "Response body contains
		// Hyderabad");
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the
		// node
		// specified by JsonPath: City (Note: You should not put $. in the Java
		// code)
		String city = jsonPathEvaluator.get("City");

		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);

		// Validate the response
		Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");

	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}

	/*
	 * @Test (groups = {"Get", "REST"}) public void test2() {
	 * 
	 * }
	 */

	// For defining order of tests
	// dependsOnGroups can also be used
	/*
	 * @Test(dependsOnMethods = {"test2"}) public void test3(){
	 * 
	 * }
	 */

}
