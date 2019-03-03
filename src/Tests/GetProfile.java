package Tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetProfile {

	@Test
	public void getPro(){
		
		RestAssured.baseURI = "https://merchant.udio.in/merchant";
		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		auth.setUserName("072e0050e5e2f5a3cfd2cc94166244a9");
		auth.setPassword("beaa79e149d965528ca57a73f801539d");
		RestAssured.authentication = auth;
		RequestSpecification rs = RestAssured.given();
		
		//Response res = rs.request(Method.GET, "/v1/api/wallet/info/9595410635");
		Response res = rs.get("/v1/api/wallet/info/9595410635");
		System.out.println(res.asString());
		
	}
	
}
