package com.sap.grc.bod.service;

import java.util.List;
import java.util.UUID;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.model.BusinessObjectFieldText;

public interface BusinessObjectFieldTextService
{
	public BusinessObjectFieldText createBusinessObjectFieldText(String fieldId, BusinessObjectFieldTextDTO businessObjectFieldTextDTO);
	public List<BusinessObjectFieldText> updateMultiBusinessObjectFieldText(List<String> fieldTextIdList, List<BusinessObjectFieldTextDTO> businessObjectFieldTextDTOList);
	public BusinessObjectFieldText findOneBusinessObjectFieldText(String fieldId, String languageId);
//	public List<BusinessObjectFieldText> findAllBusinessObjectFieldText(String businessObjectId, String fieldId);
}
