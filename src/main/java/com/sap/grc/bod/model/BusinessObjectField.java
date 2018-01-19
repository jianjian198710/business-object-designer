package com.sap.grc.bod.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sap.grc.bod.model.enumtype.BusinessObjectFieldType;

import lombok.Data;

@Entity
@Table( name = "business_object_field",uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "field_name", "bo_id"}))
@EntityListeners( AuditingEntityListener.class )
@UuidGenerator( name = "uuid2" )
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectField implements Serializable
{
	private static final long serialVersionUID = 6455653080086273259L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column( name = "field_id")
	private String uuid;
		
	@NotBlank
	@Column( name ="field_name",nullable=false)
	private String name;

	@Column( name = "field_type" )
	@Enumerated(EnumType.STRING)
	private BusinessObjectFieldType type;

	@Column( name = "is_cust_field" )
	private Boolean isCustField;

	@Column( name = "is_multi_input" )
	private Boolean isMultiInput;

	@Column( name = "is_value_set" )
	private Boolean isValueSet;

	@Column( name = "created_date", nullable = false )
	@CreatedDate
	private Date createdAt;

    @Column( name = "creator_uid" )
    private String creatorBy;

	@Column( name = "last_modified_date", nullable = false )
	@LastModifiedDate
	private Date changedAt;

	@Column( name = "last_modified_uid", nullable = false )
	@LastModifiedBy
	private String changedBy;

	@OneToMany( cascade = CascadeType.ALL , mappedBy="businessObjectField")
	private List<BusinessObjectFieldText> businessObjectFieldTextList;

	@ManyToOne( optional = false )
	@JoinColumn( name = "bo_id", referencedColumnName = "bo_id", insertable = true, updatable = false )
	private BusinessObject businessObject;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy="bussinessObjectField" )
	private List<BusinessObjectFieldOption> businessObjectFieldOptionList;
}
