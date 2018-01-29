package com.sap.grc.bod.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;

@Component
public class BusinessObjectFieldValidator
{   
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectRepository boRepo;

	public void validateBusinessObjectConsistent(String businessObjectName) {
		
		if(Objects.isNull(businessObjectName)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNull);
		}
		
		BusinessObject businessObject = boRepo.findByName(businessObjectName);
		if(Objects.isNull(businessObject)) {
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);			
		}
		
	}
	
	public void createBusinessObjectFieldValidation(BusinessObjectFieldDTO businessObjectFieldDTO){
		
		//Input Check - Field related validation
        this.inputCheckBusinessObjectField(businessObjectFieldDTO);	
        
        BusinessObjectField boField = bofRepo.findByName(businessObjectFieldDTO.getName());
        if(Objects.nonNull(boField)) {
        	throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isExisted);
        }        
        
	}
	
	public void updateBusinessObjectFieldValidation(String fieldName, BusinessObjectFieldDTO businessObjectFieldDTO){
				
		//Input Check - Field related validation
        this.inputCheckBusinessObjectField(businessObjectFieldDTO);	
        
        //Business Field consistent check
        if(!businessObjectFieldDTO.getName().equals(fieldName)) {
        	throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_FieldName_isNotSame);        	
        }
        
        BusinessObjectField boField = bofRepo.findByName(fieldName);
        if(Objects.isNull(boField)) {
        	throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
        }

	}
	
	public BusinessObjectField validateBusinessObjectField(String boName, String fieldName){
		BusinessObjectField businessObjectField = bofRepo.findByBusinessObject_NameAndName(boName, fieldName);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		return businessObjectField;
	}
	
	private void inputCheckBusinessObjectField(BusinessObjectFieldDTO businessObjectFieldDTO) {
		
		String fieldName = businessObjectFieldDTO.getName();
		
		if(Objects.isNull(fieldName)) {
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_Name_isEmpty);
		}
			
		
		if(fieldName.trim().length()==0){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_Name_isEmpty);
		}
		
		if(this.isContainEmpty(fieldName)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_errInput);
		}
				
	}
	
	private boolean isContainEmpty(String str){
		if(str.indexOf(" ") == -1){
			return false;
		}else{
			return true;
		}
	}
	
}
