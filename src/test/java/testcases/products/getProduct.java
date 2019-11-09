package testcases.products;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.commonMethods;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class getProduct {
	
	@Test
	public void getProductList() {
		
		RestAssured.baseURI="http://localhost:3030";
		
		
		Response res = given().
		param("$limit","5").
		when().
		get("/products").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		extract().response();
		
		JsonPath j = commonMethods.rawToJSON(res);
		
		
		
		//Based on ID
		
		String prodID="";		
		Response res1 = given().
		header("Content-Type", "application/json").
		when().
		get("/products/"+prodID).then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		extract().response();
		
		JsonPath j1 = commonMethods.rawToJSON(res1);
		
	}

}
