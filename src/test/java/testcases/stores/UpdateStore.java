package testcases.stores;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.commonMethods;

public class UpdateStore {

	@Test
	public void postAndUpdateStore() {
		
		RestAssured.baseURI ="http://localhost:3030";
		
		String payLoad ="{\r\n" + 
				"  \"name\": \"ArnabKaDukaan\",\r\n" + 
				"  \"type\": \"PlayStation Cafe\",\r\n" + 
				"  \"address\": \"Bangalore\",\r\n" + 
				"  \"address2\": \"Bangalore\",\r\n" + 
				"  \"city\": \"Bangalore\",\r\n" + 
				"  \"state\": \"Karnataka\",\r\n" + 
				"  \"zip\": \"751024\",\r\n" + 
				"  \"lat\": 0,\r\n" + 
				"  \"lng\": 0,\r\n" + 
				"  \"hours\": \"string\",\r\n" + 
				"  \"services\": {}\r\n" + 
				"}";
		
		String payLoadUpdate ="{\r\n" + 
				"  \"name\": \"ArnabKaDukaan1\",\r\n" + 
				"  \"type\": \"PlayStation Cafe\",\r\n" + 
				"  \"address\": \"Bangalore\",\r\n" + 
				"  \"address2\": \"Bangalore\",\r\n" + 
				"  \"city\": \"Bangalore\",\r\n" + 
				"  \"state\": \"Karnataka\",\r\n" + 
				"  \"zip\": \"751024\",\r\n" + 
				"  \"lat\": 0,\r\n" + 
				"  \"lng\": 0,\r\n" + 
				"  \"hours\": \"string\",\r\n" + 
				"  \"services\": {}\r\n" + 
				"}";
		
		Response r =given().header("Content-Type", "application/json").body(payLoad).when().post("/stores")
		.then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().extract().response();
		
		JsonPath j = commonMethods.rawToJSON(r);
		String storeId = j.get("id").toString();
		System.out.println("The id of the Store added"+storeId);
		
		Response r1 =given().header("Content-Type", "application/json").body(payLoadUpdate).when().patch("/stores/"+storeId)
		.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
		
		JsonPath j1 =commonMethods.rawToJSON(r1);
		
		
		
		
		
	}
	

}
