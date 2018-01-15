package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.assertj.core.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.service.BusinessObjectFieldOptionService;

@Service
public class BusinessObjectFieldOptionServiceImpl implements BusinessObjectFieldOptionService
{
	
	@Autowired
	private BusinessObjectFieldOptionRepository bofoRepo;
	
	@Autowired
	private BusinessObjectFieldRepository bofRepo;

	@Override
	public BusinessObjectFieldOption createBusinessObjectFieldOption(String fieldId, String languageId, BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO){
		this.createBusinessObjectServiceOptionValidation(businessObjectFieldOptionDTO);
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		BusinessObjectFieldOption businessObjectFieldOption = businessObjectFieldOptionDTO.converToModel();
		businessObjectFieldOption.setBussinessObjectField(businessObjectField);
		return bofoRepo.save(businessObjectFieldOption);
	}
	
	@Override
	public List<BusinessObjectFieldOption> createMultiBusinessObjectFieldOption(
			String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
			BusinessObjectFieldOption businessObjectFieldOption = businessObjectFieldOptionDTO.converToModel();
			businessObjectFieldOption.setBussinessObjectField(businessObjectField);
			businessObjectFieldOptionList.add(businessObjectFieldOption);
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(List<String> fieldOptionIdList, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoRepo.findByUuidIn(fieldOptionIdList);
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
		return bofoRepo.findByUuidAndLanguageId(fieldId, languageId);
	}
	
	private void createBusinessObjectServiceOptionValidation(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO){
		String fieldId = businessObjectFieldOptionDTO.getFieldId();
		String languageId = businessObjectFieldOptionDTO.getLanguageId();
		String value = businessObjectFieldOptionDTO.getValue();
		if(Objects.isNull(fieldId)||Strings.isNullOrEmpty(languageId)||Strings.isNullOrEmpty(value)){
			return;
		}
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			return;
		}
	}
	
	private void createBusinessObjectServiceOptionListValidation(List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
//		for(BusinessObjectFieldOption option)
//		String businessObjectId = businessObjectFieldOptionDTO.getBusinessObjectId();
//		String fieldId = businessObjectFieldOptionDTO.getFieldId();
//		String languageId = businessObjectFieldOptionDTO.getLanguageId();
//		String value = businessObjectFieldOptionDTO.getValue();
//		if(Strings.isNullOrEmpty(businessObjectId)||Strings.isNullOrEmpty(fieldId)||Strings.isNullOrEmpty(languageId)||Strings.isNullOrEmpty(value)){
//			return;
//		}
	}
}
