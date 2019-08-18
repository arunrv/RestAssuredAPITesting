package practice;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Test3 
{
	@Test
	public void postData()
	{
		RestAssured.baseURI="http://216.10.245.166";
		Response response = given().
		queryParam("key", "qaclick123").
		body("{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Google Shoes!\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"shoe_store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
				"}").
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).body("status", equalTo("OK")).
		extract().response();
		System.out.println("Success");
		String cleanResponse = response.asString();
		System.out.println(cleanResponse);
		JsonPath js=new JsonPath(cleanResponse);
		System.out.println(js);
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
		
		//in this program, we are doing the post call then get the response. Verify the
		//response, extract the raw response, convert into string, convert string into
		//json, Grab the id in json response, now construct json and insert that id into the delete call
		//json as a payload, again get the response and verify the delete. 	
	}

}
