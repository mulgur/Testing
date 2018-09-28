package RestAPI.testing;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertThrows;

import org.apache.http.HttpStatus;
import org.omg.CORBA.ParameterModeHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class userAction {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "https://www.batch8-api-site.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";
	}

	@Test
	public void testPublicUserGetOnlyAminProfileInfo() {
		given()
			.relaxedHTTPSValidation()
			//.auth().preemptive().basic("admin", "admin")
		.when()
			.log().all()
			.get("/users")
		.then()
			.assertThat().statusCode(200)     // statusCode(HttpStatus.SC_OK)
	        .header("Content-Type","application/json; charset=UTF-8")            //contentType(ContentType.JSON)
	        .body("id",hasSize(1))
	        .body("name",hasItem("admin"))
	        ;
	        
	}

	
	 @Test
	  public void testPublicUserShouldNotBeAble_CreateNewUser() {
	    
	    given()
	      .relaxedHTTPSValidation().
	      //.auth().preemptive().basic("admin", "admin").
	    when()
	      .log().all()
	      .body("{" +
	          "  \"username\" : \"user_b\" ,\n" + 
	          "  \"name\" : \"user b\" ,\n" + 
	          "  \"first_name\" : \"super b\", \n" + 
	          "  \"last_name\" : \"user last\" ,\n" + 
	          "  \"email\" : \"s@aaa.com\" ,\n" + 
	          "  \"roles\" : \"author\" ,\n" + 
	          "  \"password\" : \"user\" \n" + 
	          "}")
	      .contentType(ContentType.JSON)
	      .post("/users").
	    then()
	      //.statusCode(HttpStatus.SC_UNAUTHORIZED)
	      .statusCode(401) 
	      .contentType(ContentType.JSON)
//	      .header("Content-Type", "application/json; charset=UTF-8")
	      .body("code", is("rest_cannot_create_user") )
//	      .body("name", hasItem("admin") )
//	      
	    ;

	  }
	  
	  
	  @Test
	  public void testAdminUserShouldBeAble_CreateNewUser() {
	    
	    given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("admin", "admin").
	    when()
	      .log().all()
	      .body("{" +
	          "  \"username\" : \"user_c\" ,\n" + 
	          "  \"name\" : \"user c\" ,\n" + 
	          "  \"first_name\" : \"super b\", \n" + 
	          "  \"last_name\" : \"user last\" ,\n" + 
	          "  \"email\" : \"s@aaa.com\" ,\n" + 
	          "  \"roles\" : \"author\" ,\n" + 
	          "  \"password\" : \"user\" \n" + 
	          "}")
	      .contentType(ContentType.JSON)
	      .post("/users").
	    then()
	      //.statusCode(HttpStatus.SC_CREATED)
	        .statusCode(201) 
	      .contentType(ContentType.JSON)
//	      .header("Content-Type", "application/json; charset=UTF-8")
	      .body("username", is("user_c") )
//	      .body("name", hasItem("admin") )
//	      
	    ;

	  }
	
	
	  @Test
		public void adminUser_ShouldBeAbleto_UpdateExistingUser() {
		  given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("admin", "admin")
	      .contentType(ContentType.JSON)
	    .when()
	      .log().all()
	      .body("{\r\n" + 
	      		"	\"first_name\" : \"super updated\", \r\n" + 
	      		"	\"last_name\" : \"user last\" ,\r\n" + 
	      		"	\"email\" : \"s@aagg.com\" ,\r\n" + 
	      		"	\"roles\" : \"author\" ,\r\n" + 
	      		"	\"password\" : \"user\" \r\n" + 
	      		"}")
	      .contentType(ContentType.JSON)
	      .put("/users/10").
	    then()
	        .statusCode(200) 
	      .contentType(ContentType.JSON)	      
	    ;
		}
		
		@Test
		public void adminUser_ShouldBeAbleto_DeleteExistingUser() {
			given()
				.relaxedHTTPSValidation()
				.auth().preemptive().basic("admin", "admin")
				.contentType(ContentType.JSON)
				.pathParam("id",11)
		.when()
		    .queryParam("force", true)   //param("reassing",1)
		    .queryParam("reassign", 1)
			.log().all()
			.delete("/users/{id}")
			//.delete("/users/8")
	    .then()
	    	.statusCode(200)
	    	.body("deleted", is(true))
	    	.body("previous.id",equalTo(11))
	    	.contentType(ContentType.JSON);
		}
		
		@Test
		public void publicUser_ShouldNotBeAbleto_View_ExistingUser_otherThanAdmin() {
			
			 given()
		      .relaxedHTTPSValidation()
		      .auth().preemptive().basic("user1", "user1")
		      .pathParam("id", 7)
		      .contentType(ContentType.JSON)
		    .when()
		      .log().all()
		      .contentType(ContentType.JSON)
		      .get("/users/{id}").
		    then()
		        .statusCode(403) 
		      .contentType(ContentType.JSON)	 
		      .body("message",is("Sorry, you are not allowed to list users."))
		    ;
			 
			 
			 given()
		      .relaxedHTTPSValidation()
		      .auth().preemptive().basic("user1", "user1")
		      .pathParam("id", 1)
		      .contentType(ContentType.JSON)
		    .when()
		      .log().all()
		      .contentType(ContentType.JSON)
		      .get("/users/{id}").
		    then()
		        .statusCode(200) 
		      .contentType(ContentType.JSON)	 
		      .body("id",is(1))
		    ;
		}	

	
	
	
	
	
	
	
	
	
	
	
		 
		
}
