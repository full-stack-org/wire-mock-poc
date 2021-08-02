package com.login.service.invoker;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.login.service.reg.service.AddressRequest;
import com.login.service.reg.service.FindByUserIdResponse;
import com.login.service.reg.service.FindByUserWithIdRequest;
import com.login.service.reg.service.RegistrationRequest;
import com.login.service.reg.service.RegistrationResponse;
import com.login.service.request.FindByUserRequest;
import com.login.service.request.RegistrationDataRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LoginServiceInvoker {

	@Autowired
	private WebClient webClient;

	/**
	 * registerCustomer.
	 * 
	 * @param registrationRequest
	 * @return RegistrationResponse
	 */
	public RegistrationResponse registerCustomer(RegistrationDataRequest registrationRequest) {
		log.info("Entered in register of UserController");
		RegistrationResponse registrationResponse = null;
		RegistrationRequest request = buildRegistrationDataRequest(registrationRequest);

		try {

			Mono<RegistrationResponse> monoResponse = webClient.post().uri("/reg/v1/register")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(Mono.just(request), RegistrationResponse.class).retrieve()
					.bodyToMono(RegistrationResponse.class);

			if (Objects.nonNull(monoResponse.block())) {
				registrationResponse = monoResponse.block();
			}

		} catch (Exception e) {
			log.error("Exception while registering the customer {} ", e.getMessage());
		}

		log.info("Exit in register of UserController");
		return registrationResponse;
	}

	/**
	 * findByUserId.
	 * 
	 * @param findByUserWithIdRequest
	 * @return FindByUserIdResponse
	 */
	public FindByUserIdResponse findByUserId(FindByUserRequest findByUserWithIdRequest) {
		log.info("Entered in findByUserId of UserController");

		FindByUserIdResponse findByUserIdResponse = null;
		FindByUserWithIdRequest userWithIdRequest = prepareUserByIdRequest(findByUserWithIdRequest);

		try {
			Mono<FindByUserIdResponse> monoResponse = webClient.post().uri("/find/v1/finByUserId")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(Mono.just(userWithIdRequest), FindByUserIdResponse.class).retrieve()
					.bodyToMono(FindByUserIdResponse.class);

			if (Objects.nonNull(monoResponse.block())) {
				findByUserIdResponse = monoResponse.block();
			}

		} catch (Exception e) {
			log.error("Exception while fetching the customer {} ", e.getMessage());
		}

		log.info("Exit in findByUserId of UserController");
		return findByUserIdResponse;
	}

	/**
	 * prepareUserByIdRequest.
	 * 
	 * @param findByUserWithIdRequest
	 * @return FindByUserWithIdRequest
	 */
	private FindByUserWithIdRequest prepareUserByIdRequest(FindByUserRequest findByUserWithIdRequest) {
		FindByUserWithIdRequest byUserWithIdRequest = new FindByUserWithIdRequest();
		byUserWithIdRequest.setUserId(findByUserWithIdRequest.getUserId());
		return byUserWithIdRequest;
	}

	/**
	 * buildRegistrationDataRequest.
	 * 
	 * @param registrationRequest
	 * @return RegistrationRequest
	 */
	private RegistrationRequest buildRegistrationDataRequest(RegistrationDataRequest registrationRequest) {
		RegistrationRequest serviceRequest = new RegistrationRequest();
		serviceRequest.setUserId(registrationRequest.getUserId());
		serviceRequest.setPassword(registrationRequest.getPassword());

		AddressRequest addressRequest = new AddressRequest();
		addressRequest.setAddressLine(registrationRequest.getAddressRequest().getAddressLine());
		addressRequest.setState(registrationRequest.getAddressRequest().getState());
		addressRequest.setZipcode(registrationRequest.getAddressRequest().getZipcode());

		serviceRequest.setAddressRequest(addressRequest);

		return serviceRequest;
	}

}
