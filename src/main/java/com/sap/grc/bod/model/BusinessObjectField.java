package com.sap.grc.bod.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sap.grc.bod.model.id.BusinessObjectFieldId;

import lombok.Data;

@Entity
@Table( name = "business_object_field" )
@EntityListeners( AuditingEntityListener.class )
@IdClass( BusinessObjectFieldId.class )
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectField
{
	@Id
	@Column( name = "bo_id" )
	private String businessObjectId;

	@Id
	@Column( name = "field_id" )
	private String fieldId;

	@Column( name = "field_type" )
	private String fieldType;

	@Column( name = "is_cust_field" )
	private boolean isCustField;

	@Column( name = "is_multi_input" )
	private boolean isMultiInput;

	@Column( name = "is_value_set" )
	private boolean isValueSet;

	@Column( name = "created_date", nullable = false )
	@CreatedDate
	private Date createdAt;

    @Column( name = "creator_name" )
    private String creatorName;

    @Column( name = "creator_mail" )
    private String creatorMail;

	@Column( name = "last_modified_date", nullable = false )
	@LastModifiedDate
	private Date changedAt;

	@LastModifiedBy
	private LastModifiedUser changedBy;

	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumns({ 
		@JoinColumn( name = "bo_id", referencedColumnName = "bo_id" ),
		@JoinColumn( name = "field_id", referencedColumnName = "field_id" )
	})
	private List<BusinessObjectFieldText> businessObjectFieldTextList;

	@ManyToOne( optional = false )
	@JoinColumn( name = "bo_id", referencedColumnName = "bo_id", insertable = false, updatable = false )
	private BusinessObject businessObject;
	
	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumns({ 
		@JoinColumn( name = "bo_id", referencedColumnName = "bo_id" ),
		@JoinColumn( name = "field_id", referencedColumnName = "field_id" )
	})
	private List<BusinessObjectFieldValueSet> businessObjectFieldValueSetList;
}
