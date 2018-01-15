package com.sap.grc.bod.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldText;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField, String>{
	
	public List<BusinessObjectField> findByBusinessObject_Uuid(String businessObjectId);
	
}
