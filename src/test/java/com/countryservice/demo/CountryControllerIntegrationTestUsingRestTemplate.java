package com.countryservice.demo;

import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.bean.CountryResponse;

/**
 * 
 * @author Jaga
 *
 */

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { CountryControllerIntegrationTestUsingRestTemplate.class })
public class CountryControllerIntegrationTestUsingRestTemplate {
	
	private TestRestTemplate restTemplate;
	private HttpHeaders header;
	private HttpEntity<Country> request;
	private HttpEntity<CountryResponse> countryResRequest;
	private ResponseEntity<String> response;
	private Country country;
	private CountryResponse countryResponse;
	private String expectedResponse;
	
	@Test
	@Order(1)
	public void getCountriesIntegrationTest() throws JSONException {
		
		expectedResponse = "[\r\n"
				+ "    {\r\n"
				+ "        \"countryId\": 1,\r\n"
				+ "        \"countryName\": \"India\",\r\n"
				+ "        \"countryCapital\": \"Delhi\",\r\n"
				+ "        \"countryPopulation\": 31000,\r\n"
				+ "        \"countryStates\": 32,\r\n"
				+ "        \"countryLanguage\": \"Tamil\",\r\n"
				+ "        \"countrySport\": \"Kabadi\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"countryId\": 2,\r\n"
				+ "        \"countryName\": \"China\",\r\n"
				+ "        \"countryCapital\": \"Beijing\",\r\n"
				+ "        \"countryPopulation\": 32000,\r\n"
				+ "        \"countryStates\": 30,\r\n"
				+ "        \"countryLanguage\": \"Chinese\",\r\n"
				+ "        \"countrySport\": \"Tennis\"\r\n"
				+ "    }\r\n"
				+ "]";
		
		restTemplate = new TestRestTemplate();
		response = restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	@Test
	@Order(2)
	public void getCountryByIdIntegrationTest() throws JSONException {
		
		expectedResponse = "{\r\n"
				+ "    \"countryId\": 1,\r\n"
				+ "    \"countryName\": \"India\",\r\n"
				+ "    \"countryCapital\": \"Delhi\",\r\n"
				+ "    \"countryPopulation\": 31000,\r\n"
				+ "    \"countryStates\": 32,\r\n"
				+ "    \"countryLanguage\": \"Tamil\",\r\n"
				+ "    \"countrySport\": \"Kabadi\"\r\n"
				+ "}";
		
		restTemplate = new TestRestTemplate();
		response = restTemplate.getForEntity("http://localhost:8080/getcountry/1", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	@Test
	@Order(3)
	public void getCountryByNameIntegrationTest() throws JSONException {
		
		expectedResponse = "{\r\n"
				+ "    \"countryId\": 2,\r\n"
				+ "    \"countryName\": \"China\",\r\n"
				+ "    \"countryCapital\": \"Beijing\",\r\n"
				+ "    \"countryPopulation\": 32000,\r\n"
				+ "    \"countryStates\": 30,\r\n"
				+ "    \"countryLanguage\": \"Chinese\",\r\n"
				+ "    \"countrySport\": \"Tennis\"\r\n"
				+ "}";
		
		restTemplate = new TestRestTemplate();
		response = restTemplate.getForEntity("http://localhost:8080/getcountry/countryname?name=China", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	@Test
	@Order(4)
	public void addCountryIntegrationTest() throws JSONException {
		country = new Country(3, "Japan", "Tokyo", 14000, 23, "Japanese", "Sumo");
		
		expectedResponse = "{\r\n"
				+ "    \"countryName\": \"Japan\",\r\n"
				+ "    \"countryCapital\": \"Tokyo\",\r\n"
				+ "    \"countryPopulation\": 14000,\r\n"
				+ "    \"countryStates\": 23,\r\n"
				+ "    \"countryLanguage\": \"Japanese\",\r\n"
				+ "    \"countrySport\": \"sumo\"\r\n"
				+ "}";
		
		restTemplate = new TestRestTemplate();
		header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		request = new HttpEntity<Country>(country,header);
		response = restTemplate.postForEntity("http://localhost:8080/addcountry",request, String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	@Test
	@Order(5)
	public void updateCountryIntegrationTest() throws JSONException {
		country = new Country(3, "Japan", "Tokyo", 15000, 25, "Japanese", "Sumo");
		
		expectedResponse = "{\r\n"
				+ "    \"countryId\": 3,\r\n"
				+ "    \"countryName\": \"Japan\",\r\n"
				+ "    \"countryCapital\": \"Tokyo\",\r\n"
				+ "    \"countryPopulation\": 15000,\r\n"
				+ "    \"countryStates\": 25,\r\n"
				+ "    \"countryLanguage\": \"Japanese\",\r\n"
				+ "    \"countrySport\": \"Sumo\"\r\n"
				+ "}";
		
		restTemplate = new TestRestTemplate();
		header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		request = new HttpEntity<Country>(country,header);
		response = restTemplate.exchange("http://localhost:8080/updatecountry/3",HttpMethod.PUT,request, String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	@Test
	@Order(6)
	public void deleteCountryIntegrationTest() throws JSONException {
		countryResponse = new CountryResponse(3, "The country is deleted successfully");
		restTemplate = new TestRestTemplate();
		header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		countryResRequest = new HttpEntity<CountryResponse>(countryResponse,header);
		response = restTemplate.exchange("http://localhost:8080/deletecountry/3",HttpMethod.DELETE,countryResRequest, String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
}
