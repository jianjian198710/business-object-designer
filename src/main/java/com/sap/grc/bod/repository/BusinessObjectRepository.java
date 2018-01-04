package com.sap.grc.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObject;

public interface BusinessObjectRepository extends JpaRepository<BusinessObject, String> {
	public BusinessObject findBybusinessObjectId(String businessObjectId);
	public BusinessObject findBybusinessObjectName(String businessObjectName);
}
