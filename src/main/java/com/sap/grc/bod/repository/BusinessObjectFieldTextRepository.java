package com.sap.grc.bod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObjectFieldText;

public interface BusinessObjectFieldTextRepository extends JpaRepository<BusinessObjectFieldText, String>
{
	public BusinessObjectFieldText findByFieldIdAndLanguageId(String fieldId, String languageId);
}
