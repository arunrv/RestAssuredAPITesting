package jiraAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import TestFramework.payLoad;
import inputs.Payload;
import inputs.Resources;
import inputs.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test1 
{
	@Test
	public void jiraAppTest()
	{
		
		//create issue
		
		RestAssured.baseURI="http://localhost:8030";
		Response response_raw = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID"+"="+ReusableMethods.getSessionCookie("arunrv", "May@2019")).
		body(Payload.getIssuePayloadJira()).
		when().
		post("/rest/api/2/issue").
		then().assertThat().statusCode(201).
		extract().response();
		JsonPath js = ReusableMethods.rawToJson(response_raw);
		String id = js.get("id");
		System.out.println(id);
		
		//add comment
		Response response_raw1 = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID"+"="+ReusableMethods.getSessionCookie("arunrv", "May@2019")).
		body(Payload.getInsertCommentToIssuePayloadJira()).
		when().
		post("/rest/api/2/issue/"+id+"/comment").
		then().assertThat().statusCode(201).
		extract().response();
		JsonPath response_json = ReusableMethods.rawToJson(response_raw1);
		String id_comment = response_json.get("id");
		System.out.println(id_comment);
		Response response_commentupdate = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID"+"="+ReusableMethods.getSessionCookie("arunrv", "May@2019")).
		body(Payload.getUpdateCommentPayloadJira()).
		when().
		put("/rest/api/2/issue/"+id+"/comment/"+id_comment+"").
		then().assertThat().statusCode(200).
		extract().response();
		JsonPath js_commentupdate = ReusableMethods.rawToJson(response_commentupdate);
		String id_grabbed_update=js_commentupdate.get("id");
		System.out.println("IDs are "+id_comment+"    "+id_grabbed_update);
		
		
	}
	

}
