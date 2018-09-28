package RestAPI.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONPath {

	Faker faker = new Faker();
	int newUserID;

	@BeforeClass
	public void init() {

		RestAssured.baseURI = "https://www.batch8-api-site.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";

	}

	@Test
	public void testJSONPath() {

		Response response =

				given().relaxedHTTPSValidation().
				// .auth().preemptive().basic("admin", "admin").
						when().log().all().get("/users/{id}", 1);

		System.out.println(response.asString());
		response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();

		System.out.println("id :" + jsonPath.getInt("id"));
		// title , slug , self

		System.out.println("slug : " + jsonPath.getString("slug"));
		System.out.println("self : " + jsonPath.getString("_links.self[0].href"));
		System.out.println("link : " + jsonPath.getString("link"));

		;

	}

	@Test
	public void driveninfoTest() {

		Response response =

				given().relaxedHTTPSValidation().
				// .auth().preemptive().basic("admin", "admin").
						when().log().all().get("http://ergast.com/api/f1/drivers.json");

		JsonPath jpath = response.jsonPath();
		String str = "MRData.DriverTable.driver[1].givenName";
		// System.out.println(jpath.get(str));
		assertThat(str, equalToIgnoringCase("george"));
		// assertThat(str,equalTo("george"));

	}

}