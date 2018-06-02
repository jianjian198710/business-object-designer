package com.sap.grc.bod.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
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
	
	public BusinessObject validateBusinessObject(String businessObjectName){
		BusinessObject businessObject = boRepo.findByName(businessObjectName);
		if(Objects.isNull(businessObject)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);
		}
		return businessObject;
	}
	
	/*
	 * check BusinessObjectFieldDTO
	 */
	public void validateBusinessObjectFieldDTO(BusinessObjectFieldDTO bofDTO){
		if(this.isNullOrEmpty(bofDTO.getId())||this.isContainEmpty(bofDTO.getId())){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_ID_ErrorInput);
		}
		BusinessObjectFieldTextDTO boftDTO = bofDTO.getBusinessObjectFieldText();
		if(Objects.isNull(boftDTO)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldText_isNull);
		}
		if(this.isNullOrEmpty(boftDTO.getName())){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldText_Name_ErrorInput);
		}
	}
	
	/*
	 * check whether fieldId is existed when create
	 */
	public void validateBusinessObjectFieldCreate(String boName, String fieldId){
		BusinessObjectField bof = bofRepo.findByBusinessObject_NameAndId(boName, fieldId);
		if(Objects.nonNull(bof)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isExisted);
		}
	}
	
	public BusinessObjectField validateBusinessObjectField(String boName, String fieldId){
		BusinessObjectField bof = bofRepo.findByBusinessObject_NameAndId(boName, fieldId);
		if(Objects.isNull(bof)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		return bof;
	}
	
	public void validateBusinessObjectUpdate(String fieldId, BusinessObjectFieldDTO bofDTO){
		if(!fieldId.equals(bofDTO.getId())){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_ID_isNotSame);
		}
	}
	
	private boolean isNullOrEmpty(String str){
		if(Objects.isNull(str) || str.trim().length()==0 ){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isContainEmpty(String str){
		if(str.replaceAll("\\s+","").length()!=str.length()){
			return true;
		}else{
			return false;
		}
	}
}
