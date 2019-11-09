package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class commonMethods {
	
	
	public static JsonPath rawToJSON(Response r) {
		
		String response = r.asString();
		System.out.println(response);
		JsonPath j = new JsonPath(response);
		return j;
	}
	
	public static XmlPath xmlToJson(Response r) {

		String response = r.asString();
		System.out.println(response);
		XmlPath x = new XmlPath(response);
		return x;
}
}

