package com.login.service.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDataRequest {
	@NotBlank(message = "Address Line is Mandatory")
	private String addressLine;

	@NotBlank(message = "State is Mandatory")
	private String state;

	@Min(value = 1)
	private int zipcode;
}
