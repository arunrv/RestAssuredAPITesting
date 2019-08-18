package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import inputs.Payload;
import inputs.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

public class LibraryTest 
{
	@Test
	public void LibraryPostCall()
	{
		RestAssured.baseURI="http://216.10.245.166";
		Response response_raw = given().
		header("Content-Type", "application/json").
		body(Payload.addBookAPI("xyz1abcc", "330")).
		when().
		post("/Library/Addbook.php").
		then().
		assertThat().statusCode(200).contentType(ContentType.JSON).log().all().
		extract().response();
		JsonPath response_json = ReusableMethods.rawToJson(response_raw);
		String msg=response_json.get("Msg");
		Assert.assertEquals(msg, "successfully added");
		String ID = response_json.get("ID");
		//delete option
		Response response = given().
		header("Content-Type", "application/json").
		body(Payload.deleteBook(ID)).log().all().
		when().
		post("/Library/DeleteBook.php").
		then().
		assertThat().statusCode(200).and().body("msg", equalTo("book is successfully deleted")).log().all().
		extract().response();
		String serverValue = response.getHeader("Server");
		System.out.println(serverValue);
		
		
		/*headers();
		String server = header.getValue("Server");
		System.out.println(header);
		System.out.println(server);*/
	}
}