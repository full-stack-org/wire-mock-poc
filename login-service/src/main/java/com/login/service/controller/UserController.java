package com.login.service.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.service.invoker.LoginServiceInvoker;
import com.login.service.reg.service.FindByUserIdResponse;
import com.login.service.reg.service.RegistrationResponse;
import com.login.service.request.FindByUserRequest;
import com.login.service.request.RegistrationDataRequest;
import com.login.service.util.LoginServiceConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login/v1")
@Slf4j
@Api(value = "/login/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private LoginServiceInvoker loginServiceInvoker;

	@ApiOperation(value = "Register the Customer", notes = "Register the Customer", response = RegistrationResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = LoginServiceConstants.ERROR_CODE_401),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = LoginServiceConstants.ERROR_CODE_403),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = LoginServiceConstants.ERROR_CODE_500),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = LoginServiceConstants.ERROR_CODE_400),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = LoginServiceConstants.ERROR_CODE_404) })
	@PostMapping("/register")
	public RegistrationResponse register(@RequestBody @Valid RegistrationDataRequest registrationRequest) {
		log.info("Entered in register of UserController");

		RegistrationResponse registrationResponse = loginServiceInvoker.registerCustomer(registrationRequest);

		log.info("Exit in register of UserController");
		return registrationResponse;
	}

	@ApiOperation(value = "Find the user by userid", notes = "Find the user by userid", response = FindByUserIdResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = LoginServiceConstants.ERROR_CODE_401),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = LoginServiceConstants.ERROR_CODE_403),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = LoginServiceConstants.ERROR_CODE_500),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = LoginServiceConstants.ERROR_CODE_400),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = LoginServiceConstants.ERROR_CODE_404) })
	@PostMapping("/finByUserId")
	public FindByUserIdResponse finByUserId(@RequestBody @Valid FindByUserRequest findByUserWithIdRequest) {
		log.info("Entered in finByUserId of UserController");

		FindByUserIdResponse findByUserIdResponse = loginServiceInvoker.findByUserId(findByUserWithIdRequest);

		log.info("Exit in finByUserId of UserController");
		return findByUserIdResponse;
	}

}
