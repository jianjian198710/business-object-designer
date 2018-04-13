package com.sap.grc.bod.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sap.grc.bod.constant.JpaConstant;

import lombok.Data;

@Entity
@Table( name = JpaConstant.BO_TABLE_NAME )
@EntityListeners( AuditingEntityListener.class )
@UuidGenerator( name = "uuid2" )
public @Data class BusinessObject implements Serializable
{
	private static final long serialVersionUID = -3844686443222163797L;

	@Id
	@GeneratedValue( generator = "uuid2" )
	@GenericGenerator( name = "uuid2", strategy = "uuid2" )
	@Column( name = "bo_id" )
	private String uuid;

	@Column( name = "bo_name", nullable = false, unique = true )
	private String name;

	@Column( name = "description" )
	private String description;

	@Column( name = "created_date" )
	@CreatedDate
	private Date createdAt;

	@Column( name = "creator_uid" )
	private String creatorBy;

	@Column( name = "last_modified_date" )
	@LastModifiedDate
	private Date changedAt;

	@Column( name = "last_modified_uid", nullable = false )
	@LastModifiedBy
	private String changedBy;
}
