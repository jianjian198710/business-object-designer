package com.sap.grc.bod.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;


@RestController
@RequestMapping(path = BusinessObjectController.PATH)
@RequestScope
public class BusinessObjectFieldController {

	 public static final String PATH = "/BusinessObject";
	 private BusinessObjectRepository boRepository;
	 private BusinessObjectFieldRepository boFieldRepository;
	    
	 @Autowired
	 public BusinessObjectFieldController(BusinessObjectRepository boRepository, 
			                         BusinessObjectFieldRepository boFieldRepository){
		 this.boRepository = boRepository;
	     this.boFieldRepository = boFieldRepository; 
	 }
	    
    @RequestMapping(value = "/BusinessObjectFields", method = RequestMethod.POST)
    public ResponseEntity<BusinessObjectField> add(@Valid @RequestBody BusinessObjectField businessObjectField,
            UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        
    	//TODO:Validiton Key check/BO check
    	//throwIfIdNotNull(businessObjectField.getId()); 
       
        BusinessObjectField insertBusinessObjectField = boFieldRepository.save(businessObjectField);
        
        //TODO:Log
        UriComponents uriComponents = uriComponentsBuilder.path(PATH + "/{id}")
                .buildAndExpand(insertBusinessObjectField.getFieldId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(uriComponents.getPath()));   
        return new ResponseEntity<>(insertBusinessObjectField, headers, HttpStatus.CREATED);
        
    }
    
    @RequestMapping( value = "/BusinessObjectFields", method = RequestMethod.GET ) 
    @ResponseBody
    public List<BusinessObjectField> businessObjectFieldfindAll(){
    	
    	return boFieldRepository.findAll();

    }


}
