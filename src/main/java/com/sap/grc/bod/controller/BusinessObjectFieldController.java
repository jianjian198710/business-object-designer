package com.sap.grc.bod.controller;

import java.util.List;

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
		@ApiImplicitParam(name = "businessObjectId", value = "Business Object Id", required = true, paramType="path", dataType = "uuid"),
		@ApiImplicitParam(name = "businessObjectFieldDTOList", value = "Business Object Field DTO List", required = true, dataType = "List<businessObjectFieldDTOList>")
	})
	@PostMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD )
	public ResponseEntity<List<BusinessObjectField>> addBusinessObjecFields(@PathVariable String businessObjectId, 
		@RequestBody List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
		List<BusinessObjectField> businessObjectFieldList = bofService.createBusinessObjecFields(businessObjectId, businessObjectFieldDTOList, authEngine.getCurrentUserBean());
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.CREATED);
	}
	
	
	
	/* scenario description:
	 * update one business object field, including only session language field text 
	 */
	@ApiOperation( value = "update one business object field")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectId", value = "Business Object Id", required = true, paramType="path", dataType = "uuid"),
		@ApiImplicitParam(name = "businessObjectFieldDTO", value = "Business Object Field DTO", required = true, dataType = "BusinessObjectFieldDTO")
	})
	@PutMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
	public ResponseEntity<BusinessObjectField> updateOneBusinessObjectField(@PathVariable String businessObjectId, @PathVariable String fieldId, 
		@RequestBody BusinessObjectFieldDTO businessObjectFieldDTO){
		BusinessObjectField businessObjectField = bofService.updateOneBusinessObjectField(businessObjectId, fieldId, businessObjectFieldDTO);
		return new ResponseEntity<>(businessObjectField, HttpStatus.OK);
	}
	

	/* scenario description:
	 * update multiple business object field, each field including only session language field text 
	 */
	@ApiOperation( value = "update business object fields")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectId", value = "Business Object Id", required = true, paramType="path", dataType = "uuid"),
		@ApiImplicitParam(name = "businessObjectFieldDTOList", value = "Business Object Field DTO List", required = true, dataType = "List<businessObjectFieldDTOList>")
	})
	@PutMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD)
	public ResponseEntity<List<BusinessObjectField>> updateMultiBusinessObjectField(@PathVariable String businessObjectId, 
		@RequestBody List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
		List<BusinessObjectField> businessObjectFieldList 
		        = bofService.updateMultiBusinessObjectField(businessObjectId,businessObjectFieldDTOList);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}

	/*
	 * scenario description:
	 * get one business object field 
	 */
	@ApiOperation( value = "get one business object field")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectId", value = "Business Object Id", required = true, paramType="path", dataType = "uuid"),
		@ApiImplicitParam(name = "fieldId", value = "Business Object Field Id", required = true, paramType="path", dataType = "uuid")
	})
	@GetMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
	public ResponseEntity<BusinessObjectField> findOneBusinessObjectField(@PathVariable String businessObjectId, @PathVariable String fieldId){
		BusinessObjectField businessObjectField= bofService.findOneBusinessObjectField(businessObjectId, fieldId);
		return new ResponseEntity<>(businessObjectField, HttpStatus.OK);
	}
	
	/*
	 * scenario description
	 * get all business object fields of a specific business object
	 */
	@ApiOperation( value = "get all business object fields")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "businessObjectId", value = "Business Object Id", required = true, paramType="path", dataType = "uuid"),
	})
	@GetMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD)
	public ResponseEntity<List<BusinessObjectField>> findAllBusinessObjectField(@PathVariable String businessObjectId){
		List<BusinessObjectField> businessObjectFieldList = bofService.findAllBusinessObjectField(businessObjectId);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}
	
}
