package com.reg.service.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "User Id is Mandatory")
	private String userId;

	@NotBlank(message = "Password is Mandatory")
	private String password;

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, targetEntity = AddressEntity.class)
	private AddressEntity addressEntity;

}
