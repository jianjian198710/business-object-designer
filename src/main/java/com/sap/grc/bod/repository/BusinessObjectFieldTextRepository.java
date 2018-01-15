package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectFieldText;

public interface BusinessObjectFieldTextRepository extends JpaRepository<BusinessObjectFieldText, String>
{
	public BusinessObjectFieldText findByBusinessObjectField_UuidAndLanguageId(String fieldId, String languageId);
		
	@Query("select boft from BusinessObjectFieldText as boft where boft.uuid in ?1")
	public List<BusinessObjectFieldText> findByUuidIn(List<String> fieldTextIdList);
}