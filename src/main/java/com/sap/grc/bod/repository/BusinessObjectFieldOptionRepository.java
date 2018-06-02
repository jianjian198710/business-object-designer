package com.sap.grc.bod.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionRepository extends JpaRepository<BusinessObjectFieldOption, String>{
	public static final String FIND_ALL_FETCH = "select distinct bofo from BusinessObjectFieldOption bofo join fetch bofo.businessObjectFieldOptionTextList boft "+
		"where bofo.businessObjectField = ?1 " +
		"AND boft.languageId = ?2";
	
	public Set<BusinessObjectFieldOption> findByBusinessObjectField(BusinessObjectField bof);
	
	@Query("select bofo from BusinessObjectFieldOption as bofo where bofo.uuid in ?1")
	public List<BusinessObjectFieldOption> findByUuidIn(List<String> fieldOptionIdList);
	
	@Query(value=FIND_ALL_FETCH)
	public List<BusinessObjectFieldOption> findAllWithFetch(BusinessObjectField bof, String languageId);
	
	public void deleteByBusinessObjectField(BusinessObjectField bof);
	
}
