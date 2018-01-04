package com.sap.grc.bod.model.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BusinessObjectFieldId implements Serializable{

	private static final long serialVersionUID = -8651313825475676433L;
	
	private String businessObjectId;
	private String fieldId;
	
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
