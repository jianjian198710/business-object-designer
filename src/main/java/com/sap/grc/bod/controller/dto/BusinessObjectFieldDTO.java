package com.sap.grc.bod.controller.dto;

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

}
