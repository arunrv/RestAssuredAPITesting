package practice;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

public class DlaOnJob 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://qa-dlaqaapi.fof-fuso.com";
		Response resp_raw = given().
				pathParam("id", "area1").
		when().
		get("/area/{id}").
		then().assertThat().statusCode(200).
		extract().response();
		String resp_string = resp_raw.asString();
		System.out.println(resp_string);
		
	}

}
