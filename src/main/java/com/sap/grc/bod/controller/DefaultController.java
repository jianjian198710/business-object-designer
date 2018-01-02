package com.sap.grc.bod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.repository.BusinessObjectRepository;

@RequestMapping("/")
@RestController
public class DefaultController {
	
	@Autowired
	private BusinessObjectRepository boRepo;
	
	
    @GetMapping("/create")
    public BusinessObject getAll() {
    	boRepo.deleteAll();
    	BusinessObject bo = new BusinessObject();
    	bo.setBusinessObjectName("ROPA");
    	return boRepo.save(bo);
    }
}

