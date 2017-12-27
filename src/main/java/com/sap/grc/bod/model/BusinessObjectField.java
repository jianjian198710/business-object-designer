package com.sap.grc.bod.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "BUSINESS_OBJECT_FIELD")
@IdClass(BusinessObjectFieldId.class)
@EntityListeners(AuditingEntityListener.class)
public class BusinessObjectField {

    //@EmbeddedId
    //private BusinessObjectFieldId Id;
    @Id
	@Column( name = "BO_ID", nullable = false)
	private String businessObjectId;
	
    @Id
	@Column( name = "FIELD_ID",nullable = false )	
	private String fieldId;
	
    @Column( name = "FIELD_TYPE", nullable = false )
	private String fieldType;
	
    @Column( name = "IS_CUSTFIELD" )
	private boolean custField;
	
    @Column( name = "IS_MULTIINPUT" )
	private boolean multiInput;
	
    // TODO: ValueSet
    @Column( name = "IS_VALUESET" )
	private boolean valueSet;
	
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

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "bussinessObjectField" )
    @JsonManagedReference
    private List<BusinessObjectFieldText> businessObjectFieldText = new ArrayList<>();
    
    @ManyToOne( optional = false )
    @JoinColumn( name = "BO_ID", referencedColumnName = "BO_ID", insertable = false, updatable = false)
    private BusinessObject businessObject;
    
    public BusinessObjectField() {
    	
    }
    
    
	/*public BusinessObjectFieldId getId() {
		return Id;
	}

	public void setId(BusinessObjectFieldId id) {
		Id = id;
	}*/

	public String getBusinessObjectId() {
		return businessObjectId;
	}


	public void setBusinessObjectId(String businessObjectId) {
		this.businessObjectId = businessObjectId;
	}


	public String getFieldId() {
		return fieldId;
	}


	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}


	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	
	public boolean isCustField() {
		return custField;
	}


	public void setCustField(boolean custField) {
		this.custField = custField;
	}


	public boolean isMultiInput() {
		return multiInput;
	}


	public void setMultiInput(boolean multiInput) {
		this.multiInput = multiInput;
	}


	public boolean isValueSet() {
		return valueSet;
	}


	public void setValueSet(boolean valueSet) {
		this.valueSet = valueSet;
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

	public List<BusinessObjectFieldText> getBusinessObjectFieldText() {
		return businessObjectFieldText;
	}

	public void setBusinessObjectFieldText(List<BusinessObjectFieldText> businessObjectFieldText) {
		this.businessObjectFieldText = businessObjectFieldText;
	}

	public BusinessObject getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(BusinessObject businessObject) {
		this.businessObject = businessObject;
	}

    
}
