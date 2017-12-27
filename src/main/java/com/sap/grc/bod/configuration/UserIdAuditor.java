package com.sap.grc.bod.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class UserIdAuditor implements AuditorAware<String> {
  
	@Override
	public String getCurrentAuditor () {
		
		//TODO Security
		return "GEA";
		
	}
	
	
}
