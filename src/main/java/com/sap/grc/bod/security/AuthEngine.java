package com.sap.grc.bod.security;

import org.springframework.stereotype.Component;

import com.sap.grc.bod.model.UserBean;

@Component
public class AuthEngine
{
	//TODO get all info from security context
	public String getCurrentUserMail(){
		return "gdpr.admin@sap.com";
	}

	public String getCurrentUserName(){
		return "gdpr_admin";
	}

	public String getTenantId(){
		return "gdpr_tenant";
	}

	public UserBean getCurrentUserBean(){
		return new UserBean(this.getCurrentUserName(), this.getCurrentUserMail(), this.getTenantId());
	}
}