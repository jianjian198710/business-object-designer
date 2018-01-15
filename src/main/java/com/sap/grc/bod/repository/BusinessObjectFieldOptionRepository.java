package com.sap.grc.bod.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionRepository extends JpaRepository<BusinessObjectFieldOption, String>{
	public List<BusinessObjectFieldOption> findByUuidAndLanguageId(String fieldId, String languageId);

	@Query("select bofo from BusinessObjectFieldOption as bofo where bofo.uuid in ?1")
	public List<BusinessObjectFieldOption> findByUuidIn(List<String> fieldOptionIdList);
}
