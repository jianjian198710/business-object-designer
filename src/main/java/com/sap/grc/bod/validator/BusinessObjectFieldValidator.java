package com.sap.grc.bod.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldTextRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;

@Component
public class BusinessObjectFieldValidator
{   
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectRepository boRepo;
	
	public void createBusinessObjectFieldValidation(String businessObjectId, BusinessObjectFieldDTO businessObjectFieldDTO){
		
		//Input Check - Business Object
		this.validateBusinessObjectConsistent(businessObjectId, businessObjectFieldDTO);
		
		//Input Check - Field related validation
        this.inputCheckBusinessObjectField(businessObjectFieldDTO);
		
	}
	
	public void updateBusinessObjectFieldValidation(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO){
		
		//Input Check - Business Object
		this.validateBusinessObjectConsistent(businessObjectId, businessObjectFieldDTO);
		
		//Input Check - Field related validation
        this.inputCheckBusinessObjectField(businessObjectFieldDTO);	
        
        //Business Field consistent check
        BusinessObjectField boField = bofRepo.findOne(fieldId);
        if(Objects.isNull(boField)) {
        	throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
        }

	}
	
	private void validateBusinessObjectConsistent(String businessObjectId, BusinessObjectFieldDTO businessObjectFieldDTO) {
				
		if(Objects.isNull(businessObjectId)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNull);
		}
		
		BusinessObject businessObject = boRepo.findByUuid(businessObjectId);
		if(Objects.isNull(businessObject)) {
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);			
		}
		
	}
	
	private void inputCheckBusinessObjectField(BusinessObjectFieldDTO businessObjectFieldDTO) {
		
		if(businessObjectFieldDTO.getName().trim().length()==0){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_Name_isEmpty);
		}
				
	}
	
}
