package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

public class Test2 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "500").
		param("key", "AIzaSyDZKbRqWJ8rdsPQHBao9RD5tEzCkVn8Pzs").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().body("results[0].name", equalTo("Sydney")).and().
		body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
		header("Server", "scaffolding on HTTPServer2");
		System.out.println("Status code is 200");
		
	}

}
