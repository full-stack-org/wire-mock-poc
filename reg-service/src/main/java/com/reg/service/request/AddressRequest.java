package com.reg.service.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7596290597384171018L;

	@NotBlank(message = "Address Line is Mandatory")
	private String addressLine;

	@NotBlank(message = "State is Mandatory")
	private String state;

	@Min(value = 1)
	private int zipcode;

}
