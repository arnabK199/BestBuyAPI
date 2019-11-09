package testcases.products;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.commonMethods;

public class UpdateProduct {
	
	@Test
	public void updateProduct() {
		
		String productID = "9999693";
		String payLoad ="{\r\n" + 
				"  \"name\": \"arnab\",\r\n" + 
				"  \"type\": \"string\",\r\n" + 
				"  \"price\": 0,\r\n" + 
				"  \"shipping\": 0,\r\n" + 
				"  \"upc\": \"string\",\r\n" + 
				"  \"description\": \"string\",\r\n" + 
				"  \"manufacturer\": \"string\",\r\n" + 
				"  \"model\": \"string\",\r\n" + 
				"  \"url\": \"string\",\r\n" + 
				"  \"image\": \"string\"\r\n" + 
				"}";
		
		
		RestAssured.baseURI = "http://localhost:3030";
		
		Response response = given().header("Content-Type", "application/json").body(payLoad).
		when().
		patch("/products/"+productID).then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		.extract().response();
		
		JsonPath j = commonMethods.rawToJSON(response);
		
	}

}
