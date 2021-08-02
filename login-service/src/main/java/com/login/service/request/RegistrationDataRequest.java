package com.login.service.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDataRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233988437313234724L;

	@NotBlank(message = "User Id is Mandatory")
	private String userId;

	@NotBlank(message = "Password is Mandatory")
	private String password;

	@Valid
	@NotNull(message = "Address details are Mandatory")
	private AddressDataRequest addressRequest;
	
}
