package com.sap.grc.bod.validator;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;

@Component
public class BusinessObjectFieldOptionValidator
{
	@Autowired
	private BusinessObjectFieldOptionRepository bofoRepo;
	
	public void createMultiBusinessObjectFieldOptionValidation(String fieldId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		this.dtoListValidation(businessObjectFieldOptionDTOList,false);
		//no non-null uuid
		if(!businessObjectFieldOptionDTOList.stream().allMatch(t->Objects.isNull(t.getFieldOpitonId()))){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_IdAssigned);
		}
		
		//no duplicated value
		Set<String> valueSet = businessObjectFieldOptionDTOList.stream().map(t->t.getValue()).collect(Collectors.toSet());
		if(valueSet.size()!=businessObjectFieldOptionDTOList.size()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_DuplicatedId);
		}
	}
	
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOptionValidation(List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		this.dtoListValidation(businessObjectFieldOptionDTOList,true);
		
		Set<String> optionDTOIdSet = businessObjectFieldOptionDTOList.stream().map(t->t.getFieldOpitonId()).collect(Collectors.toSet());
		//no duplicated optionId
		if(optionDTOIdSet.size()!=businessObjectFieldOptionDTOList.size()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_DuplicatedId);
		}
		
		List<String> optionDTOIdList = businessObjectFieldOptionDTOList.stream().map(t->t.getFieldOpitonId()).collect(Collectors.toList());
		//each optionId is existed in db
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoRepo.findByUuidIn(optionDTOIdList);
		if(businessObjectFieldOptionList.size() != businessObjectFieldOptionDTOList.size()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_IdNotExisted);
		}
		return businessObjectFieldOptionList;
	}
	
	public void deleteBusinessObjectFieldOptionValidation(String fieldOptionId){
		if(Objects.isNull(bofoRepo.findOne(fieldOptionId))){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_IdNotExisted);
		}
	}
	
	private boolean isNullOrEmpty(String str){
		if(Objects.isNull(str) || str.trim().length()==0 ){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isContainEmpty(String str){
		if(str.replaceAll("\\s+","").length()!=str.length()){
			return true;
		}else{
			return false;
		}
	}
	
	private void dtoListValidation(List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList, boolean isUpdate){
		for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
			if(this.isNullOrEmpty(businessObjectFieldOptionDTO.getValue())||this.isContainEmpty(businessObjectFieldOptionDTO.getValue())){
				throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_errInput);
			}
			//check field option id not null when update
			if(isUpdate){
				if(this.isNullOrEmpty(businessObjectFieldOptionDTO.getFieldOpitonId())){
					throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_errInput);
				}
			}
		}
	}
}
