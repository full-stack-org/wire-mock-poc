package com.reg.service.invoker;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reg.service.entity.AddressEntity;
import com.reg.service.entity.RegistrationEntity;
import com.reg.service.repository.RegistrationRepository;
import com.reg.service.request.FindByUserWithIdRequest;
import com.reg.service.request.RegistrationRequest;
import com.reg.service.response.FindByUserIdResponse;
import com.reg.service.response.RegistrationResponse;
import com.reg.service.response.StatusResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationServiceInvoker {

	@Autowired
	private RegistrationRepository registrationRepository;

	/**
	 * registerCustomer.
	 * 
	 * @param registrationRequest
	 * @return RegistrationResponse
	 */
	public RegistrationResponse registerCustomer(RegistrationRequest registrationRequest) {
		log.info("Entered in registerCustomer of RegistrationServiceInvoker");
		long start = System.currentTimeMillis();

		RegistrationResponse registrationResponse = null;
		RegistrationEntity registrationEntity = prepareServiceRequest(registrationRequest);
		try {
			RegistrationEntity serviceEntity = registrationRepository.save(registrationEntity);

			if (Objects.nonNull(serviceEntity)) {
				registrationResponse = trannformServiceResponse(serviceEntity);
			}

		} catch (Exception e) {
			registrationResponse = new RegistrationResponse();
			StatusResponse statusResponse = new StatusResponse();
			statusResponse.setStatusCode(200);
			statusResponse.setStatusMessage("Customer registered Successfully");
			registrationResponse.setStatusResponse(statusResponse);

			log.error("Exception while registering customer {}", e.getMessage());
		}

		long end = System.currentTimeMillis();
		log.info("Time taken to register customer in registerCustomer {}", end - start);
		log.info("Entered in registerCustomer of RegistrationServiceInvoker");

		return registrationResponse;
	}

	/**
	 * findByUserId
	 * 
	 * @param findByUserWithIdRequest
	 * @return FindByUserIdResponse
	 */
	public FindByUserIdResponse findByUserId(FindByUserWithIdRequest findByUserWithIdRequest) {
		log.info("Entered in findByUserId of RegistrationServiceInvoker");
		long start = System.currentTimeMillis();

		FindByUserIdResponse findByUserIdResponse = null;
		try {

			RegistrationEntity serviceEntity = registrationRepository.findByUserId(findByUserWithIdRequest.getUserId());

			if (Objects.nonNull(serviceEntity)) {
				findByUserIdResponse = transformFindByUserResponse(serviceEntity);
			}

		} catch (Exception e) {
			findByUserIdResponse = new FindByUserIdResponse();
			StatusResponse statusResponse = new StatusResponse();
			statusResponse.setStatusCode(200);
			statusResponse.setStatusMessage("Error while fetching the customer details");
			findByUserIdResponse.setStatusResponse(statusResponse);

			log.error("Exception while fetching the customer details {}", e.getMessage());
		}

		long end = System.currentTimeMillis();
		log.info("Time taken to get the customer details {}", end - start);
		log.info("Entered in findByUserId of RegistrationServiceInvoker");

		return findByUserIdResponse;
	}

	/**
	 * transformFindByUserResponse.
	 * 
	 * @param serviceEntity
	 * @return FindByUserIdResponse
	 */
	private FindByUserIdResponse transformFindByUserResponse(RegistrationEntity serviceEntity) {
		FindByUserIdResponse findByUserIdResponse = new FindByUserIdResponse();
		findByUserIdResponse.setUserId(serviceEntity.getUserId());
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatusCode(200);
		statusResponse.setStatusMessage("Customer details fetched Successfully");
		findByUserIdResponse.setStatusResponse(statusResponse);
		return findByUserIdResponse;
	}

	/**
	 * trannformServiceResponse.
	 * 
	 * @param serviceEntity
	 * @return RegistrationResponse
	 */
	private RegistrationResponse trannformServiceResponse(RegistrationEntity serviceEntity) {
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setUserId(serviceEntity.getUserId());
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatusCode(200);
		statusResponse.setStatusMessage("Customer registered Successfully");
		registrationResponse.setStatusResponse(statusResponse);

		return registrationResponse;
	}

	/**
	 * prepareServiceRequest.
	 * 
	 * @param registrationRequest
	 * @return RegistrationEntity
	 */
	private RegistrationEntity prepareServiceRequest(RegistrationRequest registrationRequest) {
		RegistrationEntity registrationEntity = new RegistrationEntity();
		registrationEntity.setUserId(registrationRequest.getUserId());
		registrationEntity.setPassword(registrationRequest.getPassword());

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddressLine(registrationRequest.getAddressRequest().getAddressLine());
		addressEntity.setState(registrationRequest.getAddressRequest().getState());
		addressEntity.setZipcode(registrationRequest.getAddressRequest().getZipcode());

		registrationEntity.setAddressEntity(addressEntity);

		return registrationEntity;
	}

}
