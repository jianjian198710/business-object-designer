package com.sap.grc.bod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.repository.BusinessObjectRepository;
import com.sap.grc.bod.service.BusinessObjectService;


@Service
public class BusinessObjectServiceImpl implements BusinessObjectService
{
	@Autowired
	private BusinessObjectRepository boRepo;
	
	public List<BusinessObject> getAllBusinessObject(){
		return boRepo.findAll();
	}
}
