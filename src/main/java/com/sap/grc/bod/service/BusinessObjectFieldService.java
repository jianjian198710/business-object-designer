package com.sap.grc.bod.service;

import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.UserBean;

@Service
public interface BusinessObjectFieldService
{
	public BusinessObjectField createBusinessObjecField(BusinessObjectFieldDTO businessObjectFieldDTO, UserBean user);
}
