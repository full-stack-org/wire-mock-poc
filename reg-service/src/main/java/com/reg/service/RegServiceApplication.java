package com.reg.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reg.service.entity.AddressEntity;
import com.reg.service.entity.RegistrationEntity;
import com.reg.service.repository.RegistrationRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RegServiceApplication {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RegServiceApplication.class, args);
	}
	
	@PostConstruct
	public void insertCustomerData() {
		
		RegistrationEntity registrationEntity = new RegistrationEntity();
		registrationEntity.setUserId("test1");
		registrationEntity.setPassword("test1");

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddressLine("Line 1");
		addressEntity.setState("TS");
		addressEntity.setZipcode(12345);

		registrationEntity.setAddressEntity(addressEntity);
		
		RegistrationEntity registrationResponse = registrationRepository.save(registrationEntity);
		
		log.info("Registered Customer User Id is {}", registrationResponse.getUserId());
		
	}

}
