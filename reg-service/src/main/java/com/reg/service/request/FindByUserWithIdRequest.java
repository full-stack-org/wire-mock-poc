package com.reg.service.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByUserWithIdRequest implements Serializable { 
	
	private static final long serialVersionUID = -1319071976212222672L;
	
	@NotBlank(message = "User Id is Mandatory")
	private String userId;
}
