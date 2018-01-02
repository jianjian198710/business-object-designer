package com.sap.grc.bod.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;

public class BusinessObjectFieldServiceImpl
{
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	public BusinessObjectField createBusinessObjecField(BusinessObjectFieldDTO businessObjectFieldDTO, UserBean user){
		BusinessObjectField businessObjectField = new BusinessObjectField();
		BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
		businessObjectField.setCreatorName(user.getName());
		businessObjectField.setCreatorMail(user.getMail());
		return bofRepo.save(businessObjectField);
	}
	
	
}
