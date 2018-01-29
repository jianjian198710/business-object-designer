package com.sap.grc.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObjectFieldText;

public interface BusinessObjectFieldTextRepository extends JpaRepository<BusinessObjectFieldText, String>
{
	public BusinessObjectFieldText findByBusinessObjectField_UuidAndLanguageId(String fieldId, String languageId);
	public BusinessObjectFieldText findByBusinessObjectField_NameAndLanguageId(String fieldName, String languageId);
	
	//@Query("select boft from BusinessObjectFieldText as boft where boft.uuid in ?1")
	//public List<BusinessObjectFieldText> findByUuidIn(List<String> fieldTextIdList);
}