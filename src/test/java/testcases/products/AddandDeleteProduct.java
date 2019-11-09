package testcases.products;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.Hashtable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ExcelReader;
import resources.commonMethods;

public class AddandDeleteProduct {
	
	@Test(dataProvider ="getData")
	public void addDeleteProduct(Hashtable<String , String> table) throws IOException {
		
		
		RestAssured.baseURI="http://localhost:3030";
		
	/*	List<String> mylist = getExcelData.getData("addanddeleteproduct", "addanddeleteproduct");
		
		HashMap<String , Object> map = new HashMap<String , Object>();
		map.put("name", mylist.get(1));
		map.put("type", mylist.get(2));
		map.put("price", mylist.get(3).toString());
		map.put("shipping", mylist.get(4).toString());
		map.put("upc", mylist.get(5));
		map.put("description", mylist.get(6));
		map.put("manufacturer", mylist.get(7));
		map.put("model", mylist.get(8));
		map.put("url", mylist.get(9));
		map.put("image", mylist.get(10));
		
		System.out.println(map);
		
		Gson gsone = new Gson();
		JsonObject res = gsone.toJsonTree(map).getAsJsonObject();
		System.out.println(res); */
		
		String s = "{\r\n" + 
				"  \"name\": \""+table.get("name")+"\",\r\n" + 
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
		
		JsonPath j = commonMethods.rawToJSON(r);
		String prodId = j.get("id").toString();
		
		Response r1 =given().header("Content-Type", "application/json").when().delete("/products/"+prodId).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		extract().response();
		
		System.out.println(r1.asString());
	}
	
	
	@DataProvider
	public Object[][] getData() {
		
		
		ExcelReader reader = new ExcelReader("C:\\My_selenium\\BestBuyAPI.xlsx");
		
		int rowNum = reader.getRowCount("addanddeleteproduct");
		int colNum = reader.getColumnCount("addanddeleteproduct");
		System.out.println(rowNum +"--------"+colNum);
		Object[][] data = new Object[rowNum-1][1];
		
		Hashtable<String , String> table = null;
		
		for(int r =2; r<=rowNum ; r++) {
			
			table = new Hashtable<String , String>();
			
			for(int c=0 ; c<colNum ; c++) {
				table.put(reader.getCellData("addanddeleteproduct", c, 1), reader.getCellData("addanddeleteproduct", c, r));
				data[r-2][0] = table;
			}
			
		}
		
		return data;
		
	}
	
	

}
