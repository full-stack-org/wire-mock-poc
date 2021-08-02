package com.login.service;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.login.service.reg.service.AddressRequest;
import com.login.service.reg.service.FindByUserWithIdRequest;
import com.login.service.reg.service.RegistrationRequest;

import io.restassured.http.ContentType;

public class ApiIntegrationTest {

	// Here Url will be u and running wire mock server url
	private static final String baseUrl = Optional.ofNullable(System.getProperty("baseUrl"))
			.orElse("http://localhost:8082");

	@Test
	@DisplayName("testFindByUserIdSuccess")
	void testFindByUserIdSuccess() {
		FindByUserWithIdRequest findByUserWithIdRequest = new FindByUserWithIdRequest();
		findByUserWithIdRequest.setUserId("test1");

		given().body(findByUserWithIdRequest).contentType(ContentType.JSON).when()
				.post(baseUrl + "/login/v1/finByUserId").then().body("userId", equalTo("test1"));
	}

	@Test
	@DisplayName("testRegisterCustomer")
	void testRegisterCustomer() {
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setUserId("test");
		registrationRequest.setPassword("pass123");

		AddressRequest addressRequest = new AddressRequest();
		addressRequest.setAddressLine("Line 2");
		addressRequest.setState("AP");
		addressRequest.setZipcode(5223);

		registrationRequest.setAddressRequest(addressRequest);

		given().body(registrationRequest).contentType(ContentType.JSON).when().post(baseUrl + "/login/v1/register")
				.then().body("userId", equalTo("test"));

	}

	@Test
	@DisplayName("Sample Test")
	void testSample() {
		assertEquals(200, 200);

	}
}
