package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObjectFieldOption;

public interface BusinessObjectFieldOptionRepository extends JpaRepository<BusinessObjectFieldOption, String>{
	public List<BusinessObjectFieldOption> findByFieldIdAndLanguageId(String fieldId, String languageId);
}
