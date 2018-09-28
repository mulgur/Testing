package RestAPI.testing;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import RestAPI.users.Driver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPath_PRACTICE2 {
//
//	Faker faker = new Faker();
//	int newUserID;
//
//	@BeforeClass
//	public void init() {
//
//		RestAssured.baseURI = "https://www.batch8-api-site.dev.cc";
//		RestAssured.basePath = "/wp-json/wp/v2";
//		RestAssured.useRelaxedHTTPSValidation();
//
//	}
//
//	@Test
//	public void simpleGetRequest() {
//
//		Response response =
//
//				given()
//						// .relaxedHTTPSValidation()
//						.when().log().all()
//						// .queryParam("per_page",2)
//						.get("/posts")
//
//		;
//
//		JsonPath jp = response.jsonPath();
//		// USE JSON PATH TO FIND OUT FIRST AUTHOR
//		// System.out.println( jp.getInt("[0].author") );
//		System.out.println(jp.getInt("author[0]"));
//
//		List<Object> lst = jp.getList("author");
//
//		List<Integer> lstNum = jp.getList("author", Integer.class);
//		System.out.println(lstNum);
//
//		List<String> titles = jp.getList("title.rendered", String.class);
//
//		//
//		// List<Integer> lstCount =
//		// jp.getList("_links.version-history[0].count",Integer.class) ;
//		List<Object> lstCount = jp.getList("_links.version-history.count");
//
//		System.out.println(lstCount);
//
//		// USE JSON PATH TO FIND OUT ALL OF THE AUTHOR
//		// USE JSON PATH TO FIND OUT version-history count
//
//	}
//
//	@Test
//	public void getAllDriverFirstName() {
//
//		Response res = given().when().get("http://ergast.com/api/f1/drivers.json");
//
//		JsonPath jp = res.jsonPath();
//		List<String> lstGivenName = jp.getList("MRData.DriverTable.Drivers.givenName", String.class);
//
//		System.out.println(lstGivenName);
//
//		// System.out.println(jp.get("MRData.DriverTable.Drivers[0]") );
//
//		Map map1 = jp.getMap("MRData.DriverTable.Drivers[0]");
//		System.out.println(map1);
//		System.out.println(map1.keySet());
//
//		Map<String, String> map2 = jp.getMap("accountType[0]", String.class, String.class);
//		System.out.println(map2.values());
//
//		System.out.println(jp.getString("MRData.DriverTable.Drivers[1].givenName"));
//
//		// JSONPATH That rest assured use is the GPath from groovy
//		// Predicate
//		System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{ it.givenName=='George'}"));
//
//		// this is similar to java way
//		List<Object> lst4 = jp.getList("MRData.DriverTable.Drivers.findAll{ whatever-> whatever.givenName=='George'}");
//		System.out.println(lst4);
//
//		// System.out.println(
//		// jp.get("MRData.DriverTable.Drivers.findAll{ it.givenName=='George' &&
//		// it.nationality=='American' }") );
//
//		System.out.println();
//
//		List<Object> lst5 = jp.getList("MRData.DriverTable.Drivers.findAll{it.givenName.length()==3} ");
//
//		List<Object> lst6 = jp
//				.getList("MRData.DriverTable.Drivers.findAll{  driver-> driver.givenName.length()==3 }.familyName ");
//
//		System.out.println(lst6);
//
//		// find out all the driver that has 3 letters given name it.givenName.length()
//
//		// single json object ---> Driver object in java
//		// Data binding ---> Binding Json field to POJO Field
//
//		Driver driverObj = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class);
//		System.out.println(driverObj);
//		System.out.println("------------");
//		Map reqperson = new HashMap();
//		reqperson.put("driverId", "abate");
//		reqperson.put("url", "http://en.wikipedia.org/wiki/Carlo_Mario_Abate");
//		reqperson.put("givenName", "Carlo");
//		reqperson.put("familyName", "Abate");
//		reqperson.put("dateOfBirth", "1932-07-10");
//		reqperson.put("nationality", "Italian");
//
//		System.out.println(reqperson);
//
//		System.out.println("------------");
//
//	}
//
//	@Test
//	public void driver() throws Exception {
//		String JsonStringArr = "[\n" + "	{\n" + "		\"driverId\":\"unlimit\",\n"
//				+ "		\"url\":\"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\",\n"
//				+ "		\"givenName\":\"Carlo\",\n" + "		\"familyName\":\"sdfasdf\",\n"
//				+ "		\"dateOfBirth\":\"1932-07-10\",\n" + "		\"nationality\":\"Italian\n" + "	}\n" + "]";
//
//		ObjectMapper om = new ObjectMapper();
//		Driver[] drivers = om.readValue(JsonStringArr, Driver[].class);
//		System.out.println(Arrays.toString(drivers));
//
//	}
//
//	@Test
//	public void PostAMedia() {
//		
//		Map<String,String> mp = new HashMap<>();
//		mp.put("media_type","image");
//		mp.put("title","your title");
//		mp.put("caption","your caption");
//		mp.put("status","publish");
//		
//		File f = new File("/Your/Own/Path/To/png/imageFile.png") ; 
//		
//		given()
//			.relaxedHTTPSValidation()
//			.auth().preemptive().basic("admin","admin").
//		when()
//		 .accept(ContentType.JSON)
//		 //.contentType("multiPart/formdata")
//		 .multiPart("file", f, "image/png")
////		 .formParam("media_type","image")
////		 .formParam("title","your own title")
////		 .formParam("caption","your own caption")
////		 .formParam("status","publish")
//		 .formParams(mp)  // you can use either map or formParam string
//		 
//		.post("/media").
//		then()
//			.statusCode(201)
//
//		 ;
//
//		
//	}
public static void main(String[] args) {
	
    int number=8;
	for(int j = 1;j<number;j++)
	{
		for (int i = 1; i < j; i++) {
			if (i % 2 == 1)
				System.out.print("#");
			else {
				System.out.print("@");
			}
			
		}
		System.out.println();
	}
}








}
