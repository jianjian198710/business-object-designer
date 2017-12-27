package com.sap.grc.bod.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BUSINESS_OBJECT")
@EntityListeners(AuditingEntityListener.class)
public class BusinessObject implements Serializable{
    
	private static final long serialVersionUID = -3844686443222163797L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column( name = "BO_ID", unique = true, nullable = false )
	private String businessObjectId;
	
	@Column( name = "BO_NAME", nullable = false )
	private String businessObjectName;
    
    @Column( name = "CREA_DATE_TIME", nullable = false )
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date createdAt;

    @Column( name = "CREA_UNAME", nullable = false )
	@CreatedBy
    private String createdBy;
  
    @Column( name = "LCHG_DATE_TIME", nullable = false)
    @LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date changedAt;
    
    @Column( name = "LCHG_UNAME", nullable = false)
    @LastModifiedBy
    private String changedBy;
    
	public BusinessObject(){
		
	};
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getBusinessObjectId() {
		return businessObjectId;
	}

	public void setBusinessObjectId(String businessObjectId) {
		this.businessObjectId = businessObjectId;
	}

	public String getBusinessObjectName() {
		return businessObjectName;
	}

	public void setBusinessObjectName(String businessObjectName) {
		this.businessObjectName = businessObjectName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	
	
}
