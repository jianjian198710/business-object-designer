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

	@PostMapping
	public ResponseEntity<BusinessObject> addBusinessObject (@RequestBody BusinessObjectDTO businessObjectDTO){
		BusinessObject businessObject = boService.createBusinessObject(businessObjectDTO, authEngine.getCurrentUserBean());
		return new ResponseEntity<>(businessObject,HttpStatus.CREATED);
	}
	
	@PutMapping( value = "/{id}")
	public ResponseEntity<BusinessObject> updateBusinessObject(@PathVariable("id") String businessObjectId, 
		@RequestBody BusinessObjectDTO businessObjectDTO){
		BusinessObject businessObject = boService.updateBusinessObject(businessObjectId, businessObjectDTO);
		return new ResponseEntity<>(businessObject,HttpStatus.OK);
	}

	@GetMapping
	public  ResponseEntity<List<BusinessObject>> findAllbusinessObject(){
		List<BusinessObject> businessObjectList = boService.getAllBusinessObject();
		return new ResponseEntity<>(businessObjectList,HttpStatus.OK);
	}

	@GetMapping( value = "/{id}")
    public ResponseEntity<BusinessObject> findByBusinessObjectId(@PathVariable("id") String businessObjectId){	
		
		BusinessObject businessObject = boService.findBybusinessObjectId(businessObjectId);
    	return new ResponseEntity<>(businessObject, HttpStatus.OK);
    }

	@DeleteMapping( value = "{id}")
    public void deleteByBusinessObjectId(@PathVariable("id") String businessObjectId){	
    	boService.deleteBybusinessObjectId(businessObjectId);
    }
	
}
