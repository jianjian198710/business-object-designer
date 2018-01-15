package com.sap.grc.bod.controller.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.sap.grc.bod.model.BusinessObjectFieldOption;

import lombok.Data;

public @Data class BusinessObjectFieldOptionDTO
{
	private String fieldOpitonId;
	@NotBlank
	private String fieldId;
	@NotBlank
	private String value;
	
	private String description;
	
	public BusinessObjectFieldOption converToModel(){
		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
		businessObjectFieldOption.setFieldId(this.getFieldId());
		businessObjectFieldOption.setValue(this.getValue());
		businessObjectFieldOption.setDescription(this.getDescription());
		return businessObjectFieldOption;
	}
}
