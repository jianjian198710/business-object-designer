package com.sap.grc.bod.service;

import java.util.List;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionService
{
	public List<BusinessObjectFieldOption> createMultiBusinessObjectFieldOption(String boName, String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList);
	public List<BusinessObjectFieldOption> updateMultiBusinessObjectFieldOption(String boName, String fieldId, String languageId, List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList);
//	public List<BusinessObjectFieldOption> findAllBusinessObjectFieldOption(String boName, String fieldId, String languageId);
	public void deleteBusinessObjectFieldOption(String boName, String fieldId, String fieldOptionValue);
	public void deleteAllBusinessObjectFieldOption(String boName, String fieldId);
}
