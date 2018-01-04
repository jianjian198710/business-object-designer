package com.sap.grc.bod.controller.dto;

import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;

import lombok.Data;

public @Data class BusinessObjectFieldDTO
{
	private String fieldId;
	private String businessObjectId;
	private String fieldName;
	private BusinessObjectFieldType fieldType;
	private Boolean isCustField;
	private Boolean isMultiInput;
	private Boolean isValueSet;
//	private List<BusinessObjectFieldText> businessObjectFieldTextList;
//	private List<BusinessObjectFieldOption> businessObjectFieldOptionList;
	
	public BusinessObjectField convertToModel(){
		BusinessObjectField businessObjectField = new BusinessObjectField();
		businessObjectField.setBusinessObjectId(this.getBusinessObjectId());
		businessObjectField.setFieldName(this.getFieldName());
		businessObjectField.setFieldType(this.getFieldType());
		businessObjectField.setIsCustField(this.getIsCustField());
		businessObjectField.setIsMultiInput(this.getIsMultiInput());
		businessObjectField.setIsValueSet(this.getIsValueSet());
		return businessObjectField;
	}
}
