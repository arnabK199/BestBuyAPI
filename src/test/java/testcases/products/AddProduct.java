package testcases.products;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddProduct {
	
	@Test
	public void addProduct() {
		
		RestAssured.baseURI="http://localhost:3030";
		
		
		HashMap<String, Object> map = new HashMap<String , Object>();
		map.put("name", "MyConsole");
		map.put("type", "Console");
		map.put("price", "25000");
		map.put("shipping", "100");
		map.put("upc", "82375");
		map.put("description", "This is a Console");
		map.put("manufacturer", "Sony");
		map.put("model", "7219");
		map.put("url", "21921");
		map.put("image", "97219");
		
		System.out.println(map);
		
		
		String s = "{\r\n" + 
				"  \"name\": \"ab1c11dsgshd\",\r\n" + 
				"  \"type\": \"abcd\",\r\n" + 
				"  \"price\": 0,\r\n" + 
				"  \"shipping\": 0,\r\n" + 
				"  \"upc\": \"string\",\r\n" + 
				"  \"description\": \"abcd\",\r\n" + 
				"  \"manufacturer\": \"abcd\",\r\n" + 
				"  \"model\": \"abcd\",\r\n" + 
				"  \"url\": \"string\",\r\n" + 
				"  \"image\": \"string\"\r\n" + 
				"}";
		
		
		Response r = given().header("Content-Type", "application/json").body(s).
		when().post("/products").
		then().assertThat().statusCode(201).and().contentType(ContentType.JSON).
		and().extract().response();
		
		System.out.println(r.asString());
		
		
	}

}
