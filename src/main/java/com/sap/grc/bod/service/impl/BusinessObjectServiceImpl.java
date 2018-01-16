package com.sap.grc.bod.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.repository.BusinessObjectRepository;
import com.sap.grc.bod.service.BusinessObjectService;


@Service
public class BusinessObjectServiceImpl implements BusinessObjectService
{
	@Autowired
	private BusinessObjectRepository boRepo;
	
	public BusinessObject createBusinessObject(BusinessObjectDTO businessObjectDTO, UserBean user){
		
		//Definition
		BusinessObject businessObject = new BusinessObject();
		
		//Validation
		this.createBusinessObjectValidation(businessObjectDTO);
		
		//Prepare Data
		BeanUtils.copyProperties(businessObjectDTO, businessObject);
		businessObject.setCreatorBy(user.getUid());
		
		//Save Transaction
	   	try {
		  businessObject = boRepo.save(businessObject);
	    } catch (RuntimeException ex) {

         //TODO Logger, Message Translation
         //TODO logger.warn("request failed", notFoundException);
	    	throw ex;
	     } 
   	 
		return businessObject;
		
	}
	
	public BusinessObject updateBusinessObject(String businessObjectId, BusinessObjectDTO businessObjectDTO){

		BusinessObject businessObject = new BusinessObject();
		
		this.udpateBusinessObjectValidation(businessObjectId, businessObjectDTO);
		
		businessObject = boRepo.findByUuid(businessObjectId);
		BeanUtils.copyProperties(businessObjectDTO, businessObject);
		
	   	try {
		  businessObject = boRepo.save(businessObject);
	    } catch (RuntimeException ex) {
         //TODO Logger, Message Translation
         //TODO logger.warn("request failed", notFoundException);
	    	throw ex;
	     } 
   	 
		return businessObject;
	}
	
	public List<BusinessObject> getAllBusinessObject(){
		return boRepo.findAll();
	}
	
	public BusinessObject findBybusinessObjectId(String businessObjectId) {
		return boRepo.findByUuid(businessObjectId);
	};

	public BusinessObject findBybusinessObjectName(String businessObjectName) {
		return boRepo.findByName(businessObjectName);
	}
	
	public void deleteBybusinessObjectId(String businessObjectId) {
	   	try {
		  boRepo.delete(businessObjectId); 
	   	} catch (RuntimeException ex){
	    	throw ex;	   		
	   	}
	}
	
	private void createBusinessObjectValidation(BusinessObjectDTO businessObjectDTO){
		
		BusinessObject businessObject = new BusinessObject();
		
		if(businessObjectDTO.getName().isEmpty()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNull);
		}
		
        businessObject = this.findBybusinessObjectName(businessObjectDTO.getName());
		if(Objects.nonNull(businessObject)) {
		    throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isExisted);
		}		

	}
	
	private void udpateBusinessObjectValidation(String businessObjectId, BusinessObjectDTO businessObjectDTO){

		BusinessObject businessObject = new BusinessObject();
		
		if(businessObjectDTO.getName().isEmpty()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNull);
		}
		
		if(!boRepo.exists(businessObjectId)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted); 
		}
		
        businessObject = this.findBybusinessObjectName(businessObjectDTO.getName());
		if(Objects.isNull(businessObject)) {
		    throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);
		}		
	}


}
