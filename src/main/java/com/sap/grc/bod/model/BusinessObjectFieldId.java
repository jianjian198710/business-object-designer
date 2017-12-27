package com.sap.grc.bod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class BusinessObjectFieldId implements Serializable{

	private static final long serialVersionUID = -8651313825475676433L;
	
	//@Column( name = "BO_ID", nullable = false)
	private String businessObjectId;
	
	//@Column( name = "FIELD_ID",nullable = false )	
	private String fieldId;
    
	public BusinessObjectFieldId() {
		
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessObjectId == null) ? 0 : businessObjectId.hashCode());
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessObjectFieldId other = (BusinessObjectFieldId) obj;
		if (businessObjectId == null) {
			if (other.businessObjectId != null)
				return false;
		} else if (!businessObjectId.equals(other.businessObjectId))
			return false;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		return true;
	}
	
}
