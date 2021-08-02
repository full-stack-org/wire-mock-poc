package com.login.service.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByUserRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4827399461689358753L;
	
	@NotBlank(message = "User Id is Mandatory")
	private String userId;

}
