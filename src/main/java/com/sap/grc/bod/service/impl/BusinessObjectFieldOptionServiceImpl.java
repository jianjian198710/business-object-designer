package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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
			String boName, String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> bofoDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldId);
		bofoValidator.createMultiBusinessObjectFieldOptionValidation(businessObjectField.getUuid(), bofoDTOList);
		for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: bofoDTOList){
			BusinessObjectFieldOption businessObjectFieldOption = businessObjectFieldOptionDTO.converToModel();
			Set<BusinessObjectFieldOptionText> businessObjectFieldOptionTextList = new HashSet<>();
			BusinessObjectFieldOptionTextDTO businessObjectFieldOptionTextDTO = businessObjectFieldOptionDTO.getBusinessObjectFieldOptionTextDTO();
			if(Objects.nonNull(businessObjectFieldOptionTextDTO)){
				BusinessObjectFieldOptionText businessObjectFieldOptionText = new BusinessObjectFieldOptionText();
				businessObjectFieldOptionText.setLanguageId(languageId);
				businessObjectFieldOptionText.setDescription(businessObjectFieldOptionTextDTO.getDescription());
				businessObjectFieldOptionText.setBusinessObjectFieldOption(businessObjectFieldOption);
				businessObjectFieldOptionTextList.add(businessObjectFieldOptionText);
			}
			businessObjectFieldOption.setBusinessObjectField(businessObjectField);
			businessObjectFieldOption.setBusinessObjectFieldOptionTextList(businessObjectFieldOptionTextList);
			businessObjectFieldOptionList.add(businessObjectFieldOption);
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	@Transactional
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(String boName, String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> bofoDTOList){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldId);
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectField, bofoDTOList);
		for(BusinessObjectFieldOption businessObjectFieldOption: businessObjectFieldOptionList){
			for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: bofoDTOList){
				if(businessObjectFieldOptionDTO.getFieldOpitonId().equals(businessObjectFieldOption.getUuid())){
					BeanUtils.copyProperties(businessObjectFieldOptionDTO, businessObjectFieldOption);
					Set<BusinessObjectFieldOptionText> bofotSet = businessObjectFieldOption.getBusinessObjectFieldOptionTextList();
					Set<BusinessObjectFieldOptionText> bofotSpecSet = bofotSet.stream().filter(t->t.getLanguageId().equals(languageId)).collect(Collectors.toSet());
					//no specific language, then create
					if(Objects.isNull(bofotSpecSet) || bofotSpecSet.isEmpty()){
						BusinessObjectFieldOptionText bofot = new BusinessObjectFieldOptionText();
						bofot.setLanguageId(languageId);
						bofot.setDescription(businessObjectFieldOptionDTO.getBusinessObjectFieldOptionTextDTO().getDescription());
						bofot.setBusinessObjectFieldOption(businessObjectFieldOption);
						bofotSpecSet.add(bofot);
					}else{
						bofotSpecSet.stream().forEach(t->t.setDescription(businessObjectFieldOptionDTO.getBusinessObjectFieldOptionTextDTO().getDescription()));
					}
				}
			}
		}
		return bofoRepo.save(businessObjectFieldOptionList);
	}
	
	@Override
	public List<BusinessObjectFieldOption> findAllBusinessObjectFieldOption(String boName, String fieldId, String languageId){
		BusinessObjectField bof = bofValidator.validateBusinessObjectField(boName, fieldId);
		return bofoRepo.findAllWithFetch(bof, languageId);
	}
	
	@Override
	public void deleteBusinessObjectFieldOption(String boName, String fieldId, String fieldOptionValue){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName, fieldId);
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = businessObjectField.getBusinessObjectFieldOptionList().stream().filter(t->t.getValue().equals(fieldOptionValue)).collect(Collectors.toList());
		//TODO validate businessObjectFieldOptionList
		if(businessObjectFieldOptionList.size()==1){
			bofoRepo.delete(businessObjectFieldOptionList.get(0).getUuid());
		}
	}
	
	@Transactional
	@Override
	public void deleteAllBusinessObjectFieldOption(String boName, String fieldId){
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(boName,fieldId);
		bofoRepo.deleteByBusinessObjectField(businessObjectField);
	}
}
