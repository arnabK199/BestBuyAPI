package testcases.stores;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.commonMethods;

public class PostandGetStore {

	@Test
	public void postAndGetStore() {
		
		
		String payLoad = "{\r\n" + 
				"  \"name\": \"ArnabStore12342\",\r\n" + 
				"  \"type\": \"FaltuKaDukaan\",\r\n" + 
				"  \"address\": \"Bangalore\",\r\n" + 
				"  \"address2\": \"Bangalore\",\r\n" + 
				"  \"city\": \"Bangalore\",\r\n" + 
				"  \"state\": \"Karnataka\",\r\n" + 
				"  \"zip\": \"51024\",\r\n" + 
				"  \"lat\": 0,\r\n" + 
				"  \"lng\": 0,\r\n" + 
				"  \"hours\": \"9326\",\r\n" + 
				"  \"services\": {}\r\n" + 
				"}";
		
		RestAssured.baseURI="http://localhost:3030";
		
		Response r =given().header("Content-Type", "application/json").body(payLoad).
				when().post("/stores").then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().extract().response();

		
		
		JsonPath j= commonMethods.rawToJSON(r);
		String storeID = j.getString("id");
		System.out.println(storeID);
		
	     Response r1 =given().header("Content-Type", "application/json")
		.when().delete("/stores/"+storeID).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
		
		JsonPath j1 = commonMethods.rawToJSON(r1); 
		
	}
}
