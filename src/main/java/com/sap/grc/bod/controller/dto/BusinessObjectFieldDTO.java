package com.sap.grc.bod.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;

import lombok.Data;

public @Data class BusinessObjectFieldDTO
{
	@NotBlank
	private String name;
	private BusinessObjectFieldType type;
	private Boolean isMandatory;
	private Boolean isVisible;
	private Boolean isCustField;
	private Boolean isMultiInput;
	private Boolean isValueSet;
	@NotNull
	private BusinessObjectFieldTextDTO businessObjectFieldText;
}
