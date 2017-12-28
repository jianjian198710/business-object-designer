package com.sap.grc.bod.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "buiness_object")
@EntityListeners(AuditingEntityListener.class)
public @Data class BusinessObject implements Serializable{
    
	private static final long serialVersionUID = -3844686443222163797L;

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "bo_id", updatable = false, nullable = false)
	private String businessObjectId;
	
	@Column( name = "bo_name", nullable = false )
	private String businessObjectName;
    
    @Column( name = "created_date", nullable = false )
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date createdAt;

	@CreatedBy
    private CreatedUser createdBy;
  
    @Column( name = "last_modified_date", nullable = false)
    @LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date changedAt;
    
    @LastModifiedBy
    private LastModifiedUser changedBy;
}
