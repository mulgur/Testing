package RestAPI.testing;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestApi {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://www.batch8-api-site.dev.cc/wp-json";
		RestAssured.basePath ="/wp/v2";
	}

	
	@Test
	public void simpleGetRequest() {
		given().relaxedHTTPSValidation()
		.when()
		  .log().all()
		.get("/posts")
		  .then()
		.log().ifValidationFails()
		.assertThat().statusCode(200);
	}
	
	@Test
	public void idTest_withLogDetail2() {

		given().relaxedHTTPSValidation()
		.when()
			.log().all()
			//.get("/posts/{id}",5)
		    .get("/posts/5")
		.then()
			.log().all()
			.statusCode(200)
			.and()
			.body("id", equalTo(5))
			.body("title.rendered", equalTo("my awesome title"))
			.body("sticky", is(false));

	}

	
	@Test
	public void simplePostTest() {
		given().relaxedHTTPSValidation()
			
			.contentType(ContentType.JSON)
		.when()
			.body("{ \"name\":\"hi\" }")
			.pathParam("newID",15)
			.post("/posts/{newID}")
				
			
			
			
		.then()
			.log().all()
			.statusCode(200)
			.body("title.raw", equalTo("API day 3"));
		
	}
	
	
	@Test
	public void simpleDeleteTest() {
		given().relaxedHTTPSValidation()
			.auth().preemptive().basic("admin", "admin")
			.contentType(ContentType.JSON)
		.when()
		    .pathParam("deleteID", 15)
		    .queryParam("force", true)
			//.log().all()
			.delete("/posts/{deleteID}")
			//.delete("/posts/15")
	    .then()
	    	.statusCode(200)
	    	.body("deleted", is(true));
				
	}
}
