package testcases.stores;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.commonMethods;

public class GetStores {
	
	@Test
	public void getAllStores() {
		//this is a git Push test
		RestAssured.baseURI ="http://localhost:3030";
		
		Response r =given().param("$limit", "5").when().get("/stores").then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
		
		JsonPath j = commonMethods.rawToJSON(r);
		System.out.println(j.get("total").toString());
		for(int i =0 ; i<=4 ;i++) {
			System.out.println(j.get("data["+i+"].name"));
		}
		
	}

}
