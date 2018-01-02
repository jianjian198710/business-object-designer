package com.sap.grc.bod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sap.grc.bod.model.id.BusinessObjectFieldValueSetId;

import lombok.Data;

@Entity
@Table( name = "business_object_field_value_set" )
@EntityListeners( AuditingEntityListener.class )
@IdClass( BusinessObjectFieldValueSetId.class )
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectFieldValueSet implements Serializable
{
	private static final long serialVersionUID = -5276887908139500489L;

	@Id
	@Column( name = "bo_id")
	private String businessObjectId;

	@Id
	@Column( name = "field_id")
	private String fieldId;

	@Id
	@Column( name = "lang_id" )
	private String languageId;
	
	@Id
	@Column( name = "value" )
	private String value;
	
	@Column( name = "description" )
	private String description;
}
