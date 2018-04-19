package com.sap.grc.bod.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.service.impl.BusinessObjectFieldOptionServiceImpl;
import com.sap.grc.bod.validator.BusinessObjectFieldOptionValidator;
import com.sap.grc.bod.validator.BusinessObjectFieldValidator;

@RunWith( SpringRunner.class )
public class BusinessObjectFieldOptionService_UT extends BaseServiceUT
{
//	@Mock
//	private BusinessObjectFieldOptionRepository bofoRepo;
//
//	@Mock
//	private BusinessObjectFieldRepository bofRepo;
//
//	@Mock
//	private BusinessObjectFieldOptionValidator bofoValidator;
//	
//	@Mock
//	private BusinessObjectFieldValidator bofValidator;
//
//	@InjectMocks
//	private BusinessObjectFieldOptionServiceImpl businessObjectFieldOptionService;
//
//	@Before
//	public void setup()
//	{
//		super.setUp(this.getClass());
//	}
//
//	@SuppressWarnings( "unchecked" )
//	@Test
//	public void createMultiBusinessObjectFieldOption_UT()
//	{
//		String boName = "ROPA";
//		String fieldName = "CDF1";
//		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
//		String languageId = "en";
//		
//		BusinessObject businessObject = new BusinessObject();
//		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
//		businessObject.setName(boName);
//		businessObject.setDescription("ropa descritpion");
//
//		BusinessObjectField businessObjectField = new BusinessObjectField();
//		businessObjectField.setUuid("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
//		businessObjectField.setName(fieldName);
//		businessObjectField.setType(BusinessObjectFieldType.STRING);
//		businessObjectField.setBusinessObject(businessObject);
//
//		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
//		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
//		businessObjectFieldOptionDTO.setDescription("value description 1");
//		businessObjectFieldOptionDTO.setValue("value_1");
//		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
//		
//		List<BusinessObjectFieldOption> createdList = new ArrayList<>();
//		BusinessObjectFieldOption createdBusinessObjectFieldOption = new BusinessObjectFieldOption();
//		createdBusinessObjectFieldOption.setUuid("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
//		createdBusinessObjectFieldOption.setFieldId(fieldId);
//		createdBusinessObjectFieldOption.setDescription("value description 1");
//		createdBusinessObjectFieldOption.setValue("value_1");
//		createdBusinessObjectFieldOption.setLanguageId(languageId);
//		createdList.add(createdBusinessObjectFieldOption);
//
//		when(bofValidator.validateBusinessObjectField(boName, fieldName)).thenReturn(businessObjectField);
//		when(bofoRepo.save(Mockito.anyList())).thenReturn(createdList);
//		List<BusinessObjectFieldOption> resultList =
//			businessObjectFieldOptionService
//				.createMultiBusinessObjectFieldOption(boName, fieldName, languageId, businessObjectFieldOptionDTOList);
//		assertEquals(resultList.size(),1);
//	}
//
//	@SuppressWarnings( "unchecked" )
//	@Test
//	public void updateMultiBusinessObjectFieldOption_UT()
//	{
//		String boName = "ROPA";
//		String fieldName = "CDF1";
//		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
//		String languageId = "en";
//
//		BusinessObject businessObject = new BusinessObject();
//		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
//		businessObject.setName(boName);
//		businessObject.setDescription("ropa descritpion");
//
//		BusinessObjectField businessObjectField = new BusinessObjectField();
//		businessObjectField.setUuid("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
//		businessObjectField.setName(fieldName);
//		businessObjectField.setType(BusinessObjectFieldType.STRING);
//		businessObjectField.setBusinessObject(businessObject);
//
//		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
//		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
//		businessObjectFieldOption.setUuid("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
//		businessObjectFieldOption.setFieldId(fieldId);
//		businessObjectFieldOption.setDescription("value description 1");
//		businessObjectFieldOption.setValue("value_1");
//		businessObjectFieldOption.setLanguageId(languageId);
//		businessObjectFieldOptionList.add(businessObjectFieldOption);
//
//		businessObjectField.setBusinessObjectFieldOptionList(businessObjectFieldOptionList);
//
//		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
//		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
//		businessObjectFieldOptionDTO.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
//		businessObjectFieldOptionDTO.setDescription("value description 1 updated");
//		businessObjectFieldOptionDTO.setValue("value_1 updated");
//		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
//
//
//		List<BusinessObjectFieldOption> updatedList = new ArrayList<>();
//		BusinessObjectFieldOption updatedBusinessObjectFieldOption = new BusinessObjectFieldOption();
//		updatedBusinessObjectFieldOption.setUuid("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
//		updatedBusinessObjectFieldOption.setFieldId(fieldId);
//		updatedBusinessObjectFieldOption.setDescription("value description 1 updated");
//		updatedBusinessObjectFieldOption.setValue("value_1 updated");
//		updatedBusinessObjectFieldOption.setLanguageId(languageId);
//		updatedList.add(updatedBusinessObjectFieldOption);
//		
//		when(bofValidator.validateBusinessObjectField(boName, fieldName)).thenReturn(businessObjectField);
//		when(bofoValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectField,businessObjectFieldOptionDTOList)).thenReturn(businessObjectFieldOptionList);
//		when(bofoRepo.save(Mockito.anyList())).thenReturn(updatedList);
//		List<BusinessObjectFieldOption> resultList =
//			businessObjectFieldOptionService
//				.updateMultiBusinessObjectFieldOption(boName, fieldName, businessObjectFieldOptionDTOList);
//		assertEquals(resultList.size(),1);
//	}
//
//	@Test
//	public void findAllBusinessObjectFieldOption_UT()
//	{
//		String boName = "ROPA";
//		String fieldName = "CDF1";
//		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
//		String languageId = "en";
//
//		BusinessObject businessObject = new BusinessObject();
//		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
//		businessObject.setName(boName);
//		businessObject.setDescription("ropa descritpion");
//
//		BusinessObjectField businessObjectField = new BusinessObjectField();
//		businessObjectField.setUuid("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
//		businessObjectField.setName(fieldName);
//		businessObjectField.setType(BusinessObjectFieldType.STRING);
//		businessObjectField.setBusinessObject(businessObject);
//
//		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
//		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
//		businessObjectFieldOption.setUuid("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
//		businessObjectFieldOption.setFieldId(fieldId);
//		businessObjectFieldOption.setDescription("value description 1");
//		businessObjectFieldOption.setValue("value_1");
//		businessObjectFieldOption.setLanguageId(languageId);
//		businessObjectFieldOptionList.add(businessObjectFieldOption);
//		
//		businessObjectField.setBusinessObjectFieldOptionList(businessObjectFieldOptionList);
//		
//		when(bofValidator.validateBusinessObjectField(boName, fieldName)).thenReturn(businessObjectField);
//		when(bofoRepo.findByFieldIdAndLanguageId(fieldId, languageId)).thenReturn(businessObjectFieldOptionList);
//		
//		List<BusinessObjectFieldOption> resultList =
//			businessObjectFieldOptionService.findAllBusinessObjectFieldOption(boName, fieldName, languageId);
//		assertEquals(resultList.size(), 1);
//	}
//	
//	@Test
//	public void deleteBusinessObjectFieldOption_UT(){
//		String boName = "ROPA";
//		String fieldName = "CDF1";
//		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
//		String fieldOptionId = "08E365EE-ADD4-476B-ACC1-5A2LDS92LF20";
//		String fieldOptionValue = "value_1";
//		String languageId = "en";
//
//		BusinessObject businessObject = new BusinessObject();
//		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
//		businessObject.setName(boName);
//		businessObject.setDescription("ropa descritpion");
//
//		BusinessObjectField businessObjectField = new BusinessObjectField();
//		businessObjectField.setUuid("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
//		businessObjectField.setName(fieldName);
//		businessObjectField.setType(BusinessObjectFieldType.STRING);
//		businessObjectField.setBusinessObject(businessObject);
//
//		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
//		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
//		businessObjectFieldOption.setUuid(fieldOptionId);
//		businessObjectFieldOption.setFieldId(fieldId);
//		businessObjectFieldOption.setDescription("value description 1");
//		businessObjectFieldOption.setValue(fieldOptionValue);
//		businessObjectFieldOption.setLanguageId(languageId);
//		
//		businessObjectFieldOptionList.add(businessObjectFieldOption);
//		businessObjectField.setBusinessObjectFieldOptionList(businessObjectFieldOptionList);
//		
//		when(bofValidator.validateBusinessObjectField(boName, fieldName)).thenReturn(businessObjectField);
//		businessObjectFieldOptionService.deleteBusinessObjectFieldOption(boName, fieldName, fieldOptionValue, languageId);
//	}
//	
//	@Test
//	public void deleteAllBusinessObjectFieldOption_UT(){
//		String boName = "ROPA";
//		String fieldName = "CDF1";
//		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
//		String fieldOptionId = "08E365EE-ADD4-476B-ACC1-5A2LDS92LF20";
//		String languageId = "en";
//
//		BusinessObject businessObject = new BusinessObject();
//		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
//		businessObject.setName(boName);
//		businessObject.setDescription("ropa descritpion");
//
//		BusinessObjectField businessObjectField = new BusinessObjectField();
//		businessObjectField.setUuid("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
//		businessObjectField.setName(fieldName);
//		businessObjectField.setType(BusinessObjectFieldType.STRING);
//		businessObjectField.setBusinessObject(businessObject);
//
//		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
//		BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
//		businessObjectFieldOption.setUuid(fieldOptionId);
//		businessObjectFieldOption.setFieldId(fieldId);
//		businessObjectFieldOption.setDescription("value description 1");
//		businessObjectFieldOption.setValue("value_1");
//		businessObjectFieldOption.setLanguageId(languageId);
//		businessObjectFieldOptionList.add(businessObjectFieldOption);
//		
//		when(bofValidator.validateBusinessObjectField(boName, fieldName)).thenReturn(businessObjectField);
//		businessObjectFieldOptionService.deleteAllBusinessObjectFieldOption(boName, fieldName,languageId);
//	}
}
