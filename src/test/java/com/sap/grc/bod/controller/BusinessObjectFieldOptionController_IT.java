package com.sap.grc.bod.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.grc.bod.constant.ControllerPathConstant;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.util.TestUtil;

@ActiveProfiles("integration_test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusinessObjectFieldOptionController_IT
{
	/*
	 * TODO jwt token set up after security enable
	 */
	private String url = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT + "/4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33"
							+ ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/A7F21EBC-3F4E-4767-85B6-F6C1AE15F152"
							+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void findAllBusinessObjectFieldOption_IT() throws Exception
	{
		MvcResult content_result =
			mockMvc.perform(get(url)).andExpect(status().is(200)).andReturn();
		String content = content_result.getResponse().getContentAsString();
		List<BusinessObjectFieldOption> resList = mapper.readValue(content, new TypeReference<List<BusinessObjectFieldOption>>(){});
		assertEquals(resList.size(), 2);
		assertEquals(resList.get(0).getValue(), "value_1");
		assertEquals(resList.get(1).getValue(), "value_2");
	}
	
	@Test
	public void createMultiBusinessObjectFieldOption_IT() throws Exception{
		 List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList = new ArrayList<>();
		 BusinessObjectFieldOptionDTO businessObjectFieldOptionDTO = new BusinessObjectFieldOptionDTO();
		 businessObjectFieldOptionDTO.setValue("value_3");
		 businessObjectFieldOptionDTO.setDescription("value description 3");
		 businessObjectFieldOptionDTO.setFieldId("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
		 
		 businessObjectFieldOptionDTOList.add(businessObjectFieldOptionDTO);
			MvcResult content_result =
				mockMvc.perform(post(url).content(TestUtil.toJson(businessObjectFieldOptionDTOList)).contentType(APPLICATION_JSON_UTF8)).andExpect(status().is(201)).andReturn();
			String content = content_result.getResponse().getContentAsString();
			List<BusinessObjectFieldOption> resList = mapper.readValue(content, new TypeReference<List<BusinessObjectFieldOption>>(){});
			assertEquals(resList.size(), 1);
			assertNotNull(resList.get(0).getUuid());
	}
	
	@Test
	public void updateMultiBusinessObjectFieldOption_IT() throws Exception{
		
	}
}
