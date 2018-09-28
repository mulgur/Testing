package RestAPI.testing;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class test2 {
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://www.batch8-api-site.dev.cc/wp-json";
		RestAssured.basePath ="/wp/v2";
	}
	
	@Test
	public void firstTest() {
		given().relaxedHTTPSValidation().
		when()
		.get("/posts/15")
		.then().statusCode(401);

	}

	@Test
	public void test2() {
		given().relaxedHTTPSValidation().
		when()
		.get("/posts/21")
		.then().statusCode(404);

	}
	
	
	@Test
	public void test3() {
		given().relaxedHTTPSValidation().
		when()
		.get("/posts/35")
		.then().statusCode(200);

	}
	@Test
	public void test4() {
		given().relaxedHTTPSValidation().
		when()
		.get("/posts/43")
		.then().statusCode(404);
		
	}
}
