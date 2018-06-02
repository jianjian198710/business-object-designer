package com.sap.grc.bod.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
import com.sap.grc.bod.util.TestUtil;

@ActiveProfiles({ "integration_test" })
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusinessObjectFieldController_IT
{
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();

//	private EmbeddedAMQPBroker amqpBroker = new EmbeddedAMQPBroker();
//
//	@Before
//	public void startup() throws Exception{
//		amqpBroker.startBroker();
//	}
//	
//	@After
//	public void tearDonw(){
//		amqpBroker.stopBroker();
//	}
	
	@Test
	public void test1_findAllBusinessObjectField_IT() throws Exception{
		
		String url =
				ControllerPathConstant.BUSINESS_OBJECT_DEFAULT
					+ "/ROPA"
					+ ControllerPathConstant.BUSINESS_OBJECT_FIELD;
		MvcResult content_result =
			mockMvc.perform(get(url)).andExpect(status().is(200)).andReturn();
		String content = content_result.getResponse().getContentAsString();
		List<BusinessObjectField> resList = mapper.readValue(content, new TypeReference<List<BusinessObjectField>>() {});
		assertEquals(resList.size(), 2);
	}
	
	@Test
	public void test2_findOneBusinessObjectField_IT() throws Exception{
		String url =
				ControllerPathConstant.BUSINESS_OBJECT_DEFAULT
					+ "/ROPA"
					+ ControllerPathConstant.BUSINESS_OBJECT_FIELD
					+ "/CDF1";
		MvcResult content_result =
				mockMvc.perform(get(url)).andExpect(status().is(200)).andReturn();
			String content = content_result.getResponse().getContentAsString();
			BusinessObjectField result = mapper.readValue(content, new TypeReference<BusinessObjectField>() {});
			assertEquals(result.getId(),"CDF1");		
	}
	
	@Test
	public void test3_createBusinessObjecFields_IT() throws Exception{
		String url =
				ControllerPathConstant.BUSINESS_OBJECT_DEFAULT
					+ "/ROPA"
					+ ControllerPathConstant.BUSINESS_OBJECT_FIELD;
		
		BusinessObjectFieldDTO bofDTO = new BusinessObjectFieldDTO();
		BusinessObjectFieldTextDTO boftDTO = new BusinessObjectFieldTextDTO();

		bofDTO.setId("CDF3");
		bofDTO.setType(BusinessObjectFieldType.STRING);
		bofDTO.setIsCustField(true);
		bofDTO.setIsMandatory(false);
		bofDTO.setIsMultiInput(false);
		bofDTO.setIsVisible(true);

		boftDTO.setName("Custom Field 3");
		bofDTO.setBusinessObjectFieldText(boftDTO);

		MvcResult content_result =
				mockMvc.perform(post(url).content(TestUtil.toJson(bofDTO)).
						contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().is2xxSuccessful()).andReturn();
		String content = content_result.getResponse().getContentAsString();
		BusinessObjectField result =
				mapper.readValue(content, new TypeReference<BusinessObjectField>()
				{
				});
		
		assertEquals(result.getId(), "CDF3");
	}
	
	@Test
	public void test4_updateOneBusinessObjectField() throws Exception{
		String url =
				ControllerPathConstant.BUSINESS_OBJECT_DEFAULT
					+ "/ROPA"
					+ ControllerPathConstant.BUSINESS_OBJECT_FIELD
					+ "/CDF3";
		
		BusinessObjectFieldDTO bofDTO = new BusinessObjectFieldDTO();
		BusinessObjectFieldTextDTO boftDTO = new BusinessObjectFieldTextDTO();

		bofDTO.setId("CDF3");
		bofDTO.setType(BusinessObjectFieldType.STRING);
		bofDTO.setIsCustField(true);
		bofDTO.setIsMandatory(true);
		bofDTO.setIsMultiInput(false);
		bofDTO.setIsVisible(true);
		
		boftDTO.setName("Custom Field 3");
		bofDTO.setBusinessObjectFieldText(boftDTO);

		MvcResult content_result =
				mockMvc.perform(put(url).content(TestUtil.toJson(bofDTO)).
						contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().is2xxSuccessful()).andReturn();
		String content = content_result.getResponse().getContentAsString();
		BusinessObjectField result =
				mapper.readValue(content, new TypeReference<BusinessObjectField>()
				{
				});
		
		assertEquals(result.getIsMandatory(), true);
	
	}
	
	@Test
	public void test6_deleteOneBusinessObjectField() throws Exception{
		String url =
				ControllerPathConstant.BUSINESS_OBJECT_DEFAULT
					+ "/ROPA"
					+ ControllerPathConstant.BUSINESS_OBJECT_FIELD
					+ "/CDF3";
		mockMvc.perform(delete(url)).andExpect(status().is2xxSuccessful()).andReturn();
	}
	
}
