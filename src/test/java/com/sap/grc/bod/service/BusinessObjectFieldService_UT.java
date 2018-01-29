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
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.model.UserBean;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldTextRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;
import com.sap.grc.bod.service.impl.BusinessObjectFieldServiceImpl;
import com.sap.grc.bod.validator.BusinessObjectFieldValidator;

@RunWith( SpringRunner.class )
public class BusinessObjectFieldService_UT extends BaseServiceUT
{

	@Mock
	private BusinessObjectFieldRepository bofRepo;
	
	@Mock
	private BusinessObjectRepository boRepo;
	
	@Mock
	private BusinessObjectFieldTextRepository boftextRepo;
	
	@Mock
	private BusinessObjectFieldOptionRepository bofopRepo;
	
	@Mock
	private BusinessObjectFieldValidator bofValidator;
	
	
	@InjectMocks
	private BusinessObjectFieldServiceImpl bofServiceImpl;
	
	private BusinessObject businessObject = new BusinessObject();
	private String businessObjectName;
	private List<BusinessObjectFieldDTO> bofDTOList = new ArrayList<>();
	private BusinessObjectFieldDTO bofDTO = new BusinessObjectFieldDTO();
	private BusinessObjectFieldTextDTO bofTextDTO = new BusinessObjectFieldTextDTO();
	private BusinessObjectField businessObjectField = new BusinessObjectField();
	private List<BusinessObjectField> bofList = new ArrayList<>();
	private BusinessObjectFieldText bofText = new BusinessObjectFieldText();
	private List<BusinessObjectFieldText> bofTextList = new ArrayList<>();
	private UserBean userBean = new UserBean("","","test_user","tenant_ut");
	private List<BusinessObjectFieldOption> bofOptionList = new ArrayList<>();
	
	@Before
	public void setup()
	{
		super.setUp(this.getClass());
		
		businessObjectName = "ROPA";
		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
		businessObject.setName(businessObjectName);
		businessObject.setDescription("ropa descritpion");
		
		//Field CDF1
		bofTextDTO.setDescription("CDF1 en");
		bofTextDTO.setFieldShortDescription("CDF1 short en");
		
		bofDTO.setName("CDF1");
		bofDTO.setType(BusinessObjectFieldType.STRING);
		bofDTO.setIsCustField(true);
		bofDTO.setIsMandatory(false);
		bofDTO.setIsMultiInput(false);
		bofDTO.setIsValueSet(false);
		bofDTO.setIsVisible(true);
		bofDTO.setBusinessObjectFieldText(bofTextDTO);
		bofDTOList.add(bofDTO);
		
		BeanUtils.copyProperties(bofDTO, businessObjectField);
		BeanUtils.copyProperties(bofTextDTO, bofText);
		bofText.setLanguageId("en");
		bofText.setUuid("3998A580-170F-4379-AAF8-2D9D355B2B9E");
		bofTextList.add(bofText);
		
		businessObjectField.setUuid("5E85650E-EDF2-492A-8177-37E3764F6D57");
		businessObjectField.setBusinessObject(businessObject);
		businessObjectField.setBusinessObjectFieldTextList(bofTextList);
		businessObjectField.setBusinessObjectFieldOptionList(bofOptionList);
		bofList.add(businessObjectField);
		
		//Field CDF2
		bofTextDTO = new BusinessObjectFieldTextDTO();
		bofTextDTO.setDescription("CDF2 en");
		bofTextDTO.setFieldShortDescription("CDF2 short en");
		
		bofDTO = new BusinessObjectFieldDTO();
		bofDTO.setName("CDF2");
		bofDTO.setType(BusinessObjectFieldType.NUMBER);
		bofDTO.setIsCustField(true);
		bofDTO.setIsMandatory(false);
		bofDTO.setIsMultiInput(false);
		bofDTO.setIsValueSet(false);
		bofDTO.setIsVisible(true);
		bofDTO.setBusinessObjectFieldText(bofTextDTO);
		bofDTOList.add(bofDTO);		
		
        bofText = new BusinessObjectFieldText();
        bofTextList = new ArrayList<BusinessObjectFieldText>();
		BeanUtils.copyProperties(bofTextDTO, bofText);
		bofText.setLanguageId("en");
		bofText.setUuid("B4268A90-7BFE-4918-B284-1E32CFFE8D85");
		bofTextList.add(bofText);
		
		businessObjectField = new BusinessObjectField();
		BeanUtils.copyProperties(bofDTO, businessObjectField);
		businessObjectField.setUuid("80FFC972-8D4F-4683-B693-75210EAE0240");
		businessObjectField.setBusinessObject(businessObject);
		businessObjectField.setBusinessObjectFieldTextList(bofTextList);
		businessObjectField.setBusinessObjectFieldOptionList(bofOptionList);
		bofList.add(businessObjectField);		

		//Field CDF3
		bofTextDTO = new BusinessObjectFieldTextDTO();
		bofTextDTO.setDescription("CDF3 en");
		bofTextDTO.setFieldShortDescription("CDF3 short en");

		bofDTO = new BusinessObjectFieldDTO();		
		bofDTO.setName("CDF3");
		bofDTO.setType(BusinessObjectFieldType.LONG_TEXT);
		bofDTO.setIsCustField(true);
		bofDTO.setIsMandatory(false);
		bofDTO.setIsMultiInput(false);
		bofDTO.setIsValueSet(false);
		bofDTO.setIsVisible(true);
		bofDTO.setBusinessObjectFieldText(bofTextDTO);
		bofDTOList.add(bofDTO);		

        bofText = new BusinessObjectFieldText();
        bofTextList = new ArrayList<BusinessObjectFieldText>();
		BeanUtils.copyProperties(bofTextDTO, bofText);
		bofText.setLanguageId("en");
		bofText.setUuid("D17161C0-F898-426B-928F-462DB2D2B7FC");
		bofTextList.add(bofText);
		
		businessObjectField = new BusinessObjectField();
		BeanUtils.copyProperties(bofDTO, businessObjectField);
		businessObjectField.setUuid("C6A0DF6C-BEEA-435D-A9B6-1D1252FA6E90");
		businessObjectField.setBusinessObject(businessObject);
		businessObjectField.setBusinessObjectFieldTextList(bofTextList);
		businessObjectField.setBusinessObjectFieldOptionList(bofOptionList);		
		bofList.add(businessObjectField);	
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void createBusinessObjecFields_UT() {
		
		when(boRepo.findByName(businessObjectName)).thenReturn(businessObject);
		when(bofRepo.findByName("CDF1")).thenReturn(businessObjectField);
		when(bofRepo.findByName("CDF2")).thenReturn(businessObjectField);
		when(bofRepo.save(Mockito.anyList())).thenReturn(bofList);
		List<BusinessObjectField> resultList = bofServiceImpl.createBusinessObjecFields(businessObjectName, bofDTOList, userBean);
		assertEquals(resultList.size(),3);
	
	}
	
	@Test
	public void updateOneBusinessObjectField_UT() {
        
		BusinessObjectField bofCDF1 = bofList.get(0);
		BusinessObjectFieldText bofText = bofCDF1.getBusinessObjectFieldTextList().get(0);
		BusinessObjectFieldDTO bofCDF1DTO = bofDTOList.get(0);
		bofCDF1DTO.setIsMandatory(true);
		
		when(boRepo.findByName(businessObjectName)).thenReturn(businessObject);
		when(bofRepo.findByName("CDF1")).thenReturn(bofCDF1);
		when(boftextRepo.findByBusinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofText);
		when(bofopRepo.findByBussinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofOptionList);
		when(bofRepo.save(bofCDF1)).thenReturn(bofCDF1);	
		
		BusinessObjectField result = 
				bofServiceImpl.updateOneBusinessObjectField( businessObjectName, "CDF1", bofCDF1DTO );
		assertEquals(result.getIsMandatory(), true);
	}
		
	@SuppressWarnings( "unchecked" )		
	@Test
	public void updateMultiBusinessObjectField_UT() {
        
		List<BusinessObjectFieldDTO> bofDTOUpdateList = new ArrayList<>();
		
		BusinessObjectField bofCDF1 = bofList.get(0);
		BusinessObjectFieldText bofCDF1Text = bofCDF1.getBusinessObjectFieldTextList().get(0);
		BusinessObjectFieldDTO bofCDF1DTO = bofDTOList.get(0);
		bofCDF1DTO.setIsMultiInput(true);
		bofDTOUpdateList.add(bofCDF1DTO);
		
		BusinessObjectField bofCDF2 = bofList.get(1);
		BusinessObjectFieldText bofCDF2Text = bofCDF2.getBusinessObjectFieldTextList().get(0);
		BusinessObjectFieldDTO bofCDF2DTO = bofDTOList.get(1);
		bofCDF2DTO.setIsMultiInput(true);
		bofDTOUpdateList.add(bofCDF2DTO);
		
		when(boRepo.findByName(businessObjectName)).thenReturn(businessObject);
		when(bofRepo.findByName("CDF1")).thenReturn(bofCDF1);
		when(boftextRepo.findByBusinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofCDF1Text);
		when(bofopRepo.findByBussinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofOptionList);
		when(bofRepo.findByName("CDF2")).thenReturn(bofCDF2);
		when(boftextRepo.findByBusinessObjectField_NameAndLanguageId("CDF2","en")).thenReturn(bofCDF2Text);
		when(bofopRepo.findByBussinessObjectField_NameAndLanguageId("CDF2","en")).thenReturn(bofOptionList);		
		when(bofRepo.save(Mockito.anyList())).thenReturn(bofList);;	
		
		List<BusinessObjectField> resultList = 
				bofServiceImpl.updateMultiBusinessObjectField( businessObjectName, bofDTOUpdateList );
		assertEquals(resultList.get(0).getIsMultiInput(), true);
		assertEquals(resultList.get(1).getIsMultiInput(), true);		
	}
	
	@Test
	public void findOneBusinessObjectField_UT() {

		BusinessObjectField bofCDF1 = bofList.get(0);
		BusinessObjectFieldText bofCDF1Text = bofCDF1.getBusinessObjectFieldTextList().get(0);
		
		when(bofRepo.findByBusinessObject_NameAndName(businessObjectName,"CDF1")).thenReturn(bofCDF1);
		when(boftextRepo.findByBusinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofCDF1Text);
		when(bofopRepo.findByBussinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofOptionList);
		BusinessObjectField result = bofServiceImpl.findOneBusinessObjectField(businessObjectName, "CDF1");
		assertEquals(result.getName(), "CDF1");
	}
	
	@Test
	public void findAllBusinessObjectField_UT() {
		
		BusinessObjectField bofCDF1 = bofList.get(0);
		BusinessObjectFieldText bofCDF1Text = bofCDF1.getBusinessObjectFieldTextList().get(0);		

		BusinessObjectField bofCDF2 = bofList.get(1);
		BusinessObjectFieldText bofCDF2Text = bofCDF2.getBusinessObjectFieldTextList().get(0);

		when(bofRepo.findByBusinessObject_NameAndName(businessObjectName,"CDF1")).thenReturn(bofCDF1);
		when(boftextRepo.findByBusinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofCDF1Text);
		when(bofopRepo.findByBussinessObjectField_NameAndLanguageId("CDF1","en")).thenReturn(bofOptionList);
		when(bofRepo.findByBusinessObject_NameAndName(businessObjectName,"CDF2")).thenReturn(bofCDF2);
		when(boftextRepo.findByBusinessObjectField_NameAndLanguageId("CDF2","en")).thenReturn(bofCDF2Text);
		when(bofopRepo.findByBussinessObjectField_NameAndLanguageId("CDF2","en")).thenReturn(bofOptionList);		
		when(bofRepo.findByBusinessObject_Name(businessObjectName)).thenReturn(bofList);
		List<BusinessObjectField> resultList = bofServiceImpl.findAllBusinessObjectField(businessObjectName);
		assertEquals(resultList.get(0).getName(), "CDF1");	
		assertEquals(resultList.get(1).getName(), "CDF2");		
	}
	
	@Test
	public void deleteOneBusinessObjectField_UT() {
	
		BusinessObjectField bofCDF3 = bofList.get(2);
		
		when(boRepo.findByName(businessObjectName)).thenReturn(businessObject);
		when(bofValidator.validateBusinessObjectField(businessObjectName, "CDF3")).thenReturn(bofCDF3);
		bofServiceImpl.deleteOneBusinessObjectField(businessObjectName, "CDF3");
		
	}
	
}
