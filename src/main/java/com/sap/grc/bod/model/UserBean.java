package com.sap.grc.bod.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserBean{
    
	@Getter
	protected String name;
    
	@Getter
	protected String mail;
    
	@Getter
	@JsonIgnore
	protected String tenantId;

}
