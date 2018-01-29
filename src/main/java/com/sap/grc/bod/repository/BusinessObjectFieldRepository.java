package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObjectField;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField, String>{
	
	public List<BusinessObjectField> findByBusinessObject_Uuid(String businessObjectId);
	public BusinessObjectField findByBusinessObject_UuidAndUuid(String businessObjectId, String FieldId);
	
	public BusinessObjectField findByBusinessObject_NameAndName(String businessObjectName, String fieldName);
	public List<BusinessObjectField> findByBusinessObject_Name(String businessObjectName);
	public BusinessObjectField findByName(String businessObjectName);
	
}
