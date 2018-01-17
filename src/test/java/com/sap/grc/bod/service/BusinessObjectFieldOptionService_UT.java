package com.sap.grc.bod.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
import com.sap.grc.bod.repository.BusinessObjectFieldOptionRepository;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.service.impl.BaseServiceUT;
import com.sap.grc.bod.service.impl.BusinessObjectFieldOptionServiceImpl;
import com.sap.grc.bod.validator.BusinessObjectFieldOptionValidator;

@RunWith( SpringRunner.class )
public class BusinessObjectFieldOptionService_UT extends BaseServiceUT
{

	@Mock
	private BusinessObjectFieldOptionRepository bofoRepo;

	@Mock
	private BusinessObjectFieldRepository bofRepo;

	@Mock
	private BusinessObjectFieldOptionValidator bofoValidator;

	@InjectMocks
	private BusinessObjectFieldOptionServiceImpl businessObjectFieldOptionService;

	@Before
	public void setup()
	{
		super.setUp(this.getClass());
	}

	@Test
	public void createMultiBusinessObjectFieldOption_UT()
	{
		BusinessObject businessObject = new BusinessObject();
		businessObject.setUuid("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33");
		businessObject.setName("ROPA");
		businessObject.setDescription("ropa descritpion");
		
		BusinessObjectField businessObjectField = new BusinessObjectField();
		businessObjectField.setUuid("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
		businessObjectField.setName("CDF1");
		businessObjectField.setType(BusinessObjectFieldType.STRING);
		businessObjectField.setBusinessObject(businessObject);
		
		String fieldId = "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152";
		String languageId = "en";
		List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		businessObjectFieldOptionDTO.setFieldId(fieldId);
		businessObjectFieldOptionDTO.setDescription("value description 1");
		businessObjectFieldOptionDTO.setValue("value_1");
		businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
		
		when(bofRepo.findOne("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152")).thenReturn(businessObjectField);
		businessObjectFieldOptionService.createMultiBusinessObjectFieldOption(fieldId, languageId, businessObjectFieldOptionDTOList);
	}
}
