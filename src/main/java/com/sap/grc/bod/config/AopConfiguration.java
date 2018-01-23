package com.sap.grc.bod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.sap.grc.bod.aop.BusinessObjectFieldAspect;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration
{
	@Bean
	public BusinessObjectFieldAspect businessObjectFieldAspect(){
		return new BusinessObjectFieldAspect();
	}
}
