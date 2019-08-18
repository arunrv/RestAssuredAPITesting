package practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Logging 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		Response response_raw = given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "500").
		param("key", "AIzaSyDZKbRqWJ8rdsPQHBao9RD5tEzCkVn8Pzs").log().all().
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().
		statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).log().all().
		extract().
		response();
		String response_string = response_raw.asString();
		JsonPath response_json=new JsonPath(response_string);
		int count=response_json.get("results.size()");
		System.out.println(response_json.get("results[0].name"));
		System.out.println(count);
		for(int i=0;i<count;i++)
		{
			String name=response_json.get("results["+i+"].name");
			System.out.println(name);
		}
	}

}
