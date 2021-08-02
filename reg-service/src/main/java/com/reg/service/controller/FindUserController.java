package com.reg.service.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reg.service.invoker.RegistrationServiceInvoker;
import com.reg.service.request.FindByUserWithIdRequest;
import com.reg.service.response.FindByUserIdResponse;
import com.reg.service.util.RegisrationConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/find/v1")
@Slf4j
@Api(value = "/find/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
public class FindUserController {

	@Autowired
	private RegistrationServiceInvoker registrationServiceInvoker;

	@ApiOperation(value = "Find the user by userid", notes = "Find the user by userid", response = FindByUserIdResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = RegisrationConstants.ERROR_CODE_401),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = RegisrationConstants.ERROR_CODE_403),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = RegisrationConstants.ERROR_CODE_500),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = RegisrationConstants.ERROR_CODE_400),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = RegisrationConstants.ERROR_CODE_404) })
	@PostMapping("/finByUserId")
	public FindByUserIdResponse finByUserId(@RequestBody @Valid FindByUserWithIdRequest findByUserWithIdRequest) {
		log.info("Entered in finByUserId of RegistrationController");

		FindByUserIdResponse findByUserIdResponse = registrationServiceInvoker.findByUserId(findByUserWithIdRequest);

		log.info("Exit in finByUserId of RegistrationController");
		return findByUserIdResponse;
	}
	
}
