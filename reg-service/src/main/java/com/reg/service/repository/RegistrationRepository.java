package com.reg.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reg.service.entity.RegistrationEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {

	RegistrationEntity findByUserId(String userId);

}
