package com.sap.grc.bod.validator;

//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.BeanUtils;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
//import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
//import com.sap.grc.bod.exception.BusinessObjectCustomException;
//import com.sap.grc.bod.exception.BusinessObjectCustomException.ExceptionEnum;
//import com.sap.grc.bod.model.BusinessObject;
//import com.sap.grc.bod.model.BusinessObjectField;
//import com.sap.grc.bod.model.BusinessObjectFieldOption;
//import com.sap.grc.bod.model.BusinessObjectFieldText;
//import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
//import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
//import com.sap.grc.bod.repository.BusinessObjectRepository;
//import com.sap.grc.bod.service.BaseServiceUT;
//
//@RunWith( SpringRunner.class )
//public class BusinessObjectFieldValidator_UT extends BaseServiceUT
//{

//	@Mock
//	private BusinessObjectFieldRepository bofRepo;
//	
//	@Mock
//	private BusinessObjectRepository boRepo;
//	
//	@InjectMocks
//	private BusinessObjectFieldValidator bofValidator;
//
//	private String businessObjectName;
//	private BusinessObject businessObject = new BusinessObject();
//	private BusinessObjectFieldDTO bofDTO = new BusinessObjectFieldDTO();
//	private BusinessObjectFieldTextDTO bofTextDTO = new BusinessObjectFieldTextDTO();
//	private BusinessObjectField businessObjectField = new BusinessObjectField();
//	private BusinessObjectFieldText bofText = new BusinessObjectFieldText();
//	private List<BusinessObjectFieldText> bofTextList = new ArrayList<>();
//	private List<BusinessObjectFieldOption> bofOptionList = new ArrayList<>();
//	
//	@Before
//	public void setup()
//	{
//		super.setUp(this.getClass());
//		
//		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
//		businessObject.setName("ROPA");
//		businessObject.setDescription("ropa descritpion");
//		
//		//Field CDF1
//		bofTextDTO.setDescription("CDF1 en");
//		bofTextDTO.setFieldShortDescription("CDF1 short en");
//		
//		bofDTO.setName("CDF1");
//		bofDTO.setType(BusinessObjectFieldType.STRING);
//		bofDTO.setIsCustField(true);
//		bofDTO.setIsMandatory(false);
//		bofDTO.setIsMultiInput(false);
//		bofDTO.setIsValueSet(false);
//		bofDTO.setIsVisible(true);
//		bofDTO.setBusinessObjectFieldText(bofTextDTO);
//
//		BeanUtils.copyProperties(bofDTO, businessObjectField);
//		BeanUtils.copyProperties(bofTextDTO, bofText);
//		bofText.setLanguageId("en");
//		bofText.setUuid("3998A580-170F-4379-AAF8-2D9D355B2B9E");
//		bofTextList.add(bofText);
//		
//		businessObjectField.setUuid("5E85650E-EDF2-492A-8177-37E3764F6D57");
//		businessObjectField.setBusinessObject(businessObject);
//		businessObjectField.setBusinessObjectFieldTextList(bofTextList);
//		businessObjectField.setBusinessObjectFieldOptionList(bofOptionList);
//		
//	}
//	
//	@Test(expected=BusinessObjectCustomException.class)
//	public void validateBusinessObjectConsistent_case1_UT() {
//		
//		bofValidator.validateBusinessObjectConsistent(businessObjectName);
//		
//	}
//
//	@Test(expected=BusinessObjectCustomException.class)
//	public void validateBusinessObjectConsistent_case2_UT() {
//
//		businessObjectName = "IT";
//		bofValidator.validateBusinessObjectConsistent(businessObjectName);
//		
//	}	
//	
//	@Test(expected=BusinessObjectCustomException.class)
//	public void createBusinessObjectFieldValidation_case1_UT() {
//		
//		BusinessObjectFieldDTO bofDTONameNull = new BusinessObjectFieldDTO();
//		bofValidator.createBusinessObjectFieldValidation(bofDTONameNull);
//		
//	}
//
//	@Test(expected=BusinessObjectCustomException.class)
//	public void createBusinessObjectFieldValidation_case2_UT() {
//		
//		bofDTO.setName(" ");
//		bofValidator.createBusinessObjectFieldValidation(bofDTO);
//		
//	}	
//	
//	@Test(expected=BusinessObjectCustomException.class)
//	public void createBusinessObjectFieldValidation_case3_UT() {
//		
//		bofDTO.setName("Z CDF1");
//		bofValidator.createBusinessObjectFieldValidation(bofDTO);
//		
//	}
//	
//	@Test(expected=BusinessObjectCustomException.class)
//	public void createBusinessObjectFieldValidation_case4_UT() {
//	
//		when(bofRepo.findByName("CDF1")).thenReturn(businessObjectField);
//		bofValidator.createBusinessObjectFieldValidation(bofDTO);		
//	}
//	
//	@Test(expected=BusinessObjectCustomException.class)	
//	public void updateBusinessObjectFieldValidation_case1_UT() {
//		
//		String fieldName = "CDF11";
//		bofValidator.updateBusinessObjectFieldValidation(fieldName, bofDTO);
//	}
//	
//	@Test(expected=BusinessObjectCustomException.class)	
//	public void updateBusinessObjectFieldValidation_case2_UT() {
//		
//		String fieldName = "CDF1";
//		bofValidator.updateBusinessObjectFieldValidation(fieldName, bofDTO);
//	}
//	
//	@Test(expected=BusinessObjectCustomException.class)	
//	public void validateBusinessObjectField_case1_UT() {
//		
//		String fieldName = "CDF1";
//		bofValidator.validateBusinessObjectField(businessObjectName, fieldName);
//	}
//}
