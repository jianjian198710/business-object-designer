package com.sap.grc.bod.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sap.grc.bod.model.BusinessObjectFieldOption;

import lombok.Data;

public @Data class BusinessObjectFieldOptionDTO
{
	private String fieldOpitonId;
	@NotBlank
	private String value;
	@NotNull
	private BusinessObjectFieldOptionTextDTO businessObjectFieldOptionTextDTO;
	
	public BusinessObjectFieldOption converToModel(){
		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
		businessObjectFieldOption.setValue(this.getValue());
		return businessObjectFieldOption;
	}
}
