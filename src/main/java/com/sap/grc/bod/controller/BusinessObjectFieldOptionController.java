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
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "bofoDTOList", value = "Business Object Field Option DTO List", required = true, dataType = "List<BusinessObjectFieldOptionDTO>")
	})
	@PostMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<List<BusinessObjectFieldOption>> createMultiBusinessObjectFieldOption(@PathVariable String boName, @PathVariable String fieldId, 
		@RequestBody List<BusinessObjectFieldOptionDTO> bofoDTOList){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.createMultiBusinessObjectFieldOption(boName, fieldId, languageId, bofoDTOList); 
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.CREATED);
	}
	
	@ApiOperation( value = "update business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "bofoDTOList", value = "Business Object Field Option DTO List", required = true, dataType = "List<BusinessObjectFieldOptionDTO>")
	})
	@PutMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<List<BusinessObjectFieldOption>> updateMultiBusinessObjectFieldOption(@PathVariable String boName, @PathVariable String fieldId,
		@RequestBody List<BusinessObjectFieldOptionDTO> bofoDTOList){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.updateMultiBusinessObjectFieldOption(boName, fieldId, languageId, bofoDTOList); 
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.OK);
	}
	
	@ApiOperation( value = "get business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String")
	})
	@GetMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<List<BusinessObjectFieldOption>> findAllBusinessObjectFieldOption(@PathVariable String boName, @PathVariable String fieldId){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		List<BusinessObjectFieldOption> businessObjectFieldOptionList = 
			bofoService.findAllBusinessObjectFieldOption(boName, fieldId, languageId);
		return new ResponseEntity<>(businessObjectFieldOptionList, HttpStatus.OK);
	}
	
	@ApiOperation( value = "delete one business object option")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldOptionIdValue", value = "Business Object Field Option Value", required = true, paramType="path", dataType = "uuid")
	})
	@DeleteMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION + "/{fieldOptionIdValue}")
	public ResponseEntity<Void> deleteOneBusinessObjectFieldOption(@PathVariable String boName, @PathVariable String fieldId, @PathVariable String fieldOptionIdValue){
		bofoService.deleteBusinessObjectFieldOption(boName, fieldId, fieldOptionIdValue);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation( value = "delete all business object options")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String")
	})
	@DeleteMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}" 
		+ ControllerPathConstant.BUSINESS_OBJECT_FIELD_OPTION )
	public ResponseEntity<Void> deleteAllBusinessObjectFieldOption(@PathVariable String boName, @PathVariable String fieldId){
		bofoService.deleteAllBusinessObjectFieldOption(boName, fieldId);
		return ResponseEntity.noContent().build();
	}
}
