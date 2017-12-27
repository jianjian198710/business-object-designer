package com.sap.grc.bod.model;

import java.io.Serializable;

public class BusinessObjectFieldTextId implements Serializable{

	private static final long serialVersionUID = 6643452097963231103L;

	private String businessObjectId;
	
	private String fieldId;

	private String languageId;
	
	public BusinessObjectFieldTextId() {
		
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

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessObjectId == null) ? 0 : businessObjectId.hashCode());
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
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
		BusinessObjectFieldTextId other = (BusinessObjectFieldTextId) obj;
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
		if (languageId == null) {
			if (other.languageId != null)
				return false;
		} else if (!languageId.equals(other.languageId))
			return false;		
		
		return true;
	}
	
}
