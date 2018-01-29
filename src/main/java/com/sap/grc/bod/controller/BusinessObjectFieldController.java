package com.sap.grc.bod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * create business object fields, including only session language field text
	 */
	@ApiOperation( value = "create business object fields")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "businessObjectFieldDTOList", value = "Business Object Field DTO List", required = true, dataType = "List<businessObjectFieldDTOList>")
	})
	@PostMapping(value = "/{businessObjectName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD )
	public ResponseEntity<List<BusinessObjectField>> createBusinessObjecFields(@PathVariable String businessObjectName, 
		@RequestBody List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
		List<BusinessObjectField> businessObjectFieldList = bofService.createBusinessObjecFields(businessObjectName, businessObjectFieldDTOList, authEngine.getCurrentUserBean());
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.CREATED);
	}
	
	
	/* scenario description:
	 * update one business object field, including only session language field text 
	 */
	@ApiOperation( value = "update one business object field")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldName", value = "Field Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "businessObjectFieldDTO", value = "Business Object Field DTO", required = true, dataType = "BusinessObjectFieldDTO")
	})
	@PutMapping(value = "/{businessObjectName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldName}")
	public ResponseEntity<BusinessObjectField> updateOneBusinessObjectField(@PathVariable String businessObjectName, @PathVariable String fieldName, 
		@RequestBody BusinessObjectFieldDTO businessObjectFieldDTO){
		BusinessObjectField businessObjectField = bofService.updateOneBusinessObjectField(businessObjectName, fieldName, businessObjectFieldDTO);
		return new ResponseEntity<>(businessObjectField, HttpStatus.OK);
	}
	

	/* scenario description:
	 * update multiple business object field, each field including only session language field text 
	 */
	@ApiOperation( value = "update business object fields")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "businessObjectFieldDTOList", value = "Business Object Field DTO List", required = true, dataType = "List<businessObjectFieldDTOList>")
	})
	@PutMapping(value = "/{businessObjectName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD)
	public ResponseEntity<List<BusinessObjectField>> updateMultiBusinessObjectField(@PathVariable String businessObjectName, 
		@RequestBody List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
		List<BusinessObjectField> businessObjectFieldList 
		        = bofService.updateMultiBusinessObjectField(businessObjectName,businessObjectFieldDTOList);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}

	/*
	 * scenario description:
	 * get one business object field 
	 */
	@ApiOperation( value = "get one business object field")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
		@ApiImplicitParam(name = "fieldName", value = "Business Object Field Name", required = true, paramType="path", dataType = "String")
	})
	@GetMapping(value = "/{businessObjectName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldName}")
	public ResponseEntity<BusinessObjectField> findOneBusinessObjectField(@PathVariable String businessObjectName, @PathVariable String fieldName){
		BusinessObjectField businessObjectField= bofService.findOneBusinessObjectField(businessObjectName, fieldName);
		return new ResponseEntity<>(businessObjectField, HttpStatus.OK);
	}
	
	/*
	 * scenario description
	 * get all business object fields of a specific business object
	 */
	@ApiOperation( value = "get all business object fields")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectName", value = "Business Object Name", required = true, paramType="path", dataType = "String"),
	})
	@GetMapping(value = "/{businessObjectName}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD)
	public ResponseEntity<List<BusinessObjectField>> findAllBusinessObjectField(@PathVariable String businessObjectName){
		List<BusinessObjectField> businessObjectFieldList = bofService.findAllBusinessObjectField(businessObjectName);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}
	
	/*
	 * scenario description
	 * delete one business object fields of a specific business object
	 */	
	@DeleteMapping(value = "/{businessObjectName}"+ ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldName}")
	public void deleteOneBusinessObjectField(@PathVariable String businessObjectName, @PathVariable String fieldName) {
		bofService.deleteOneBusinessObjectField(businessObjectName, fieldName);
	}
}
