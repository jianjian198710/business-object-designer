package com.sap.grc.bod.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;
import com.sap.grc.bod.service.BusinessObjectFieldService;

@Service
public class BusinessObjectFieldServiceImpl implements BusinessObjectFieldService 
{
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectRepository boRepo;
	
	@Override
	public BusinessObjectField createBusinessObjecField(String businessObjectId, BusinessObjectFieldDTO businessObjectFieldDTO, UserBean user){
		BusinessObject businessObject = boRepo.findOne(businessObjectId);
		if(Objects.isNull(businessObject)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);
		}
		this.createBusinessObjectFieldValidation(businessObjectFieldDTO);
		BusinessObjectField businessObjectField = businessObjectFieldDTO.convertToModel();
		businessObjectField.setCreatorName(user.getName());
		businessObjectField.setCreatorMail(user.getMail());
		businessObjectField.setBusinessObject(businessObject);
		return bofRepo.save(businessObjectField);
	}
	
	@Override
	public BusinessObjectField updateOneBusinessObjectField(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO){
		BusinessObjectField businessObjectField = this.updateBusinessObjectFieldValidation(businessObjectId, fieldId, businessObjectFieldDTO);
		BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
		return bofRepo.save(businessObjectField);
	}
	
	@Override
	public List<BusinessObjectField> updateMultiBusinessObjectField(String businessObjectId, List<String> fieldIdList, List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
		List<BusinessObjectField> businessObjectFieldList = bofRepo.findAll(fieldIdList);
		for(BusinessObjectField businessObjectField: businessObjectFieldList){
			for(BusinessObjectFieldDTO businessObjectFieldDTO: businessObjectFieldDTOList){
				if(businessObjectFieldDTO.getFieldId().equals(businessObjectField.getFieldId())){
					BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
				}
			}
		}
		return bofRepo.save(businessObjectFieldList);
	}
	
	@Override
	public BusinessObjectField findOneBusinessObjectField(String businessObjectId, String fieldId){
		return bofRepo.findOne(fieldId);
	}
	
	@Override
	public List<BusinessObjectField> findAllBusinessObjectField(String businessObjectId){
		return bofRepo.findByBusinessObjectId(businessObjectId);
	}
	
	
	//TODO enhancement
	private void createBusinessObjectFieldValidation(BusinessObjectFieldDTO businessObjectFieldDTO){
		if(Objects.isNull(businessObjectFieldDTO.getBusinessObjectId())){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_errInput);
		}
	}
	
	//TODO enhancement
	private BusinessObjectField updateBusinessObjectFieldValidation(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO){
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		return businessObjectField;
	}
}
