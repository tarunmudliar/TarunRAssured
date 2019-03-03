package Tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import response.Login;

public class PostTest {

	@Test
	public void login() {
		RestAssured.baseURI = "https://wallet-uat.udio.in";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject RequestParams = new JSONObject();
		RequestParams.put("mobileNumber", "1600160016"); // Cast
		RequestParams.put("password", "Udio@123");
		RequestParams.put("socialType", "NR");
		System.out.println(RequestParams.toJSONString());
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("tsv-product-id", "1");
		httpRequest.body(RequestParams.toJSONString());
		//Response response = httpRequest.post("/v2/user/login");
		Response response = httpRequest.request(Method.POST,"/v2/user/login");
		System.out.println(response.asString());
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		String message = response.jsonPath().get("message");
		System.out.println(message);
		Assert.assertEquals(message, "Wallet authentication successful", "Correct Success code was returned");
		ResponseBody body = response.getBody();
		if (response.getStatusCode() == 200) {
			// Deserialize the Response body into RegistrationFailureResponse
			Login responseBody = body.as(Login.class);
			// Use the RegistrationFailureResponse class instance to Assert the
			// values of
			// Response.
			Assert.assertEquals(0, responseBody.code);
			Assert.assertEquals("Wallet authentication successful", responseBody.message);
		} 
		Boolean isLinked = response.jsonPath().get("data.isLinked");
		System.out.println(isLinked);
		Long consumerId = response.jsonPath().get("data.userDetail.consumerId");
		System.out.println(consumerId);
	
		
		
		
	}

}