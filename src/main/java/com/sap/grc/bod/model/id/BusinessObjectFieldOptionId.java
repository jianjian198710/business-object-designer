package com.sap.grc.bod.model.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BusinessObjectFieldOptionId implements Serializable
{
	private static final long serialVersionUID = -2160258477608071817L;
	
	private String businessObjectId;
	private String fieldId;
	private String languageId;
	private String value;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessObjectId == null) ? 0 : businessObjectId.hashCode());
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		BusinessObjectFieldOptionId other = (BusinessObjectFieldOptionId) obj;
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
