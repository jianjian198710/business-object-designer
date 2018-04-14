package com.sap.grc.bod.controller.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public @Data class BusinessObjectFieldTextDTO implements Serializable
{

	private static final long serialVersionUID = 6768271770288156432L;

	@NotBlank
	private String name;
	private String description;
	
}
