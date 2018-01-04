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

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectFieldController {
	
	@Autowired
	private BusinessObjectFieldService bofService;
	
	@Autowired
	private AuthEngine authEngine;

	/*
	 * scenario description:
	 * create one business object field, not including field text and field
	 */
	@PostMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD )
	public ResponseEntity<BusinessObjectField> addBusinessObjecField(@PathVariable String businessObjectId, @RequestBody BusinessObjectFieldDTO businessObjectFieldDTO){
		BusinessObjectField businessObjectField = bofService.createBusinessObjecField(businessObjectId, businessObjectFieldDTO, authEngine.getCurrentUserBean());
		return new ResponseEntity<>(businessObjectField, HttpStatus.CREATED);
	}
	
	/*
	 * scenario description:
	 * update one business object field, not including field text and field
	 */
//	@PutMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
//	public ResponseEntity<BusinessObjectField> updateOneBusinessObjectField(@PathVariable String businessObjectId, @PathVariable String fieldId, 
//		@RequestBody BusinessObjectFieldDTO businessObjectFieldDTO){
//		BusinessObjectField businessObjectField = bofService.updateOneBusinessObjectField(businessObjectId, fieldId, businessObjectFieldDTO);
//		return new ResponseEntity<>(businessObjectField, HttpStatus.OK);
//	}
	
	/*
	 * scenario description:
	 * update multiple business object field, not including field text and field
	 */
	@PutMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldIdList}")
	public ResponseEntity<List<BusinessObjectField>> updateMultiBusinessObjectField(@PathVariable String businessObjectId, @PathVariable List<String> fieldIdList,
		@RequestBody List<BusinessObjectFieldDTO> businessObjectFieldDTOList){
		List<BusinessObjectField> businessObjectFieldList = bofService.updateMultiBusinessObjectField(businessObjectId, fieldIdList, businessObjectFieldDTOList);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}
	
	/*
	 * scenario description:
	 * get one business object field 
	 */
	@GetMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD + "/{fieldId}")
	public ResponseEntity<BusinessObjectField> findOneBusinessObjectField(@PathVariable String businessObjectId, @PathVariable String fieldId){
		BusinessObjectField businessObjectField = bofService.findOneBusinessObjectField(businessObjectId, fieldId);
		return new ResponseEntity<>(businessObjectField, HttpStatus.OK);
	}
	
	/*
	 * scenario description
	 * get all business object fields of a specific business object
	 */
	@GetMapping(value = "/{businessObjectId}" + ControllerPathConstant.BUSINESS_OBJECT_FIELD)
	public ResponseEntity<List<BusinessObjectField>> findAllBusinessObjectField(@PathVariable String businessObjectId){
		List<BusinessObjectField> businessObjectFieldList = bofService.findAllBusinessObjectField(businessObjectId);
		return new ResponseEntity<>(businessObjectFieldList, HttpStatus.OK);
	}
	
}
