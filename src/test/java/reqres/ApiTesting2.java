//different ways to add data to post requests
package reqres;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;






public class ApiTesting2 
{
	
// 1. using Hashmaps	
	
	@Test
	public void hashMap() 
	{
		HashMap dataMap= new HashMap();
		dataMap.put("name","yash");
		dataMap.put("job", "Solidity");
		
		given().
			contentType("application/json").body(dataMap).
		when().post("https://reqres.in/api/users")
		
		.then().statusCode(201)
		.body("name",equalTo("yash"))
		.body("job",equalTo("Solidity")).log().all();
		
		
	}

	
// 2. Using org.json library
	
	@Test
	public void JsonObjectClass() 
	{
		JsonObject data = new JsonObject();
		data.addProperty("name", "james");
		data.addProperty("job", "jaadu mantralaya");
		
		given()
			.contentType("application/json").body(data)
		
		.when().post("https://reqres.in/api/users")
		
		.then().statusCode(201)
		.body("name", equalTo("james"))
		.body("job", equalTo("jaadu mantralaya"));
		
		
		
		
		
	}

}
