package com.sap.grc.bod.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "business_object")
@EntityListeners(AuditingEntityListener.class)
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObject implements Serializable{
    
	private static final long serialVersionUID = -3844686443222163797L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column( name="bo_id")
	private String businessObjectId;
	
	@Column( name = "bo_name", nullable = false )
	private String businessObjectName;
    
    @Column( name = "created_date", nullable = false )
	@CreatedDate
	private Date createdAt;

    @Column( name = "creator_name" )
    private String creatorName;

    @Column( name = "creator_mail" )
    private String creatorMail;
  
    @Column( name = "last_modified_date", nullable = false)
    @LastModifiedDate
    private Date changedAt;
    
    @LastModifiedBy
    private LastModifiedUser changedBy;
}
