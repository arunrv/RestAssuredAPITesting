package inputs;

public class Payload 
{
	public static String getPostData()
	{
		String payload="{"+
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
				"}";
		return payload;
		
	}
	public static String addBookAPI(String isbn, String aisle)
	{
		String payload="{\r\n\"name\":\"Learn Spring Boot and JPA\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\"Rahul Shetty\"\r\n}";
		return payload;
	}
	
	public static String deleteBook(String id)
	{
		String payload="{\r\n\"ID\" : \""+id+"\"\r\n}";
		return payload;
	}
	
	public static String getIssuePayloadJira()
	{
		String payload="{\r\n\t\"fields\": {\r\n\t\t\"project\": {\r\n\t\t\t\"key\": \"TES\"\r\n\t\t},\r\n\t\t\"summary\": \"AutomateRestAPI\",\r\n\t\t\"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n\t\t\"issuetype\": {\r\n\t\t\t\"name\": \"Bug\"\r\n\t\t}\r\n\t}\r\n}";
		return payload;
	}
	public static String getInsertCommentToIssuePayloadJira()
	{
		String payload="{\r\n\t\"body\": \"This is a added by rest API Automation Program.\"\r\n}";
		return payload;
	}
	public static String getUpdateCommentPayloadJira()
	{
		String payload="{\r\n\t\"body\": \"This comment is updated by rest API Automation Program.\"\r\n}";
		return payload;
	}
}
