package RestAPI.testing;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import org.hamcrest.Matchers;

public class RestTest {
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://www.batch8-api-site.dev.cc/wp-json";
		RestAssured.basePath ="/wp/v2";
	}

	@Test
	public void firstTest() {
		// Given rest end point "http://73.166.37.2:1000/ords/hr/countries/"
		// When I send HTTP Get request to the server
		// Then I should get 200 OK result as status code (edited)
		when()
		.get("http://73.166.37.2:1000/ords/hr/employees/100")
		.then().statusCode(200);

	}
	
	
	
	@Test
	public void secondTest() {
		// Given rest end point "http://73.166.37.2:1000/ords/hr/countries/"
		// When I send HTTP Get request to the server
		// Then I should get 200 OK result as status code (edited)
		given().relaxedHTTPSValidation()
		.when()
		.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts")
		.then().statusCode(200);

	}
	
	
	
	@Test
	public void idTest() {
		// Given rest end point "https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts"
		// When I send HTTP Get request to the server
		// Then I should get 200 OK result as status code (edited)
		//and id field should be 15
		given().relaxedHTTPSValidation()
		.when()
		.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/5")
		.then().statusCode(200)
		.and().body("id",equalTo(5))
		.body("title.rendered", equalTo("my awesome title"));

	}
	
	
//	.get("http://73.166.37.2:1000/ords/hr/jobs/ACXZ_DEV")
	
	@Test
	public void idTest_withLogDetail() {

		given().relaxedHTTPSValidation()
		.when()
		.log().all()
		.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/1")
		.then()
		.log().all()
		.statusCode(200)
		.and().body("id",equalTo(1))
		.body("title.rendered", equalTo("Hello world!"));

	}
	
	
	
	@Test
	public void idTest_withLogDetail2() {

		String s="https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/";
		given().relaxedHTTPSValidation()
		.when()
		.log().all()
		.get("https://www.batch8-api-site.dev.cc/wp-json/wp/v2/posts/{id}",5)
		.then()
		.log().all()
		.statusCode(200)
		.and().body("id",equalTo(5))
		.body("title.rendered", equalTo("my awesome title"));

	}
	
	
	@Test
	public void Four_Test() {


		given()
			.relaxedHTTPSValidation()	
		.when()
			.get("/posts")
		.then()
			.assertThat().statusCode(200) ; 
		
	}
	
	
	
	

}
