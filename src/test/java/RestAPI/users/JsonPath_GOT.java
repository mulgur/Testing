package RestAPI.users;


import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPath_GOT {
  
  /*
   * use this end point -- GET https://api.got.show/api/characters/
   * get only the characters belong to house stark  tips -- use findAll method 
   * and get the count of it 
   * and print out as String 
   * */
  
  @Test
  public void testJsonPath() {
	

			Response response =

					given().relaxedHTTPSValidation().accept(ContentType.JSON).when()
							.get("https://api.got.show/api/characters/");

			String responseString = response.asString();
			System.out.println(responseString);

			// JsonPath jp = response.jsonPath();
			JsonPath jp = JsonPath.from(responseString);
			
			System.out.println(jp.getString("house[0]"));
			
			List<String> starks = jp.getList("findAll{it.house=='House Stark'}.name", String.class);
			
			System.out.println(starks.size());

//	 System.out.println(jp.getList("findAll{ it.house==''}") ); 
//	System.out.println(jp.getList("findAll{ it.house==''}").size());
//    System.out.println(jp.getString("findAll{ it.house==''}"));
    
			 
			 
			 
  }
  
  
  
  

}