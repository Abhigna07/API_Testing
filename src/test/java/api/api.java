package api;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class api {
  @Test
  
  public void getApi() {
	  
	  RequestSpecification request= RestAssured.given();
	  request.baseUri("https://restful-booker.herokuapp.com/booking");
	  Response response = request.get();
	  
	  String resString = response.asString();
	  System.out.println("Respnse Details : " + resString);
	  
	  ValidatableResponse valRes = response.then();
	  valRes.statusCode(200);
	  System.out.print("Stopped");
	  valRes.statusLine("HTTP/1.1 200 OK");
	  
  }
  
  @Test
  
 public void postApi()
 {
	String Jsonstring ="{\"username\" : \"admin\",\"password\" : \"password123\"}";
	RequestSpecification specification = RestAssured.given();
	specification.contentType(ContentType.JSON);
	specification.baseUri("https://restful-booker.herokuapp.com/auth");
	specification.body(Jsonstring);
	Response resp= specification.post();
	System.out.println(resp.asString());
	 
	ValidatableResponse valres = resp.then();
	valres.statusCode(200);
    valres.body("token", Matchers.notNullValue());
    valres.body("token.length()", Matchers.is(15));
    valres.body("token", Matchers.matchesRegex("^[a-z0-9]+$"));
	
}
  
  
  @Test
  
  public void putapi()
  {
	  
	  String jsonString = "{\r\n" + "    \"firstname\" : \"Amod\",\r\n" + "    \"lastname\" : \"Mahajan\",\r\n"
				+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n" + "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

	  RequestSpecification request = RestAssured.given();
	  request.contentType(ContentType.JSON);
	  request.cookie("token", "9faae49eda5b501");
	  request.baseUri("https://restful-booker.herokuapp.com/booking/1");
	  request.body(jsonString);
	  Response response = request.put();
	  System.out.println(response.asString());
	  ValidatableResponse validatableResponse = response.then();
	  validatableResponse.statusCode(200);
	  validatableResponse.body("firstname", Matchers.equalTo("Amod"));
	  validatableResponse.body("lastname", Matchers.equalTo("Mahajan"));
	  
	  }
  
  @Test
  
  public void patchapi()
  {
	  
	  String jsonString = "{\r\n" + 
				"    \"firstname\" : \"Amod\",\r\n" + 
				"    \"lastname\" : \"Mahajan\"}";
	  
    RequestSpecification request = RestAssured.given();
    request.contentType(ContentType.JSON);
    request.cookie("token","fb18d4df4e7276f");
    request.baseUri("https://restful-booker.herokuapp.com/booking/2");
    request.body(jsonString);
    Response response = request.patch();
    System.out.println(response.asString());
    ValidatableResponse validatableresponse = response.then();
    validatableresponse.statusCode(200);
    validatableresponse.body("firstname", Matchers.equalTo("Amod"));
    validatableresponse.body("lastname", Matchers.equalTo("Mahajan"));
	  
  }
  
  
  @Test
  
  public void deleteapi() throws InterruptedException
  {
	  
	  
	  RequestSpecification requestspecification = RestAssured.given();
	  requestspecification.cookie("token","4c70e406adf0e72");
	  requestspecification.baseUri("https://restful-booker.herokuapp.com/booking/3");
	  Response response= requestspecification.delete();
	  ValidatableResponse validatableresponse = response.then();
	  validatableresponse.statusCode(201);
	  Thread.sleep(5000);
	  RequestSpecification request = RestAssured.given();
	  request.baseUri("https://restful-booker.herokuapp.com/booking/3");
	  Response resp= request.get();
	  ValidatableResponse valresp =resp.then();
	  valresp.statusCode(404);
	  
	  
  }
 
 
}
