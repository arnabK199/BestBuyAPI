package testcases.services;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.commonMethods;

public class GetServices {
	
	@Test
	public void getServices() {
		
		RestAssured.baseURI ="http://localhost:3030";
		
		
		Response r1 = given().header("Content-Type", "application/json").param("$limit", "100").
		when().
		get("/services").then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
		
		
		JsonPath j = commonMethods.rawToJSON(r1);
	}

}
