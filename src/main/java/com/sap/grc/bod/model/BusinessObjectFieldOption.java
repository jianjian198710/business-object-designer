package com.sap.grc.bod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table( name = "business_object_field_option",uniqueConstraints = @UniqueConstraint(columnNames = {"field_id","value","lang_id"}))
@EntityListeners( AuditingEntityListener.class )
@UuidGenerator( name = "uuid2" )
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectFieldOption implements Serializable
{
	private static final long serialVersionUID = -5276887908139500489L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column( name = "field_option_id" )
	private String uuid;
	
	@Column( name = "field_id", nullable = false )
	private String fieldId;

	@Column( name = "lang_id", nullable = false )
	private String languageId;
	
	@Column( name = "value", nullable = false )
	private String value;
	
	@Column( name = "description" )
	private String description;
	
	@ManyToOne
    @JoinColumn( name = "field_id", referencedColumnName = "field_id", insertable = false, updatable = false )
	@JsonBackReference
	private BusinessObjectField bussinessObjectField;
	
	@Override
	public String toString() {
	    return this.getClass().getSimpleName() + "-" + this.getValue() + this.getDescription();
	}
}
