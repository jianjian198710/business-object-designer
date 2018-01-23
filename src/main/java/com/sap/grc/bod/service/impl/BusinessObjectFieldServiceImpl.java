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
	private BusinessObjectFieldValidator bofValidator;
	
	@Override
	public List<BusinessObjectField> createBusinessObjecFields(String businessObjectId, 
           List<BusinessObjectFieldDTO> businessObjectFieldDTOList, UserBean user){
		
		BusinessObject businessObject = boRepo.findOne(businessObjectId);
		BusinessObjectFieldDTO businessObjectFieldDTO;
		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
		
		Iterator<BusinessObjectFieldDTO> bofDTOit = businessObjectFieldDTOList.iterator();
		while(bofDTOit.hasNext()) {
			
			BusinessObjectField businessObjectField = new BusinessObjectField();
			List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
			BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
			
			businessObjectFieldDTO = bofDTOit.next();
			
			//Validation
			bofValidator.createBusinessObjectFieldValidation(businessObjectId, businessObjectFieldDTO);		
			
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
	public BusinessObjectField updateOneBusinessObjectField(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO){
		
		BusinessObjectField businessObjectField = 
				this.preBusinessObjectField(businessObjectId, fieldId, businessObjectFieldDTO);
		return bofRepo.save(businessObjectField);
		
	}
	
	@Override
	public List<BusinessObjectField> updateMultiBusinessObjectField(String businessObjectId, 
		   List<BusinessObjectFieldDTO> businessObjectFieldDTOList){

		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
		
		Iterator<BusinessObjectFieldDTO> bofDTOit = businessObjectFieldDTOList.iterator();
		while(bofDTOit.hasNext()) {

			BusinessObjectFieldDTO businessObjectFieldDTO;
	
			businessObjectFieldDTO = bofDTOit.next();	
			String fieldId = businessObjectFieldDTO.getUuid();
	        
			BusinessObjectField businessObjectField = 
					this.preBusinessObjectField(businessObjectId, fieldId, businessObjectFieldDTO);
			
			businessObjectFieldList.add(businessObjectField);
			
		}
		
		return bofRepo.save(businessObjectFieldList);
	}

	private BusinessObjectField preBusinessObjectField(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO) {

		List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
		BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
		
		//Validation
		bofValidator.updateBusinessObjectFieldValidation(businessObjectId, fieldId, businessObjectFieldDTO);
		
		//Fetch field by fieldId(uuid)
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
		
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		BusinessObjectFieldText existedBusinessObjectFieldText = 
				   boftextRepo.findByBusinessObjectField_UuidAndLanguageId(fieldId, languageId);
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
		
		businessObjectField.getBusinessObjectFieldOptionList().clear();
		return businessObjectField;
		
	}
	
	@Override
	public BusinessObjectField findOneBusinessObjectField(String businessObjectId, String fieldId){
		
		BusinessObjectField businessObjectField = new BusinessObjectField();
		List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
		BusinessObjectFieldText preBusinessObjectFieldText = new BusinessObjectFieldText();
		
		BusinessObjectField preBusinessObjectField = bofRepo.findByBusinessObject_UuidAndUuid(businessObjectId,fieldId);
		if(Objects.isNull(preBusinessObjectField)) {
			return businessObjectField;
		}
		
		BeanUtils.copyProperties(preBusinessObjectField, businessObjectField);
		businessObjectField.getBusinessObjectFieldOptionList().clear();
		businessObjectField.getBusinessObjectFieldTextList().clear();
		
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		preBusinessObjectFieldText = 
				   boftextRepo.findByBusinessObjectField_UuidAndLanguageId(fieldId, languageId);
		if(!Objects.isNull(preBusinessObjectFieldText)) {
          businessObjectFieldTextList.add(preBusinessObjectFieldText);
          businessObjectField.setBusinessObjectFieldTextList(businessObjectFieldTextList);  
		}

		return businessObjectField;
	}
	
//	public List<BusinessObjectField> findAllBusinessObjectField(String businessObjectId){
//		
//		List<BusinessObjectField> prebusinessObjectFieldList = bofRepo.findByBusinessObject_Uuid(businessObjectId);
//		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
//		
//		Iterator<BusinessObjectField> bofIt = prebusinessObjectFieldList.iterator();
//		while(bofIt.hasNext()) {
//			BusinessObjectField prebusinessObjectField;
//            prebusinessObjectField = bofIt.next();	
//			String fieldId = prebusinessObjectField.getUuid();
//			BusinessObjectField businessObjectField = this.findOneBusinessObjectField(businessObjectId, fieldId);
//			if(!Objects.isNull(businessObjectField)) {
//				businessObjectFieldList.add(businessObjectField);			
//			}			
//		}	
//		
//		return businessObjectFieldList;
//	}
	
	public List<BusinessObjectField> findAllBusinessObjectField(String businessObjectName){
		return bofRepo.findByBusinessObject_Name(businessObjectName);
	}
}
