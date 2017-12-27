package com.sap.grc.bod.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.grc.bod.model.BusinessObject;

public interface BusinessObjectRepository extends JpaRepository<BusinessObject, UUID> {
  
    public BusinessObject findBybusinessObjectId(String businessObjectId);
}
