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
import com.sap.grc.bod.controller.dto.BusinessObjectFieldOptionDTO;
import com.sap.grc.bod.model.BusinessObjectFieldOption;
import com.sap.grc.bod.service.BusinessObjectFieldOptionService;

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectFieldOptionController
{
	@Autowired
	private BusinessObjectFieldOptionService bofoService;
	
	@PostMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION + "/{languageId}")
	public ResponseEntity<List<BusinessObjectFieldOption>> createMultiBusinessObjectFieldOption(@PathVariable String businessObjectId, @PathVariable String fieldId, 
		@PathVariable String languageId, @RequestBody List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.createMultiBusinessObjectFieldOption(fieldId, languageId, businessObjectFieldOptionDTOList); 
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION + "/{languageId}/{fieldOptionIdList}")
	public ResponseEntity<List<BusinessObjectFieldOption>> updateMultiBusinessObjectFieldOption(@PathVariable String businessObjectId, @PathVariable String fieldId, 
		@PathVariable String languageId, @PathVariable List<String> fieldOptionIdList, @RequestBody List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.updateMultiBusinessObjectFieldOption(fieldOptionIdList, businessObjectFieldOptionDTOList); 
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION + "/{languageId}")
	public ResponseEntity<List<BusinessObjectFieldOption>> findAllBusinessObjectFieldOption(@PathVariable String businessObjectId, @PathVariable String fieldId, 
		@PathVariable String languageId){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.findAllBusinessObjectFieldOption(fieldId, languageId);
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.OK);
	}
	
}
