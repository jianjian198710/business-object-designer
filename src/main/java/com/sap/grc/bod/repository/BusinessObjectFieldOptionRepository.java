package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionRepository extends JpaRepository<BusinessObjectFieldOption, String>{
	public List<BusinessObjectFieldOption> findByFieldIdAndLanguageId(String fieldId, String languageId);

	@Query("select bofo from BusinessObjectFieldOption as bofo where bofo.fieldOptionId in ?1")
	public List<BusinessObjectFieldOption> findByFieldOptionIdIn(List<String> fieldOptionIdList);
}