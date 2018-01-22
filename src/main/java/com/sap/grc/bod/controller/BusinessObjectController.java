package com.sap.grc.bod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.grc.bod.constant.ControllerPathConstant;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.service.BusinessObjectService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( value = ControllerPathConstant.BUSINESS_OBJECT_DEFAULT )
public class BusinessObjectController
{
	
	@Autowired
	private BusinessObjectService boService;
	
	@ApiOperation( value = "get all business objects")
	@GetMapping
	public  ResponseEntity<List<BusinessObject>> findAllbusinessObject(){
		List<BusinessObject> businessObjectList = boService.getAllBusinessObject();
		return new ResponseEntity<>(businessObjectList,HttpStatus.OK);
	}
}
