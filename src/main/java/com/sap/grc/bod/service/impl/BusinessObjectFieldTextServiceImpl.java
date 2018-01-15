package com.sap.grc.bod.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldTextRepository;
import com.sap.grc.bod.service.BusinessObjectFieldTextService;

@Service
public class BusinessObjectFieldTextServiceImpl implements BusinessObjectFieldTextService
{
	@Autowired
	private BusinessObjectFieldTextRepository boftRepo;
	
	@Autowired
	private BusinessObjectFieldRepository bofRepo;
	
	@Override
	public BusinessObjectFieldText createBusinessObjectFieldText(String fieldId, BusinessObjectFieldTextDTO businessObjectFieldTextDTO){
		//TODO validation businessObjectFieldTextDTO
		BusinessObjectField businessObjectField = bofRepo.findOne(fieldId);
		if(Objects.isNull(businessObjectField)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectField_isNotExisted);
		}
		BusinessObjectFieldText businessObjectFieldText = businessObjectFieldTextDTO.convertToModel();
		businessObjectFieldText.setBusinessObjectField(businessObjectField);
		return boftRepo.save(businessObjectFieldText);
	}
	
	@Override
	public List<BusinessObjectFieldText> updateMultiBusinessObjectFieldText(List<String> fieldTextIdList, List<BusinessObjectFieldTextDTO> businessObjectFieldTextDTOList){
		//TODO validation businessObjectFieldTextDTOList
		List<BusinessObjectFieldText> businessObjectFieldTextList = boftRepo.findByUuidIn(fieldTextIdList);
		for(BusinessObjectFieldText businessObjectFieldText: businessObjectFieldTextList){
			for(BusinessObjectFieldTextDTO businessObjectFieldTextDTO: businessObjectFieldTextDTOList){
/*				if(businessObjectFieldText.getUuid().equals(businessObjectFieldTextDTO.getUuid())){
					BeanUtils.copyProperties(businessObjectFieldTextDTO, businessObjectFieldText);
				}*/
			}
		}
		return boftRepo.save(businessObjectFieldTextList);
	}
	
	@Override
	public BusinessObjectFieldText findOneBusinessObjectFieldText(String fieldId, String languageId){
		//return boftRepo.findByFieldIdAndLanguageId(fieldId,languageId);
		//TODO
		return null;
	}
	
//	@Override
//	public List<BusinessObjectFieldText> findAllBusinessObjectFieldText(String businessObjectId, String fieldId){
//		return boftRepo.findByBusinessObjectIdAndFieldId(businessObjectId, fieldId);
//	}
}
