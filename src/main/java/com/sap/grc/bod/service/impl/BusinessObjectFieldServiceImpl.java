package com.sap.grc.bod.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
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
	public BusinessObjectField createBusinessObjecField(String boName, BusinessObjectFieldDTO bofDTO, UserBean user, String languageId){
		BusinessObject bo = bofValidator.validateBusinessObject(boName);
		bofValidator.validateBusinessObjectFieldDTO(bofDTO);
		bofValidator.validateBusinessObjectFieldCreate(boName, bofDTO.getId());
		BusinessObjectField bof = new BusinessObjectField();
		BeanUtils.copyProperties(bofDTO, bof);
		
		Set<BusinessObjectFieldText> boftSet = new HashSet<>();
		
		BusinessObjectFieldTextDTO boftDTO = bofDTO.getBusinessObjectFieldText();
		BusinessObjectFieldText boft = new BusinessObjectFieldText();
		BeanUtils.copyProperties(boftDTO, boft);
		boft.setBusinessObjectField(bof);
		boft.setLanguageId(languageId);
		boftSet.add(boft);
		
		//create date & create user
		
		bof.setBusinessObject(bo);
		bof.setBusinessObjectFieldTextList(boftSet);
		return bofRepo.save(bof);
	}
	
	@Override
	public BusinessObjectField updateBusinessObjectField(String boName, String fieldId, BusinessObjectFieldDTO bofDTO, UserBean user, String languageId){
		bofValidator.validateBusinessObject(boName);
		bofValidator.validateBusinessObjectFieldDTO(bofDTO);
		BusinessObjectField bof = bofValidator.validateBusinessObjectField(boName, fieldId);
		bofValidator.validateBusinessObjectUpdate(fieldId, bofDTO);
		BeanUtils.copyProperties(bofDTO, bof);
		
		Set<BusinessObjectFieldText> boftSet = bof.getBusinessObjectFieldTextList();
		Optional<BusinessObjectFieldText> optionalBoftSpecific  = boftSet.stream().filter(t->t.getLanguageId().equals(languageId)).findFirst();
		//if no specific language text, then create
		if(optionalBoftSpecific.isPresent()){
			BusinessObjectFieldText boft = optionalBoftSpecific.get();
			BeanUtils.copyProperties(bofDTO.getBusinessObjectFieldText(), boft);
		}else{
			BusinessObjectFieldText boft = new BusinessObjectFieldText();
			BeanUtils.copyProperties(bofDTO.getBusinessObjectFieldText(), boft);
			boft.setLanguageId(languageId);
			boft.setBusinessObjectField(bof);
			boftSet.add(boft);
		}
		
		//update date & update user
		return bofRepo.save(bof);
	}

	@Override
	public BusinessObjectField findOneBusinessObjectField(String boName, String fieldId, String languageId){
		bofValidator.validateBusinessObject(boName);
		return bofRepo.findOneWithFetch(languageId, fieldId);
	}
	
	@Override
	public List<BusinessObjectField> findAllBusinessObjectField(String boName, String languageId){
		bofValidator.validateBusinessObject(boName);
		return bofRepo.findAllWithFetch(languageId);
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
	
//	private BusinessObjectField getBusinessObjectFieldWithSpecificLanguage(BusinessObjectField bof, String languageId){
//		Set<BusinessObjectFieldText> boftSet = bof.getBusinessObjectFieldTextList();
//		Set<BusinessObjectFieldText> boftSpecificSet =
//			boftSet.stream().filter(t->t.getLanguageId().equals(languageId)).collect(Collectors.toSet());
//		
//		Set<BusinessObjectFieldOption> bofoSet = bof.getBusinessObjectFieldOptionList();
//		for(BusinessObjectFieldOption bofo: bofoSet){
//			Set<BusinessObjectFieldOptionText> bofotSpecificSet =
//				bofo.getBusinessObjectFieldOptionTextList().stream().filter(t->t.getLanguageId().equals(languageId)).collect(Collectors.toSet());
//			bofo.setBusinessObjectFieldOptionTextList(bofotSpecificSet);
//		}
//		bof.setBusinessObjectFieldTextList(boftSpecificSet);
//		return bof;
//	}
}
