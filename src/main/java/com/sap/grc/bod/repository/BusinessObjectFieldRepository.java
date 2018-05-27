package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectField;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField, String>{
	
	public static final String FIND_ALL_FETCH = "select bof from BusinessObjectField bof join fetch bof.businessObjectFieldTextList boft "+
		"join fetch bof.businessObjectFieldOptionList bofo " +
		"join fetch bofo.businessObjectFieldOptionTextList bofot " +
		"where boft.languageId = ?1 " +
		"AND bofot.languageId = ?1";
	
	public static final String FIND_ONE_FETCH = FIND_ALL_FETCH + " " + 
		"AND bof.id = ?2";
	
	public BusinessObjectField findByBusinessObject_NameAndId(String boName, String fieldId);
	public List<BusinessObjectField> findByBusinessObject_Name(String boName);
	
	@Query(value=FIND_ALL_FETCH)
	public List<BusinessObjectField> findAllWithFetch(String languageId);
	
	@Query(value=FIND_ONE_FETCH)
	public BusinessObjectField findOneWithFetch(String languageId, String fieldId);
	
//	@Query(value=FIND_ALL_FETCH)
//	public List<BusinessObjectField> findAllWithFetch();
}
