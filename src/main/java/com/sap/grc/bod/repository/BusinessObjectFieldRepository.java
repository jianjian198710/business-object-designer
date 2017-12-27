package com.sap.grc.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldId;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField,BusinessObjectFieldId>{

}
