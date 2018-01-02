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
		this.createBusinessObjectValidation(businessObjectDTO);
		BusinessObject businessObject = new BusinessObject();
		BeanUtils.copyProperties(businessObjectDTO, businessObject);
		businessObject.setCreatorName(user.getName());
		businessObject.setCreatorMail(user.getMail());
		return boRepo.save(businessObject);
	}
	
	public BusinessObject updateBusinessObject(BusinessObjectDTO businessObjectDTO){
		BusinessObject businessObject = this.udpateBusinessObjectValidation(businessObjectDTO);
		BeanUtils.copyProperties(businessObjectDTO, businessObject);
		return boRepo.save(businessObject);
	}
	
	public List<BusinessObject> getAllBusinessObject(){
		return boRepo.findAll();
	}
	
	private void createBusinessObjectValidation(BusinessObjectDTO businessObjectDTO){
		if(Objects.nonNull(businessObjectDTO.getBusinessObjectId())){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isExisted);
		}
	}
	
	private BusinessObject udpateBusinessObjectValidation(BusinessObjectDTO businessObjectDTO){
		BusinessObject businessObject = boRepo.getOne(businessObjectDTO.getBusinessObjectId());
		if(Objects.isNull(businessObject)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted); 
		}
		return businessObject;
	}
}
