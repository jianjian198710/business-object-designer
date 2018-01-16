package com.sap.grc.bod.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.grc.bod.constant.ControllerPathConstant;
import com.sap.grc.bod.model.BusinessObject;

@ActiveProfiles({ "integration_test" })
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusinessObjectFieldController_IT
{
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void findAllbusinessObject_IT() throws Exception{
		MvcResult content_result =
			mockMvc.perform(get(ControllerPathConstant.BUSINESS_OBJECT_DEFAULT)).andExpect(status().is(200)).andReturn();
		String content = content_result.getResponse().getContentAsString();
		List<BusinessObject> resList = mapper.readValue(content, new TypeReference<List<BusinessObject>>() {});
		assertEquals(resList.size(), 1);
	}
}
