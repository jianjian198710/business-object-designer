package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.id.BusinessObjectFieldId;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField, String>{
	
	public List<BusinessObjectField> findByBusinessObjectId(String businessObjectId);
}
