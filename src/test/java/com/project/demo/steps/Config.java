package com.project.demo.steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Config {

	@Before
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.basePath = "/api";

	}
}
