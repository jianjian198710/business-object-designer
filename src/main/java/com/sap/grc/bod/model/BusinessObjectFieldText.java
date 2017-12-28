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

import lombok.Data;

@Entity
@Table(name = "business_object_field_text")
@IdClass(BusinessObjectFieldTextId.class)
public @Data class BusinessObjectFieldText implements Serializable{
    
	private static final long serialVersionUID = -6588685401064628459L;

	@Id
	@Column( name = "bo_id", nullable = false)
	private String businessObjectId;
	
	@Id	
	@Column( name = "field_id", nullable = false )	
	private String fieldId;
 
	//TODO where come from?
	@Id
	@Column( name = "lang_id", nullable = false )	
	private String languageId;
	
    @Column( name = "field_name")
    private String fieldName;
    
    @Column( name = "description" )
	private String description;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({ 
    	@JoinColumn( name = "bo_id",    referencedColumnName = "bo_id", insertable = false, updatable = false ),
    	@JoinColumn( name = "field_id", referencedColumnName = "field_id", insertable = false, updatable = false )
    })
    @JsonBackReference
    private BusinessObjectField bussinessObjectField;
}
