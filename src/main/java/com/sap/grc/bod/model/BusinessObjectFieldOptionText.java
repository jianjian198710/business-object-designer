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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sap.grc.bod.constant.JpaConstant;

import lombok.Data;

@Entity
@Table( name = JpaConstant.BO_FIELD_OPTION_TEXT_TABLE_NAME,uniqueConstraints = @UniqueConstraint(columnNames = {"field_option_id","lang_id"}))
@UuidGenerator( name = "uuid2" )
@Multitenant
@TenantDiscriminatorColumn( name = "tenant_id", contextProperty = "eclipselink.tenant-id", length = 36 )
public @Data class BusinessObjectFieldOptionText implements Serializable
{
	private static final long serialVersionUID = 8881565021216636869L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column( name = "field_option_text_id" )
	private String uuid;
	
	@Column( name = "lang_id" )
	private String languageId;
	
	@Column( name = "description" )
	private String description;
	
	@ManyToOne
	@JoinColumn( name = "field_option_id", referencedColumnName = "field_option_id", updatable = false, insertable = true )
	@JsonBackReference
	private BusinessObjectFieldOption businessObjectFieldOption;
	
	@Override
	public int hashCode(){
		return Objects.hash(this.getBusinessObjectFieldOption().getValue(),this.getLanguageId());
	}
	
	@Override
	public boolean equals(Object obj){
		if( obj == null ){
			return false;
		}
		if( !BusinessObjectFieldOptionText.class.isAssignableFrom(obj.getClass())){
			return false;
		}
		final BusinessObjectFieldOptionText other = (BusinessObjectFieldOptionText)obj;
		if(( this.getLanguageId() == null )?(other.getLanguageId()!=null):!this.getLanguageId().equals(other.getLanguageId())){
			return false;
		}
		if(!this.getBusinessObjectFieldOption().getValue().equals(other.getBusinessObjectFieldOption().getValue())){
			return false;
		}
		return true;
	}
}
