package com.sap.grc.bod.contract.ropa;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sap.grc.bod.controller.BusinessObjectFieldController;
import com.sap.grc.bod.controller.BusinessObjectFieldOptionController;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;
import com.sap.grc.bod.service.BusinessObjectFieldOptionService;
import com.sap.grc.bod.service.BusinessObjectFieldService;

@RunWith( MockitoJUnitRunner.class )
public abstract class RopaBase
{

    @Autowired
    BusinessObjectFieldController bodFieldRestController;
    @Autowired
    BusinessObjectFieldOptionController bodFieldOptionController;

    @MockBean
    BusinessObjectFieldService bodFieldService;
    @MockBean
    BusinessObjectFieldOptionService bodFieldOptionService;

    @Before
    public void setup()
    {
        RestAssuredMockMvc.standaloneSetup(bodFieldRestController);
        RestAssuredMockMvc.standaloneSetup(bodFieldOptionController);

        List<BusinessObjectField> customFields = new ArrayList<>();
        BusinessObjectField customField = new BusinessObjectField();
        customField.setUuid("c479d16b-0b0f-4c4f-8af8-ed403b89afc3");
        customField.setCreatedAt(new Date());
        customField.setCreatorBy("Jane Huang");
        customField.setChangedAt(new Date());
        customField.setChangedBy("Jane Huang");
        customField.setName("Custom1");
        customField.setType(BusinessObjectFieldType.STRING);
        customField.setIsCustField(true);
        customField.setIsValueSet(true);
        customField.setIsMandatory(false);
        customField.setIsMultiInput(false);
        customField.setIsVisible(true);
        List<BusinessObjectFieldText> customFieldText = new ArrayList<>();
        BusinessObjectFieldText text = new BusinessObjectFieldText();
        text.setLanguageId("en");
        text.setDescription("Custom Field 1");
        text.setFieldShortDescription("Custom Field 1");
        text.setUuid("3e61ca1b-7bdf-44a9-bb0d-1666488bd47d");
        customFieldText.add(text);
        customField.setBusinessObjectFieldTextList(customFieldText);
        customFields.add(customField);
        customField = new BusinessObjectField();
        customField = new BusinessObjectField();
        customField.setUuid("56e61124-ac3e-42b9-81d1-1775c6704867");
        customField.setCreatedAt(new Date(1516089558466L));
        customField.setCreatorBy("Jane Huang");
        customField.setChangedAt(new Date(1516089558466L));
        customField.setChangedBy("Jane Huang");
        customField.setName("Custom2");
        customField.setType(BusinessObjectFieldType.STRING);
        customField.setIsCustField(true);
        customField.setIsValueSet(true);
        customField.setIsMandatory(false);
        customField.setIsMultiInput(false);
        customField.setIsVisible(true);
        customFieldText = new ArrayList<>();
        text = new BusinessObjectFieldText();
        text.setLanguageId("en");
        text.setDescription("Custom Field 2");
        text.setFieldShortDescription("Custom Field 2");
        text.setUuid("921a7e46-8ad6-4f77-ab7b-cbd372b7aa65");
        customFieldText.add(text);
        customField.setBusinessObjectFieldTextList(customFieldText);
        customFields.add(customField);

        Mockito.when(bodFieldService.findAllBusinessObjectField("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33")).thenReturn(customFields);
        
        List<BusinessObjectFieldOption> businessObjectFieldOptionList = new ArrayList<>();
        BusinessObjectFieldOption businessObjectFieldOption = new BusinessObjectFieldOption();
        businessObjectFieldOption.setUuid("f4ca61a2-c008-4e66-b026-4174466235e5");
        businessObjectFieldOption.setValue("Value1");
        businessObjectFieldOption.setDescription("value 1 description");
        businessObjectFieldOption.setFieldId("A7F21EBC-3F4E-4767-85B6-F6C1AE15F152");
        businessObjectFieldOption.setLanguageId("en");
        businessObjectFieldOptionList.add(businessObjectFieldOption);
        businessObjectFieldOption = new BusinessObjectFieldOption();
        businessObjectFieldOption.setUuid("32e67c28-f80c-4264-afb2-8358a7602ea4");
        businessObjectFieldOption.setValue("Value2");
        businessObjectFieldOption.setDescription("value 2 description");
        businessObjectFieldOption.setFieldId("0a18a56f-1ef2-4348-a48a-3df66bd625a4");
        businessObjectFieldOption.setLanguageId("en");
        businessObjectFieldOptionList.add(businessObjectFieldOption);

        Mockito.when(bodFieldOptionService.findAllBusinessObjectFieldOption("4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33","Custom1", "en")).thenReturn(businessObjectFieldOptionList);
    }

}
