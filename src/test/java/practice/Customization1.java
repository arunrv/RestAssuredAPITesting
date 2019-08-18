package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import inputs.Payload;
import inputs.Resources;
import inputs.ReusableMethods;


public class Customization1 
{
	Properties prop=new Properties();
	@BeforeTest
	public void beforeTestMethod() throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\ARUNR11\\Downloads\\RESTAssuredTest\\RESTAssuredTest\\src\\test\\java\\inputs\\input.properties");
		prop.load(fis);
	}
	@Test
	public void postData()
	{
		RestAssured.baseURI=prop.getProperty("url");
		Response response = given().
		queryParam("key", prop.getProperty("key")).
		body(Payload.getPostData()).
		when().
		post(Resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).body("status", equalTo("OK")).
		extract().response();
		System.out.println("Success");
		JsonPath js = ReusableMethods.rawToJson(response);
		System.out.println(js);
		/*String cleanResponse = response.asString();
		System.out.println(cleanResponse);
		JsonPath js=new JsonPath(cleanResponse);
		System.out.println(js);*/
		String placeid = js.get("place_id");
		System.out.println(placeid);
		
		String jsonstring1="{"+"\"place_id\""+":"+"\""+placeid+"\""+"}";
		Response respost = given().
		queryParam("key", "qaclick123").
		body(jsonstring1).
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).
		extract().response();
		String postresponsestring = respost.asString();
		System.out.println(postresponsestring);
		JsonPath jpathdel=new JsonPath(postresponsestring);
		String status = jpathdel.get("status");
		System.out.println(status);

}
}
