package com.sap.grc.bod.validator;

import java.util.HashSet;
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
		this.dtoListValidation(businessObjectFieldOptionDTOList);
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
	
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOptionValidation(List<String> fieldOptionIdList, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		this.dtoListValidation(businessObjectFieldOptionDTOList);
		//no null uuid
		if(fieldOptionIdList.stream().anyMatch(t->this.isNullOrEmpty(t))||businessObjectFieldOptionDTOList.stream().anyMatch(t->this.isNullOrEmpty(t.getFieldOpitonId()))){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_IdNotExisted);
		}
		
		Set<String> optionSet = new HashSet<>();
		Set<String> optionDTOSet = businessObjectFieldOptionDTOList.stream().map(t->t.getFieldOpitonId()).collect(Collectors.toSet());
		for(String fieldOptionId: fieldOptionIdList){
			optionSet.add(fieldOptionId);
		}
		//no duplicated optionId
		if(optionSet.size()!=fieldOptionIdList.size() || optionDTOSet.size()!=businessObjectFieldOptionDTOList.size()){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_DuplicatedId);
		}
		
		//one to one optionIdList <--> optionDTOList
		if(!optionSet.containsAll(optionDTOSet) || !optionDTOSet.containsAll(optionSet)){
			throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_errInput);
		}
		//each optionId is existed in db
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = bofoRepo.findByUuidIn(fieldOptionIdList);
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
	
	private void dtoListValidation(List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		for(BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO: businessObjectFieldOptionDTOList){
			if(this.isNullOrEmpty(businessObjectFieldOptionDTO.getFieldId())||this.isNullOrEmpty(businessObjectFieldOptionDTO.getValue())){
				throw new BusinessObjectCustomException(ExceptionEnum.BusinessObjectFieldOption_errInput);
			}
		}
	}
}
