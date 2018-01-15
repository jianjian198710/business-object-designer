package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectField;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField, String>{
	
	public List<BusinessObjectField> findByBusinessObject_Uuid(String businessObjectId);
	
	@Query("select bof from BusinessObjectField as bof where bof.uuid in ?1")
	public List<BusinessObjectField> findByUuidIn(List<String> fieldIdList);
}
