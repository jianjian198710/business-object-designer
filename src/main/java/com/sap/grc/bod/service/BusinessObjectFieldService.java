package com.sap.grc.bod.service;

import java.util.List;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.UserBean;

public interface BusinessObjectFieldService
{
	public List<BusinessObjectField> createBusinessObjecFields(String businessObjectId, List<BusinessObjectFieldDTO> businessObjectFieldDTOList, UserBean user);
	
	public BusinessObjectField updateOneBusinessObjectField(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO);
	public List<BusinessObjectField> updateMultiBusinessObjectField(String businessObjectId,List<BusinessObjectFieldDTO> businessObjectFieldDTOList);
	
	public BusinessObjectField findOneBusinessObjectField(String businessObjectId, String fieldId);
	public List<BusinessObjectField> findAllBusinessObjectField(String businessObjectId);
}
