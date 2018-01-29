package com.sap.grc.bod.service;

import java.util.List;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.UserBean;

public interface BusinessObjectFieldService
{
	public List<BusinessObjectField> createBusinessObjecFields(String businessObjectName, List<BusinessObjectFieldDTO> businessObjectFieldDTOList, UserBean user);
	
	public BusinessObjectField updateOneBusinessObjectField(String businessObjectName, String fieldName, BusinessObjectFieldDTO businessObjectFieldDTO);
	public List<BusinessObjectField> updateMultiBusinessObjectField(String businessObjectName,List<BusinessObjectFieldDTO> businessObjectFieldDTOList);
	
	public BusinessObjectField findOneBusinessObjectField(String businessObjectName, String fieldName);
	public List<BusinessObjectField> findAllBusinessObjectField(String businessObjectName);
    
	public void deleteOneBusinessObjectField(String businessObjectName, String fieldName);
}
