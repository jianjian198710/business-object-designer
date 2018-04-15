package com.sap.grc.bod.service;

import java.util.List;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.UserBean;

public interface BusinessObjectFieldService
{
	public BusinessObjectField createBusinessObjecField(String boName, BusinessObjectFieldDTO bofDTO, UserBean user, String languageId);
	public BusinessObjectField updateBusinessObjectField(String boName, String fieldId, BusinessObjectFieldDTO bofDTO, UserBean user, String languageId);
	public BusinessObjectField findOneBusinessObjectField(String boName, String fieldId, String languageId);
	public List<BusinessObjectField> findAllBusinessObjectField(String boName, String languageId);
	public List<BusinessObjectField> findAllBusinessObjectField(String boName);
	public void deleteOneBusinessObjectField(String boName, String fieldId);
}
