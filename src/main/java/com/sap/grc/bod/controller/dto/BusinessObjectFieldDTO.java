package com.sap.grc.bod.controller.dto;

import java.util.List;

import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;

import lombok.Data;

public @Data class BusinessObjectFieldDTO
{
	private String uuid;
	private String businessObjectId;
	private String name;
	private BusinessObjectFieldType type;
	private Boolean isCustField;
	private Boolean isMultiInput;
	private Boolean isValueSet;
	private BusinessObjectFieldTextDTO businessObjectFieldText;
//	private List<BusinessObjectFieldText> businessObjectFieldTextList;
//	private List<BusinessObjectFieldOption> businessObjectFieldOptionList;
	
	/*public BusinessObjectField convertToModel(){
		BusinessObjectField businessObjectField = new BusinessObjectField();
		businessObjectField.setBusinessObjectId(this.getBusinessObjectId());
		businessObjectField.setName(this.getFieldName());
		businessObjectField.setType(this.getFieldType());
		businessObjectField.setIsCustField(this.getIsCustField());
		businessObjectField.setIsMultiInput(this.getIsMultiInput());
		businessObjectField.setIsValueSet(this.getIsValueSet());
		return businessObjectField;
	}*/
}
