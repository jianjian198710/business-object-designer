package com.sap.grc.bod.validator;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.exception.BusinessObjectCustomException;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.service.BaseServiceUT;

@RunWith( SpringRunner.class )
public class BusinessObjectFieldOptionValidator_UT extends BaseServiceUT
{

	@Mock
	private BusinessObjectFieldOptionRepository bofoRepo;
	
	@InjectMocks
	private BusinessObjectFieldOptionValidator businessObjectFieldOptionValidator;
	
	/*
	 * check option has non-null id when create
	 */
	@Test(expected = BusinessObjectCustomException.class)
	public void createMultiBusinessObjectFieldOptionValidation_case1_UT(){
		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value_1");
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionValidator.createMultiBusinessObjectFieldOptionValidation(fieldId, businessObjectFieldOptionDTOList);
	}
	
	/*
	 * check duplicate option value
	 */
	@Test(expected = BusinessObjectCustomException.class)
	public void createMultiBusinessObjectFieldOptionValidation_case2_UT(){
		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value_1");

		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO_two = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO_two.setDescription("value description 1");
		businessObjectFieldOptionDTO_two.setValue("value_1");
		
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO_two);
		businessObjectFieldOptionValidator.createMultiBusinessObjectFieldOptionValidation(fieldId, businessObjectFieldOptionDTOList);
	}
	
	/*
	 * check empty option value
	 */
	@Test(expected = BusinessObjectCustomException.class)
	public void createMultiBusinessObjectFieldOptionValidation_case3_UT(){
		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("");

		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO_two = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO_two.setDescription("value description 2");
		businessObjectFieldOptionDTO_two.setValue("value_2");
		
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO_two);
		businessObjectFieldOptionValidator.createMultiBusinessObjectFieldOptionValidation(fieldId, businessObjectFieldOptionDTOList);
	}
	
	/*
	 * option option value contains space
	 */
	@Test(expected = BusinessObjectCustomException.class)
	public void createMultiBusinessObjectFieldOptionValidation_case4_UT(){
		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value 1");
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionValidator.createMultiBusinessObjectFieldOptionValidation(fieldId, businessObjectFieldOptionDTOList);
	}
	
	/*
	 * check duplicate id
	 */
	@Test(expected = BusinessObjectCustomException.class)
	public void updateMultiBusinessObjectFieldOptionValidation_case1_UT(){
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value_1");

		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO_two = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO_two.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
		businessObjectFieldOptionDTO_two.setDescription("value description 2");
		businessObjectFieldOptionDTO_two.setValue("value_2");
		
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO_two);
		businessObjectFieldOptionValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectFieldOptionDTOList);
	}
	
	/*
	 * check each optionId is existed in db
	 */
	@SuppressWarnings( "unchecked" )
	@Test(expected = BusinessObjectCustomException.class)
	public void updateMultiBusinessObjectFieldOptionValidation_case2_UT(){
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value_1");

		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO_two = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO_two.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF21");
		businessObjectFieldOptionDTO_two.setDescription("value description 2");
		businessObjectFieldOptionDTO_two.setValue("value_2");
		
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO_two);
		
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
		when(bofoRepo.findByUuidIn(Mockito.anyList())).thenReturn(businessObjectFieldOptionList);
		businessObjectFieldOptionValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectFieldOptionDTOList);
	}
	
	/*
	 * check null option id when update
	 */
	@Test(expected = BusinessObjectCustomException.class)
	public void updateMultiBusinessObjectFieldOptionValidation_case3_UT(){
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value_1");

		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO_two = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO_two.setFieldOpitonId("08E365EE-ADD4-476B-ACC1-5A2LDS92LF20");
		businessObjectFieldOptionDTO_two.setDescription("value description 2");
		businessObjectFieldOptionDTO_two.setValue("value_2");
		
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO_two);
		
		businessObjectFieldOptionValidator.updateMultiBusinessObjectFieldOptionValidation(businessObjectFieldOptionDTOList);
	}
	
	@Test(expected = BusinessObjectCustomException.class)	
	public void deleteBusinessObjectFieldOptionValidation_UT(){
		String fieldOptionId = "08E365EE-ADD4-476B-ACC1-5A2LDS92LF20";
		when(bofoRepo.findOne(fieldOptionId)).thenReturn(null);
		businessObjectFieldOptionValidator.deleteBusinessObjectFieldOptionValidation(fieldOptionId);
	}
}
