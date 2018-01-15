package com.sap.grc.bod.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.grc.bod.constant.ControllerPathConstant;
import com.sap.grc.bod.controller.dto.BusinessObjectFieldTextDTO;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.service.BusinessObjectFieldTextService;

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectFieldTextController
{
	@Autowired
	private BusinessObjectFieldTextService boftService;
	
	/*
	 * scenario description:
	 * add a new business object field text in one language
	 */
	@PostMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_TEXT + "/{languageId}")
	public ResponseEntity<BusinessObjectFieldText> createBusinessObjectFieldText(@PathVariable String businessObjectId, @PathVariable String fieldId,
		@PathVariable String languageId, @RequestBody BusinessObjectFieldTextDTO businessObjectFieldTextDTO){
		BusinessObjectFieldText businessObjectFieldText = boftService.createBusinessObjectFieldText(fieldId, businessObjectFieldTextDTO);
		return new ResponseEntity<>(businessObjectFieldText, HttpStatus.CREATED);
	}
	
	/*
	 * scenario description:
	 * update one/many business object field text of one business object field
	 */
	@PutMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_TEXT + "/{fieldTextIdList}" )
	public ResponseEntity<List<BusinessObjectFieldText>> updateMultiBusinessObjectFieldText(@PathVariable String businessObjectId, @PathVariable String fieldId,
		@PathVariable List<String> fieldTextIdList, @RequestBody List<BusinessObjectFieldTextDTO> businessObjectFieldTextDTOList){
		List<BusinessObjectFieldText> businessObjectFieldTextList = boftService.updateMultiBusinessObjectFieldText(fieldTextIdList, businessObjectFieldTextDTOList);
		return new ResponseEntity<>(businessObjectFieldTextList, HttpStatus.OK);
	}
	
	/*
	 * scenario description:
	 * get business object field text of one business object field in one language
	 */
	@GetMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}"
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_TEXT + "/{languageId}")
	public ResponseEntity<BusinessObjectFieldText> findOneBusinessObjectFieldText(@PathVariable String businessObjectId, @PathVariable String fieldId, @PathVariable String languageId){
		BusinessObjectFieldText businessObjectFieldText = boftService.findOneBusinessObjectFieldText(fieldId, languageId);
		return new ResponseEntity<>(businessObjectFieldText, HttpStatus.OK);
	}
}
