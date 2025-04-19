package reqres;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import org.testng.annotations.Test;

public class ApiTesting1 
{
	int id;
	
	@Test(priority=1)
	public void getapi() 
	{
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then().statusCode(200);
		
		
	}
	
	@Test(priority=2)
	public void postapi() 
	{
		HashMap<String,String> datamap=new HashMap<>();
		datamap.put("name","salman");
		datamap.put("job","tester");
		
		id= 
		given().contentType("application/json").body(datamap)
		
		.when().post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		//.then().statusCode(201).log().all();
		
	}
	
	@Test(priority=3,dependsOnMethods= {"postapi"} )
	public void putapi() 
	{
		
		HashMap<String,String> putdata= new HashMap<>();
		putdata.put("name","akhil");
		putdata.put("job", "developer");
		
		given().contentType("application/json").body(putdata)
		
		.when().put("https://reqres.in/api/users/"+id)
		
		.then().statusCode(200).log().all();
		
	}
	@Test(priority=4,dependsOnMethods={"putapi","postapi"})
	public void deleteapi() 
	{

		when().delete("https://reqres.in/api/users/"+id)
		.then().statusCode(204);
		
		
	}
	
}


