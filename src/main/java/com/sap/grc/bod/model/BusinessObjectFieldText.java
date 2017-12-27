package com.sap.grc.bod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "BUSINESS_OBJECT_FIELD_TEXT")
@IdClass(BusinessObjectFieldTextId.class)
public class BusinessObjectFieldText implements Serializable{
    
	private static final long serialVersionUID = -6588685401064628459L;

	@Id
	@Column( name = "BO_ID", nullable = false)
	private String businessObjectId;
	
	@Id	
	@Column( name = "FIELD_ID", nullable = false )	
	private String fieldId;
 
	@Id
	@Column( name = "LANG_ID", nullable = false )	
	private String languageId;
	
    @Column( name = "FIELD_NAME")
    private String fieldName;
    
    @Column( name = "DESCRIPTION" )
	private String description;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({ 
    	@JoinColumn( name = "BO_ID",    referencedColumnName = "BO_ID", insertable = false, updatable = false ),
    	@JoinColumn( name = "FIELD_ID", referencedColumnName = "FIELD_ID", insertable = false, updatable = false )
    })
    @JsonBackReference
    private BusinessObjectField bussinessObjectField;
    
	public String getBusinessObjectId() {
		return businessObjectId;
	}

	public void setBusinessObjectId(String businessObjectId) {
		this.businessObjectId = businessObjectId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BusinessObjectField getBussinessObjectField() {
		return bussinessObjectField;
	}

	public void setBussinessObjectField(BusinessObjectField bussinessObjectField) {
		this.bussinessObjectField = bussinessObjectField;
	}
    
}
