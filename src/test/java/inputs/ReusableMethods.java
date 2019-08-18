package inputs;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods
{
	public static JsonPath rawToJson(Response r)
	{
		String response_string = r.asString();
		JsonPath response_json=new JsonPath(response_string);
		return response_json;
	}
	public static String getSessionCookie(String name, String password)
	{
		RestAssured.baseURI="http://localhost:8030";
		Response response_raw = given().
		header("Content-Type", "application/json").
		body("{ \"username\": \""+name+"\", \"password\": \""+password+"\"}").
		when().
		post("/rest/auth/1/session").
		then().assertThat().statusCode(200).
		extract().response();
		JsonPath js = ReusableMethods.rawToJson(response_raw);
		String session_cookie = js.get("session.value");
		System.out.println(session_cookie);
		return session_cookie;
	}

}
