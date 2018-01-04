package com.sap.grc.bod.controller.dto;

import com.sap.grc.bod.model.BusinessObjectFieldText;

import lombok.Data;

public @Data class BusinessObjectFieldTextDTO
{
	private String fieldTextId;
	private String fieldId;
	private String languageId;
	private String fieldShortDescription;
	private String description;
	
	public BusinessObjectFieldText convertToModel(){
		BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
		businessObjectFieldText.setFieldId(this.getFieldId());
		businessObjectFieldText.setLanguageId(this.getLanguageId());
		businessObjectFieldText.setFieldShortDescription(this.getFieldShortDescription());
		businessObjectFieldText.setDescription(this.getDescription());
		return businessObjectFieldText;
	}
}
