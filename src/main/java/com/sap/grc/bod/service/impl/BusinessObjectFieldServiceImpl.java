package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.model.BusinessObjectFieldOptionText;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.service.BusinessObjectFieldService;
import com.sap.grc.bod.validator.BusinessObjectFieldValidator;

@Service
public class BusinessObjectFieldServiceImpl implements BusinessObjectFieldService 
{
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectFieldValidator bofValidator;
	
	@Override
	public BusinessObjectField createBusinessObjecFields(String boName, BusinessObjectFieldDTO bofDTO, UserBean user, String languageId){
		BusinessObject bo = bofValidator.validateBusinessObject(boName);
		bofValidator.validateBusinessObjectFieldDTO(bofDTO);
		bofValidator.validateBusinessObjectFieldCreate(boName, bofDTO.getId());
		BusinessObjectField bof = new BusinessObjectField();
		BeanUtils.copyProperties(bofDTO, bof);
		
		List<BusinessObjectFieldText> boftList = new ArrayList<>();
		
		BusinessObjectFieldTextDTO boftDTO = bofDTO.getBusinessObjectFieldText();
		BusinessObjectFieldText boft = new BusinessObjectFieldText();
		BeanUtils.copyProperties(boftDTO, boft);
		boft.setBusinessObjectField(bof);
		boft.setLanguageId(languageId);
		boftList.add(boft);
		
		//create date & create user
		
		bof.setBusinessObject(bo);
		bof.setBusinessObjectFieldTextList(boftList);
		return bofRepo.save(bof);
	}
	
	@Override
	public BusinessObjectField updateBusinessObjectField(String boName, String fieldId, BusinessObjectFieldDTO bofDTO, UserBean user, String languageId){
		bofValidator.validateBusinessObject(boName);
		bofValidator.validateBusinessObjectFieldDTO(bofDTO);
		BusinessObjectField bof = bofValidator.validateBusinessObjectField(boName, fieldId);
		bofValidator.validateBusinessObjectUpdate(fieldId, bofDTO);
		BeanUtils.copyProperties(bofDTO, bof);
		
		List<BusinessObjectFieldText> boftList = bof.getBusinessObjectFieldTextList();
		Optional<BusinessObjectFieldText> optionalBoftSpecific  = boftList.stream().filter(t->t.getLanguageId().equals(languageId)).findFirst();
		//if no specific language text, then create
		if(optionalBoftSpecific.isPresent()){
			BusinessObjectFieldText boft = optionalBoftSpecific.get();
			BeanUtils.copyProperties(bofDTO.getBusinessObjectFieldText(), boft);
		}else{
			BusinessObjectFieldText boft = new BusinessObjectFieldText();
			BeanUtils.copyProperties(bofDTO.getBusinessObjectFieldText(), boft);
			boft.setLanguageId(languageId);
			boft.setBusinessObjectField(bof);
			boftList.add(boft);
		}
		
		//update date & update user
		return bofRepo.save(bof);
	}

	@Override
	public BusinessObjectField findOneBusinessObjectField(String boName, String fieldId, String languageId){
		bofValidator.validateBusinessObject(boName);
		BusinessObjectField bof = bofValidator.validateBusinessObjectField(boName, fieldId);
		return this.getBusinessObjectFieldWithSpecificLanguage(bof, languageId);
	}
	
	@Override
	public List<BusinessObjectField> findAllBusinessObjectField(String boName, String languageId){
		bofValidator.validateBusinessObject(boName);
		List<BusinessObjectField> bofList = bofRepo.findByBusinessObject_Name(boName);
		for(BusinessObjectField bof: bofList){
			bof = this.getBusinessObjectFieldWithSpecificLanguage(bof, languageId);
		}
		return bofList;
	}
	
	@Override
	public List<BusinessObjectField> findAllBusinessObjectField(String boName){
		bofValidator.validateBusinessObject(boName);
		return bofRepo.findByBusinessObject_Name(boName);
	}
	
	@Override
	public void deleteOneBusinessObjectField(String boName, String fieldId){
		bofValidator.validateBusinessObject(boName);
		BusinessObjectField bof = bofValidator.validateBusinessObjectField(boName, fieldId);
		bofRepo.delete(bof);
	}
	
	private BusinessObjectField getBusinessObjectFieldWithSpecificLanguage(BusinessObjectField bof, String languageId){
		List<BusinessObjectFieldText> boftList = bof.getBusinessObjectFieldTextList();
		List<BusinessObjectFieldText> boftSpecificList =
			boftList.stream().filter(t->t.getLanguageId().equals(languageId)).collect(Collectors.toList());
		
		List<BusinessObjectFieldOption> bofoList = bof.getBusinessObjectFieldOptionList();
		for(BusinessObjectFieldOption bofo: bofoList){
			List<BusinessObjectFieldOptionText> bofotSpecificList =
				bofo.getBusinessObjectFieldOptionTextList().stream().filter(t->t.getLanguageId().equals(languageId)).collect(Collectors.toList());
			bofo.setBusinessObjectFieldOptionTextList(bofotSpecificList);
		}
		bof.setBusinessObjectFieldTextList(boftSpecificList);
		return bof;
	}
}
