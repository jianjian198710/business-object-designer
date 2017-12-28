package com.sap.grc.bod.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table( name = "business_object_field" )
@EntityListeners( AuditingEntityListener.class )
@IdClass( BusinessObjectFieldId.class )
public class BusinessObjectField
{
	@Id
	@Column( name = "bo_id", nullable = false )
	private String businessObjectId;

	@Id
	@Column( name = "field_id", nullable = false )
	private String fieldId;

	@Column( name = "field_type", nullable = false )
	private String fieldType;

	@Column( name = "is_cust_field" )
	private boolean isCustField;

	@Column( name = "is_multi_input" )
	private boolean isMultiInput;

	@Column( name = "is_value_set" )
	private boolean isValueSet;

	@Column( name = "created_date", nullable = false )
	@CreatedDate
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ" )
	private Date createdAt;

	@CreatedBy
	private CreatedUser createdBy;

	@Column( name = "last_modified_date", nullable = false )
	@LastModifiedDate
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ" )
	private Date changedAt;

	@LastModifiedBy
	private LastModifiedUser changedBy;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "bussinessObjectField" )
	@JsonManagedReference
	private List<BusinessObjectFieldText> businessObjectFieldText = new ArrayList<>();

	@ManyToOne( optional = false )
	@JoinColumn( name = "bo_id", referencedColumnName = "bo_id", insertable = false, updatable = false )
	private BusinessObject businessObject;
}
