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
import com.sap.grc.bod.controller.dto.BusinessObjectFieldDTO;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.security.AuthEngine;
import com.sap.grc.bod.service.BusinessObjectFieldService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectFieldController {
	
	@Autowired
	private BusinessObjectFieldService bofService;
	
	@Autowired
	private AuthEngine authEngine;

	
	/*
	 * scenario description:
	 * create business object field
	 */
	@ApiOperation( value = "create business object field")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "bofDTO", value = "Business Object Field DTO", required = true, dataType = "BusinessObjectFieldDTO")
	})
	@PostMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD )
	public ResponseEntity<BusinessObjectField> createBusinessObjectField(@PathVariable String boName, 
		@RequestBody BusinessObjectFieldDTO bofDTO){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		BusinessObjectField bof = bofService.createBusinessObjecField(boName, bofDTO, authEngine.getCurrentUserBean(), languageId);
		return new ResponseEntity<>(bof, HttpStatus.CREATED);
	}
	
	
	/* scenario description:
	 * update business object field
	 */
	@ApiOperation( value = "update business object field")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "bofDTO", value = "Business Object Field DTO", required = true, dataType = "BusinessObjectFieldDTO")
	})
	@PutMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
	public ResponseEntity<BusinessObjectField> updateBusinessObjectField(@PathVariable String boName, @PathVariable String fieldId, 
		@RequestBody BusinessObjectFieldDTO bofDTO){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		BusinessObjectField bof = bofService.updateBusinessObjectField(boName, fieldId, bofDTO, authEngine.getCurrentUserBean(), languageId);
		return new ResponseEntity<>(bof, HttpStatus.OK);
	}

	/*
	 * scenario description:
	 * get one business object field with specific language
	 */
	@ApiOperation( value = "get one business object field with specific language")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field ID", required = true, paramType="path", dataType = "String")
	})
	@GetMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
	public ResponseEntity<BusinessObjectField> findOneBusinessObjectField(@PathVariable String boName, @PathVariable String fieldId){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		BusinessObjectField bof= bofService.findOneBusinessObjectField(boName, fieldId, languageId);
		return new ResponseEntity<>(bof, HttpStatus.OK);
	}
	
	/*
	 * scenario description
	 * get all business object fields with a specific language
	 */
	@ApiOperation( value = "get all business object fields with a specific language")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
	})
	@GetMapping(value = "/{boName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD)
	public ResponseEntity<List<BusinessObjectField>> findAllBusinessObjectFieldWithSpecificLanguage(@PathVariable String boName){
		String languageId = LocaleContextHolder.getLocale().getLanguage();
		List<BusinessObjectField> businessObjectFieldList = bofService.findAllBusinessObjectField(boName, languageId);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}
	
	/*
	 * scenario description
	 * delete one business object field
	 */	
	@DeleteMapping(value = "/{boName}"+ ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
	public ResponseEntity<Void> deleteOneBusinessObjectField(@PathVariable String boName, @PathVariable String fieldId) {
		bofService.deleteOneBusinessObjectField(boName, fieldId);
		return ResponseEntity.noContent().build();
	}
}
