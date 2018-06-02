package com.sap.grc.bod.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.grc.bod.constant.JpaConstant;

import lombok.Data;

@Entity
@Table(name = JpaConstant.BO_FIELD_TEXT_TABLE_NAME,uniqueConstraints = @UniqueConstraint(columnNames = {"field_id","lang_id"}))
@UuidGenerator( name = "uuid2" )
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectFieldText implements Serializable{
    
	private static final long serialVersionUID = -6588685401064628459L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column( name = "field_text_id" )
	private String uuid;
 
	@NotBlank
	@Column( name = "lang_id")	
	private String languageId;
	
	@Column( name = "field_name")
	private String name;

	@Column( name = "field_description" )
	private String description;

	@ManyToOne
	@JoinColumn( name = "field_id", referencedColumnName = "field_id",updatable = false, insertable = false )
	@JsonIgnoreProperties(value={"businessObjectFieldTextList","businessObjectFieldOptionList"})
	private BusinessObjectField businessObjectField;
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "-" + this.getName();
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.getBusinessObjectField().getId(),this.getLanguageId());
	}
	
	@Override
	public boolean equals(Object obj){
		if( obj == null ){
			return false;
		}
		if( !BusinessObjectFieldText.class.isAssignableFrom(obj.getClass())){
			return false;
		}
		final BusinessObjectFieldText other = (BusinessObjectFieldText)obj;
		if(( this.getLanguageId() == null )?(other.getLanguageId()!=null):!this.getLanguageId().equals(other.getLanguageId())){
			return false;
		}
		if(!this.getBusinessObjectField().getId().equals(other.getBusinessObjectField().getId())){
			return false;
		}
		return true;
	}

}
