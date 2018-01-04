package com.sap.grc.bod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "business_object_field_text",uniqueConstraints = @UniqueConstraint(columnNames = {"field_id","lang_id"}))
//@IdClass(BusinessObjectFieldTextId.class)
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectFieldText implements Serializable{
    
	private static final long serialVersionUID = -6588685401064628459L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column( name = "field_text_id")
	private String fieldTextId;
	
	@Column( name = "field_id", nullable = false )
	private String fieldId;
 
	//TODO where come from?
	@Column( name = "lang_id")	
	private String languageId;
	
	@Column( name = "field_short_description")
	private String fieldShortDescription;

	@Column( name = "field_description" )
	private String description;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "field_id", referencedColumnName = "field_id", insertable = false, updatable = false )
	@JsonBackReference
	private BusinessObjectField bussinessObjectField;
}
