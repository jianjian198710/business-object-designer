package com.sap.grc.bod.controller.dto;

import java.util.List;

import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.model.BusinessObjectFieldValueSet;

import lombok.Data;

public @Data class BusinessObjectFieldDTO
{
	private String businessObjectId;
	private String fieldId;
	private String fieldType;
	private boolean isCustField;
	private boolean isMultiInput;
	private boolean isValueSet;
	private List<BusinessObjectFieldText> businessObjectFieldTextList;
	private BusinessObject businessObject;
	private List<BusinessObjectFieldValueSet> businessObjectFieldValueSetList;
}
