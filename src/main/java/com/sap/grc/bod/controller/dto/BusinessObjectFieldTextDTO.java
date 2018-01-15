package com.sap.grc.bod.controller.dto;

import java.io.Serializable;
import java.util.UUID;

import com.sap.grc.bod.model.BusinessObjectFieldText;

import lombok.Data;

public @Data class BusinessObjectFieldTextDTO implements Serializable
{

	private static final long serialVersionUID = 6768271770288156432L;
	
	private String languageId;
	private String fieldShortDescription;
	private String description;
	
	public BusinessObjectFieldText convertToModel(){
		BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
		//businessObjectFieldText.setFieldId(this.getFieldId());
		businessObjectFieldText.setLanguageId(this.getLanguageId());
		businessObjectFieldText.setFieldShortDescription(this.getFieldShortDescription());
		businessObjectFieldText.setDescription(this.getDescription());
		return businessObjectFieldText;
	}
}
