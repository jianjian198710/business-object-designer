package com.sap.grc.bod.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sap.grc.bod.model.BusinessObjectField;

public interface BusinessObjectFieldRepository extends JpaRepository<BusinessObjectField, String>,JpaSpecificationExecutor{
	
	public BusinessObjectField findByBusinessObject_NameAndId(String boName, String fieldId);
	public List<BusinessObjectField> findByBusinessObject_Name(String boName);
	
	public static Specification<BusinessObjectField> findSpecificationOneQuery() {
		return new Specification<BusinessObjectField>(){
			@Override
			public Predicate toPredicate( Root<BusinessObjectField> root, CriteriaQuery<?> query, CriteriaBuilder builder )
			{
				root = query.from(BusinessObjectField.class);
				root.fetch("businessObjectFieldTextList",JoinType.INNER);
				root.fetch("businessObjectFieldOptionList",JoinType.INNER);
				
				
				
				return null;
			}
		};
	}
	
}
