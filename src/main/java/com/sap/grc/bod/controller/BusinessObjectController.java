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
import com.sap.grc.bod.repository.BusinessObjectRepository;


@RestController
@RequestMapping(path = BusinessObjectFieldController.PATH)
@RequestScope
public class BusinessObjectController {

	 public static final String PATH = "/BusinessObject";
	 private BusinessObjectRepository boRepository;
	    
	 @Autowired
	 public BusinessObjectController(BusinessObjectRepository boRepository){
		 this.boRepository = boRepository;
	 }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BusinessObject> addBusinessObject(@Valid @RequestBody BusinessObject businessObject,
            UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        
    	//TODO:Validiton
    	//throwIfIdNotNull(businessObject.getId()); 
      
    	BusinessObject insertBusinessObject = boRepository.save(businessObject);
        
        //TODO:Log
    	//TODO:Exception Handle
        UriComponents uriComponents = uriComponentsBuilder.path(PATH + "/{id}")
                .buildAndExpand(insertBusinessObject.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(uriComponents.getPath()));   
        return new ResponseEntity<>(insertBusinessObject, headers, HttpStatus.CREATED);
        
    }
    	    
    @RequestMapping( value = "", method = RequestMethod.GET ) 
    @ResponseBody
    public List<BusinessObject> businessObjectfindAll(){
    	
    	return boRepository.findAll();
    }

}
