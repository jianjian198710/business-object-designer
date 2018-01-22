package com.sap.grc.bod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectFieldOptionController
{
	@Autowired
	private BusinessObjectFieldOptionService bofoService;
	
	@ApiOperation( value = "create business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bo_name", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "field_name", value = "Business Object Field Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "businessObjectFieldOptionDTOList", value = "Business Object Field Option DTO List", required = true, dataType = "List<BusinessObjectFieldOptionDTO>")
	})
	@PostMapping(value = "/{bo_name}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{field_name}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<List<BusinessObjectFieldOption>> createMultiBusinessObjectFieldOption(@PathVariable String bo_name, @PathVariable String field_name, 
		@RequestBody List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.createMultiBusinessObjectFieldOption(bo_name, field_name, languageId, businessObjectFieldOptionDTOList); 
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.CREATED);
	}
	
	@ApiOperation( value = "update business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bo_name", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "field_name", value = "Business Object Field Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "businessObjectFieldOptionDTOList", value = "Business Object Field Option DTO List", required = true, dataType = "List<BusinessObjectFieldOptionDTO>")
	})
	@PutMapping(value = "/{bo_name}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{field_name}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<List<BusinessObjectFieldOption>> updateMultiBusinessObjectFieldOption(@PathVariable String bo_name, @PathVariable String field_name,
		@RequestBody List<BusinessObjectFieldOptionDTO> businessObjectFieldOptionDTOList){
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.updateMultiBusinessObjectFieldOption(bo_name, field_name, businessObjectFieldOptionDTOList); 
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.OK);
	}
	
	@ApiOperation( value = "update business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bo_name", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "field_name", value = "Business Object Field Name", required = true, paramType="path", dataType = "String")
	})
	@GetMapping(value = "/{bo_name}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{field_name}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<List<BusinessObjectFieldOption>> findAllBusinessObjectFieldOption(@PathVariable String bo_name, @PathVariable String field_name){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.findAllBusinessObjectFieldOption(bo_name, field_name, languageId);
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.OK);
	}
	
	@ApiOperation( value = "delete one business object option")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bo_name", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "field_name", value = "Business Object Field Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldOptionId", value = "Business Object Field Option Id", required = true, paramType="path", dataType = "uuid")
	})
	@DeleteMapping(value = "/{bo_name}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{field_name}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION + "/{field_option_value}")
	public ResponseEntity<Void> deleteOneBusinessObjectFieldOption(@PathVariable String bo_name, @PathVariable String field_name, @PathVariable String field_option_value){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		bofoService.deleteBusinessObjectFieldOption(bo_name, field_name, field_option_value, languageId);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation( value = "delete all business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bo_name", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "field_name", value = "Business Object Field Name", required = true, paramType="path", dataType = "String")
	})
	@DeleteMapping(value = "/{bo_name}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{field_name}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<Void> deleteAllBusinessObjectFieldOption(@PathVariable String bo_name, @PathVariable String field_name){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		bofoService.deleteAllBusinessObjectFieldOption(bo_name, field_name, languageId);
		return ResponseEntity.noContent().build();
	}
}
