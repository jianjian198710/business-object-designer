package com.sap.grc.bod.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionRepository extends JpaRepository<BusinessObjectFieldOption, String>{
//	public List<BusinessObjectFieldOption> findByFieldIdAndLanguageId(String fieldId, String languageId);
	public static final String FIND_ALL_FETCH = "select bofo from BusinessObjectFieldOption bofo join fetch bofo.businessObjectFieldOptionTextList boft "+
		"where bofo.value = ?1 " +
		"AND boft.languageId = ?2";
	
	public Set<BusinessObjectFieldOption> findByBusinessObjectField(BusinessObjectField bof);
	
//	public List<BusinessObjectFieldOption> findByBusinessObjectField_NameAndLanguageId(String fieldName, String languageId);	
	
	@Query("select bofo from BusinessObjectFieldOption as bofo where bofo.uuid in ?1")
	public List<BusinessObjectFieldOption> findByUuidIn(List<String> fieldOptionIdList);
	
	@Query(value=FIND_ALL_FETCH)
	public List<BusinessObjectFieldOption> findAllWithFetch(String value, String languageId);
	
	@Modifying
	@Query("delete from BusinessObjectFieldOption as bofo where bofo.businessObjectField = ?1")
	public void deleteByFieldId(BusinessObjectField bof);
	
}
