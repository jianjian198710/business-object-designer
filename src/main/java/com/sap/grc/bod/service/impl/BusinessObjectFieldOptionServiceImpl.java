package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.service.BusinessObjectFieldOptionService;
import com.sap.grc.bod.validator.BusinessObjectFieldOptionValidator;
import com.sap.grc.bod.validator.BusinessObjectFieldValidator;

@Service
public class BusinessObjectFieldOptionServiceImpl implements BusinessObjectFieldOptionService
{
	
	@Autowired
	private BusinessObjectFieldOptionRepository bofoRepo;
	
	@Autowired
	private BusinessObjectFieldOptionValidator bofoValidator;
	
	@Autowired
	private BusinessObjectFieldValidator bofValidator;

	@Override
	@Transactional
	public List<BusinessObjectFieldOption> createMultiBusinessObjectFieldOption(
			String boName, String fieldName, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		bofoValidator.createMultiBusinessObjectFieldOptionValidation(businessObjectField.getUuid(), businessObjectFieldOptionDTOList);
		for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
			BusinessObjectFieldOption businessObjectFieldOption = businessObjectFieldOptionDTO.converToModel();
			businessObjectFieldOption.setLanguageId(languageId);
			businessObjectFieldOption.setFieldId(businessObjectField.getUuid());
			businessObjectFieldOption.setBussinessObjectField(businessObjectField);
			businessObjectFieldOptionList.add(businessObjectFieldOption);
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	@Transactional
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(String boName, String fieldName, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectField, businessObjectFieldOptionDTOList);
		for(BusinessObjectFieldOption businessObjectFieldOption: businessObjectFieldOptionList){
			for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
				if(businessObjectFieldOptionDTO.getFieldOpitonId().equals(businessObjectFieldOption.getUuid())){
					BeanUtils.copyProperties(businessObjectFieldOptionDTO, businessObjectFieldOption);
				}
			}
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	public List<BusinessObjectFieldOption> findAllBusinessObjectFieldOption(String boName, String fieldName, String languageId){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		return bofoRepo.findByFieldIdAndLanguageId(businessObjectField.getUuid(), languageId);
	}
	
	@Override
	@Transactional
	public void deleteBusinessObjectFieldOption(String boName, String fieldName, String fieldOptionValue, String languageId){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = businessObjectField.getBusinessObjectFieldOptionList().stream().filter(t->t.getValue().equals(fieldOptionValue)&&t.getLanguageId().equals(languageId)).collect(Collectors.toList());
		//TODO validate businessObjectFieldOptionList
		if(businessObjectFieldOptionList.size()==1){
			bofoRepo.delete(businessObjectFieldOptionList.get(0).getUuid());
		}
	}
	
	@Override
	@Transactional
	public void deleteAllBusinessObjectFieldOption(String boName, String fieldName, String languageId){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName,fieldName);
		bofoRepo.deleteByFieldIdAndLanguageId(businessObjectField.getUuid(), languageId);
	}
}
