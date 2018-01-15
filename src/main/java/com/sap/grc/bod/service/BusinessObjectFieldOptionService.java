package com.sap.grc.bod.service;

import java.util.List;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionService
{
//	public BusinessObjectFieldOption createBusinessObjectFieldOption(String fieldId, String languageId, BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO);
	public List<BusinessObjectFieldOption> createMultiBusinessObjectFieldOption(String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList);
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(List<String> fieldOptionIdList, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList);
	public List<BusinessObjectFieldOption> findAllBusinessObjectFieldOption(String fieldId, String languageId);
	public void deleteBusinessObjectFieldOption(String fieldOptionId);
	public void deleteAllBusinessObjectFieldOption(String fieldId, String languageId);
}
