package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionTextDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.model.BusinessObjectFieldOptionText;
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
//			businessObjectFieldOption.setLanguageId(languageId);
			List<BusinessObjectFieldOptionText> businessObjectFieldOptionTextList = new ArrayList<>();
			BusinessObjectFieldOptionTextDTO businessObjectFieldOptionTextDTO = businessObjectFieldOptionDTO.getBusinessObjectFieldOptionTextDTO();
			if(Objects.nonNull(businessObjectFieldOptionTextDTO)){
				BusinessObjectFieldOptionText businessObjectFieldOptionText = new BusinessObjectFieldOptionText();
				businessObjectFieldOptionText.setLanguageId(languageId);
				businessObjectFieldOptionText.setDescription(businessObjectFieldOptionTextDTO.getDescription());
				businessObjectFieldOptionText.setBusinessObjectFieldOption(businessObjectFieldOption);
				businessObjectFieldOptionTextList.add(businessObjectFieldOptionText);
			}
//			businessObjectFieldOption.setFieldId(businessObjectField.getUuid());
			businessObjectFieldOption.setBussinessObjectField(businessObjectField);
			businessObjectFieldOptionList.add(businessObjectFieldOption);
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	@Transactional
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(String boName, String fieldName, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectField, businessObjectFieldOptionDTOList);
		for(BusinessObjectFieldOption businessObjectFieldOption: businessObjectFieldOptionList){
			for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
				if(businessObjectFieldOptionDTO.getFieldOpitonId().equals(businessObjectFieldOption.getUuid())){
//					BeanUtils.copyProperties(businessObjectFieldOptionDTO, businessObjectFieldOption);
					List<BusinessObjectFieldOptionText> bofotList = businessObjectFieldOption.getBusinessObjectFieldOptionTextList();
					List<BusinessObjectFieldOptionText> bofotSpecList = bofotList.stream().filter(t->t.getLanguageId().equals(languageId)).collect(Collectors.toList());
					//no specific language, then create
					if(Objects.isNull(bofotSpecList) || bofotSpecList.isEmpty()){
						BusinessObjectFieldOptionText bofot = new BusinessObjectFieldOptionText();
						bofot.setLanguageId(languageId);
						bofot.setDescription(businessObjectFieldOptionDTO.getBusinessObjectFieldOptionTextDTO().getDescription());
						bofot.setBusinessObjectFieldOption(businessObjectFieldOption);
						bofotList.add(bofot);
					}else{
						bofotSpecList.get(0).setDescription(businessObjectFieldOptionDTO.getBusinessObjectFieldOptionTextDTO().getDescription());
					}
				}
			}
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	public List<BusinessObjectFieldOption> findAllBusinessObjectFieldOption(String boName, String fieldName, String languageId){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		return bofoRepo.findByFieldId(businessObjectField.getUuid());
	}
	
	@Override
	@Transactional
	public void deleteBusinessObjectFieldOption(String boName, String fieldName, String fieldOptionValue){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldName);
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = businessObjectField.getBusinessObjectFieldOptionList().stream().filter(t->t.getValue().equals(fieldOptionValue)).collect(Collectors.toList());
		//TODO validate businessObjectFieldOptionList
		if(businessObjectFieldOptionList.size()==1){
			bofoRepo.delete(businessObjectFieldOptionList.get(0).getUuid());
		}
	}
	
	@Override
	@Transactional
	public void deleteAllBusinessObjectFieldOption(String boName, String fieldName){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName,fieldName);
		bofoRepo.deleteByFieldId(businessObjectField.getUuid());
	}
}
