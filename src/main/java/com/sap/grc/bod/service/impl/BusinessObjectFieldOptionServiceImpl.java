package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.service.BusinessObjectFieldOptionService;
import com.sap.grc.bod.validator.BusinessObjectFieldOptionValidator;

@Service
public class BusinessObjectFieldOptionServiceImpl implements BusinessObjectFieldOptionService
{
	
	@Autowired
	private BusinessObjectFieldOptionRepository bofoRepo;
	
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectFieldOptionValidator bofoValidator;

//	@Override
//	public BusinessObjectFieldOption createBusinessObjectFieldOption(String fieldId, String languageId, BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO){
//		this.createBusinessObjectServiceOptionValidation(businessObjectFieldOptionDTO);
//		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
//		if(Objects.isNull(businessObjectField)){
//			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
//		}
//		BusinessObjectFieldOption businessObjectFieldOption = businessObjectFieldOptionDTO.converToModel();
//		businessObjectFieldOption.setBussinessObjectField(businessObjectField);
//		return bofoRepo.save(businessObjectFieldOption);
//	}
	
	@Override
	@Transactional
	public List<BusinessObjectFieldOption> createMultiBusinessObjectFieldOption(
			String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		bofoValidator.createMultiBusinessObjectFieldOptionValidation(fieldId, businessObjectFieldOptionDTOList);
		for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
			BusinessObjectFieldOption businessObjectFieldOption = businessObjectFieldOptionDTO.converToModel();
			businessObjectFieldOption.setLanguageId(languageId);
			businessObjectFieldOption.setBussinessObjectField(businessObjectField);
			businessObjectFieldOptionList.add(businessObjectFieldOption);
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	@Transactional
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(List<String> fieldOptionIdList, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoValidator.updateMultiBusinessObjectFieldOptionValidation(fieldOptionIdList, businessObjectFieldOptionDTOList);
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
	public List<BusinessObjectFieldOption> findAllBusinessObjectFieldOption(String fieldId, String languageId){
		return bofoRepo.findByFieldIdAndLanguageId(fieldId, languageId);
	}
	
	@Override
	@Transactional
	public void deleteBusinessObjectFieldOption(String fieldOptionId){
		bofoValidator.deleteBusinessObjectFieldOptionValidation(fieldOptionId);
		bofoRepo.delete(fieldOptionId);
	}
	
	@Override
	@Transactional
	public void deleteAllBusinessObjectFieldOption(String fieldId, String languageId){
		bofoRepo.deleteByFieldIdAndLanguageId(fieldId, languageId);
	}
	
//	private void createBusinessObjectServiceOptionValidation(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO){
//		String fieldId = businessObjectFieldOptionDTO.getFieldId();
//		String value = businessObjectFieldOptionDTO.getValue();
//		if(Objects.isNull(fieldId)||Strings.isNullOrEmpty(languageId)||Strings.isNullOrEmpty(value)){
//			return;
//		}
//		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
//		if(Objects.isNull(businessObjectField)){
//			return;
//		}
//	}
}
