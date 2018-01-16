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
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldTextRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;
import com.sap.grc.bod.service.BusinessObjectFieldService;

@Service
public class BusinessObjectFieldServiceImpl implements BusinessObjectFieldService 
{
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Autowired
	private BusinessObjectRepository boRepo;
	
	@Autowired
	private BusinessObjectFieldTextRepository boftextRepo;
	
	private String languageId = LocaleContextHolder.getLocale().getLanguage();
	
	@Override
	public List<BusinessObjectField> createBusinessObjecFields(String businessObjectId, 
           List<BusinessObjectFieldDTO> businessObjectFieldDTOList, UserBean user){
		
		//Find BusinessObject Id
		BusinessObject businessObject = boRepo.findOne(businessObjectId);
		if(Objects.isNull(businessObject)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);
		}
		
		BusinessObjectFieldDTO businessObjectFieldDTO;
		List<BusinessObjectField> businessObjectFieldList = new ArrayList<>();
		
		Iterator<BusinessObjectFieldDTO> bofDTOit = businessObjectFieldDTOList.iterator();
		while(bofDTOit.hasNext()) {
			
			BusinessObjectField businessObjectField = new BusinessObjectField();
			List<BusinessObjectFieldText> businessObjectFieldTextList = new ArrayList<>();
			BusinessObjectFieldText businessObjectFieldText = new BusinessObjectFieldText();
			
			businessObjectFieldDTO = bofDTOit.next();
			
			//Validation
			//this.createBusinessObjectFieldValidation(businessObjectFieldDTO);		
			
			//Copy to business object field model
			BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
			businessObjectField.setCreatorBy(user.getUid());
			businessObjectField.setBusinessObject(businessObject);

			//Copy to business object field text model			
			BeanUtils.copyProperties(businessObjectFieldDTO.getBusinessObjectFieldText(), businessObjectFieldText);
			businessObjectFieldText.setLanguageId(languageId);
			
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
		//this.updateBusinessObjectFieldValidation(businessObjectId, fieldId, businessObjectFieldDTO);
		
		//Fetch field by fieldId(uuid)
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		
		BeanUtils.copyProperties(businessObjectFieldDTO, businessObjectField);
		
		BusinessObjectFieldText existedBusinessObjectFieldText = 
				   boftextRepo.findByBusinessObjectField_UuidAndLanguageId(fieldId, languageId);
        if(!Objects.isNull(existedBusinessObjectFieldText)) {
        	businessObjectFieldText = existedBusinessObjectFieldText;
        }
        //Copy to Existed Text Model
		BeanUtils.copyProperties(businessObjectFieldDTO.getBusinessObjectFieldText(), businessObjectFieldText);			
		//businessObjectFieldText.setLanguageId(languageId);
		
		//Set field to text
		businessObjectFieldText.setBusinessObjectField(businessObjectField);
		
		//Add text list
		businessObjectFieldTextList.add(businessObjectFieldText);
		
		//Set text list to field
		businessObjectField.setBusinessObjectFieldTextList(businessObjectFieldTextList);
		
		return businessObjectField;
		
	}
	
	@Override
	public BusinessObjectFieldDTO findOneBusinessObjectField(String businessObjectId, String fieldId){
		
		BusinessObjectFieldDTO businessObjectFieldDTO = new BusinessObjectFieldDTO();
		BusinessObjectFieldTextDTO businessObjectFieldTextDTO = new BusinessObjectFieldTextDTO();
		BusinessObjectFieldText preBusinessObjectFieldText = new BusinessObjectFieldText();
		
		BusinessObjectField preBusinessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(preBusinessObjectField)) {
			return businessObjectFieldDTO;
		}
		
		BeanUtils.copyProperties(preBusinessObjectField, businessObjectFieldDTO);
		businessObjectFieldDTO.setBusinessObjectId(businessObjectId);
		
		preBusinessObjectFieldText = 
				   boftextRepo.findByBusinessObjectField_UuidAndLanguageId(fieldId, languageId);
		if(!Objects.isNull(preBusinessObjectFieldText)) {
		  BeanUtils.copyProperties(preBusinessObjectFieldText, businessObjectFieldTextDTO);	
		  businessObjectFieldDTO.setBusinessObjectFieldText(businessObjectFieldTextDTO);
		}

		return businessObjectFieldDTO;
	}
	
	public List<BusinessObjectFieldDTO> findAllBusinessObjectField(String businessObjectId){
		
		List<BusinessObjectField> businessObjectFieldList = bofRepo.findByBusinessObject_Uuid(businessObjectId);
		List<BusinessObjectFieldDTO> businessObjectFieldDTOList = new ArrayList<>();
		Iterator<BusinessObjectField> bofIt = businessObjectFieldList.iterator();
		while(bofIt.hasNext()) {

			BusinessObjectField businessObjectField;
	
			businessObjectField = bofIt.next();	
			String fieldId = businessObjectField.getUuid();
			BusinessObjectFieldDTO businessObjectFieldDTO = this.findOneBusinessObjectField(businessObjectId, fieldId);
			if(!Objects.isNull(businessObjectFieldDTO)) {
				businessObjectFieldDTOList.add(businessObjectFieldDTO);			
			}			
		}	
		
		return businessObjectFieldDTOList;
	}
	
	
	//TODO enhancement
	private void createBusinessObjectFieldValidation(BusinessObjectFieldDTO businessObjectFieldDTO){
		
		//Business Object related validation
		if(Objects.isNull(businessObjectFieldDTO.getBusinessObjectId())){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_errInput);
		}
		
		BusinessObject businessObject = boRepo.findByUuid(businessObjectFieldDTO.getBusinessObjectId());
		if(Objects.isNull(businessObject)) {
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObject_isNotExisted);			
		}
		
		//Field related validation
		if(businessObjectFieldDTO.getName().isEmpty()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isEmpty);
		}	
		
	}
	
	//TODO enhancement
	private void updateBusinessObjectFieldValidation(String businessObjectId, String fieldId, BusinessObjectFieldDTO businessObjectFieldDTO){
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}

	}
}
