package com.sap.grc.bod.controller.dto;

import com.sap.grc.bod.model.BusinessObjectFieldOption;

import lombok.Data;

public @Data class BusinessObjectFieldOptionDTO
{
	private String fieldOpitonId;
	private String fieldId;
	private String languageId;
	private String value;
	private String description;
	
	public BusinessObjectFieldOption converToModel(){
		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
		businessObjectFieldOption.setFieldId(this.getFieldId());
		businessObjectFieldOption.setLanguageId(this.getLanguageId());
		businessObjectFieldOption.setValue(this.getValue());
		businessObjectFieldOption.setDescription(this.getDescription());
		return businessObjectFieldOption;
	}
}
