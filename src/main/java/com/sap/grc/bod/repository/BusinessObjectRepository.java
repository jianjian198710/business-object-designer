package com.sap.grc.bod.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObject;

public interface BusinessObjectRepository extends JpaRepository<BusinessObject, String> {
	public BusinessObject findByUuid(String businessObjectId);
	public BusinessObject findByName(String businessObjectName);
}