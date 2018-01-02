package com.sap.grc.bod.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.grc.bod.constant.ControllerPathConstant;
import com.sap.grc.bod.controller.dto.BusinessObjectDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.security.AuthEngine;
import com.sap.grc.bod.service.BusinessObjectService;

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectController
{
	
	@Autowired
	private BusinessObjectService boService;
	
	@Autowired
	private AuthEngine authEngine;

	@PostMapping( value = "/create" )
	public ResponseEntity<BusinessObject> addBusinessObject (@Valid @RequestBody BusinessObjectDTO businessObjectDTO)
	{
		BusinessObject businessObject = boService.createBusinessObject(businessObjectDTO, authEngine.getCurrentUserBean());
		return new ResponseEntity<>(businessObject,HttpStatus.CREATED);
	}
	
	@PutMapping( value = "/update")
	public ResponseEntity<BusinessObject> updateBusinessObject(@Valid @RequestBody BusinessObjectDTO businessObjectDTO){
		BusinessObject businessObject = boService.updateBusinessObject(businessObjectDTO);
		return new ResponseEntity<>(businessObject,HttpStatus.OK);
	}

	@GetMapping( value= "/get/all")
	public  ResponseEntity<List<BusinessObject>> findAllbusinessObject()
	{
		List<BusinessObject> businessObjectList = boService.getAllBusinessObject();
		return new ResponseEntity<>(businessObjectList,HttpStatus.CREATED);
	}

//	private void throwIfConflict( UUID id, String businessObject )
//	{
//		if( boRepository.exists(id) ) {
//			ConflictException notFoundException = new ConflictException(businessObject + " ex");
//			// logger.warn("request failed", notFoundException);
//			throw notFoundException;
//		}
//	}
}
