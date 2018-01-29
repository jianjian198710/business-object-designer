package com.sap.grc.bod.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldTextRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;
import com.sap.grc.bod.service.BusinessObjectFieldService;
import com.sap.grc.bod.validator.BusinessObjectFieldValidator;

@Service
public class BusinessObjectFieldServiceImpl implements BusinessObjectFieldService 
{
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectRepository boRepo;
	
	@Autowired
	private BusinessObjectFieldTextRepository boftextRepo;
	
	@Autowired
	private BusinessObjectFieldOptionRepository bofopRepo;
	
	@Autowired
	private BusinessObjectFieldValidator bofValidator;
	
	@Override
	public List<BusinessObjectField> createBusinessObjecFields(String businessObjectName, 
           List<BusinessObjectFieldDTO> businessObjectFieldDTOList, UserBean user){
		
		//Validation
        bofValidator.validateBusinessObjectConsistent(businessObjectName);       
		BusinessObject businessObject = boRepo.findByName(businessObjectName);
		
		BusinessObjectFieldDTO businessObjectFieldDTO;
		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
		
		Iterator<BusinessObjectFieldDTO> bofDTOit = businessObjectFieldDTOList.iterator();
		while(bofDTOit.hasNext()) {
			
			BusinessObjectField businessObjectField = new BusinessObjectField();
			List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
			BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
			
			businessObjectFieldDTO = bofDTOit.next();
			
			//Validation
			bofValidator.createBusinessObjectFieldValidation(businessObjectFieldDTO);	
			
			//Copy to business object field model
			BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
			businessObjectField.setCreatorBy(user.getUid());
			businessObjectField.setBusinessObject(businessObject);

			//Copy to business object field text model
			String languageId = LocaleContextHolder.getLocale().getLanguage();
			businessObjectFieldText.setLanguageId(languageId);	
			if(Objects.nonNull(businessObjectFieldDTO.getBusinessObjectFieldText())){
				BeanUtils.copyProperties(businessObjectFieldDTO.getBusinessObjectFieldText(), businessObjectFieldText);		  
			}
			
			//Association:set field to text
			businessObjectFieldText.setBusinessObjectField(businessObjectField);
			businessObjectFieldTextList.add(businessObjectFieldText);
			
			//Association:set text list to field
			businessObjectField.setBusinessObjectFieldTextList(businessObjectFieldTextList);
			
			businessObjectFieldList.add(businessObjectField);

		}
				
		return bofRepo.save(businessObjectFieldList);
	}
	
	@Override
	public BusinessObjectField updateOneBusinessObjectField(String businessObjectName, String fieldName, BusinessObjectFieldDTO businessObjectFieldDTO){
		
		bofValidator.validateBusinessObjectConsistent(businessObjectName);
		
		BusinessObjectField businessObjectField = 
				this.preBusinessObjectField(fieldName, businessObjectFieldDTO);
		
		return bofRepo.save(businessObjectField);
		
	}
	
	@Override
	public List<BusinessObjectField> updateMultiBusinessObjectField(String businessObjectName, 
		   List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
        
		bofValidator.validateBusinessObjectConsistent(businessObjectName);
		
		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
		
		Iterator<BusinessObjectFieldDTO> bofDTOit = businessObjectFieldDTOList.iterator();
		while(bofDTOit.hasNext()) {

			BusinessObjectFieldDTO businessObjectFieldDTO;
	
			businessObjectFieldDTO = bofDTOit.next();	
			String fieldName = businessObjectFieldDTO.getName();
	        
			BusinessObjectField businessObjectField = 
					this.preBusinessObjectField(fieldName, businessObjectFieldDTO);
			
			businessObjectFieldList.add(businessObjectField);
			
		}
		
		return bofRepo.save(businessObjectFieldList);
	}

	@Override
	public BusinessObjectField findOneBusinessObjectField(String businessObjectName, String fieldName){
		
		BusinessObjectField businessObjectField = new BusinessObjectField();
		List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
		BusinessObjectFieldText preBusinessObjectFieldText = new BusinessObjectFieldText();
		List<BusinessObjectFieldOption> prebofOptionList = new ArrayList<>();
		
		BusinessObjectField preBusinessObjectField = bofRepo.findByBusinessObject_NameAndName(businessObjectName,fieldName);
		if(Objects.isNull(preBusinessObjectField)) {
			return businessObjectField;
		}
		
		BeanUtils.copyProperties(preBusinessObjectField, businessObjectField);
		businessObjectField.getBusinessObjectFieldOptionList().clear();
		businessObjectField.getBusinessObjectFieldTextList().clear();
		
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		preBusinessObjectFieldText = 
				   boftextRepo.findByBusinessObjectField_NameAndLanguageId(fieldName, languageId);
		if(!Objects.isNull(preBusinessObjectFieldText)) {
          businessObjectFieldTextList.add(preBusinessObjectFieldText);
          businessObjectField.setBusinessObjectFieldTextList(businessObjectFieldTextList);  
		}
        
		prebofOptionList = bofopRepo.findByBussinessObjectField_NameAndLanguageId(fieldName, languageId);
		if(!prebofOptionList.isEmpty()) {
			businessObjectField.setBusinessObjectFieldOptionList(prebofOptionList);
		}
		return businessObjectField;
	}

	public List<BusinessObjectField> findAllBusinessObjectField(String businessObjectName){
		
		List<BusinessObjectField> prebusinessObjectFieldList = bofRepo.findByBusinessObject_Name(businessObjectName);
		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
		
		Iterator<BusinessObjectField> bofIt = prebusinessObjectFieldList.iterator();
		while(bofIt.hasNext()) {
			BusinessObjectField prebusinessObjectField;
	        prebusinessObjectField = bofIt.next();	
			String fieldName = prebusinessObjectField.getName();
			BusinessObjectField businessObjectField = this.findOneBusinessObjectField(businessObjectName, fieldName);
			if(!Objects.isNull(businessObjectField)) {
				businessObjectFieldList.add(businessObjectField);			
			}			
		}	
		
		return businessObjectFieldList;
	}

	private BusinessObjectField preBusinessObjectField(String fieldName, BusinessObjectFieldDTO businessObjectFieldDTO) {

		List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
		BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
		
		//Validation
		bofValidator.updateBusinessObjectFieldValidation(fieldName, businessObjectFieldDTO);
		
		//Fetch field by field name
		BusinessObjectField businessObjectField = bofRepo.findByName(fieldName);
		BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
		
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		BusinessObjectFieldText existedBusinessObjectFieldText = 
				   boftextRepo.findByBusinessObjectField_NameAndLanguageId(fieldName, languageId);
        if(Objects.isNull(existedBusinessObjectFieldText)) {	
        	businessObjectFieldText.setLanguageId(languageId);	
        }else {
            businessObjectFieldText = existedBusinessObjectFieldText;        	
        }
        //Copy to Existed Text Model
        if(Objects.nonNull(businessObjectFieldDTO.getBusinessObjectFieldText())) {
        	BeanUtils.copyProperties(businessObjectFieldDTO.getBusinessObjectFieldText(), businessObjectFieldText);
        }
						
		//Set field to text
		businessObjectFieldText.setBusinessObjectField(businessObjectField);
		
		//Add text list
		businessObjectFieldTextList.add(businessObjectFieldText);
		
		//Set text list to field
		businessObjectField.setBusinessObjectFieldTextList(businessObjectFieldTextList);
		
		//Set Option List
		List<BusinessObjectFieldOption> bofOptionList = bofopRepo.findByBussinessObjectField_NameAndLanguageId(fieldName, languageId);
		if(bofOptionList.isEmpty()) {
			if(Objects.nonNull(businessObjectField.getBusinessObjectFieldOptionList())) {
				businessObjectField.getBusinessObjectFieldOptionList().clear();					
			}
		}else {
			businessObjectField.setBusinessObjectFieldOptionList(bofOptionList);	
		}
		        		
		return businessObjectField;
		
	}

	@Override
	public void deleteOneBusinessObjectField(String businessObjectName, String fieldName) {
		
		bofValidator.validateBusinessObjectConsistent(businessObjectName);
		BusinessObjectField businessObjectField = bofValidator.validateBusinessObjectField(businessObjectName, fieldName);
		bofRepo.delete(businessObjectField);
		
	}
	
}
