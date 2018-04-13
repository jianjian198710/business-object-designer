package com.sap.grc.bod.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.grc.bod.mq.MessageProducer;

@Aspect
public class BusinessObjectFieldAspect
{
	@Autowired
	private MessageProducer messageProducer;
	
	@Pointcut("execution(* com.sap.grc.bod.service.BusinessObjectField*.create*(..)) && args(boName,..)")
	public void createBusinessObjectField(String boName){}
	
	@Pointcut("execution(* com.sap.grc.bod.service.BusinessObjectField*.update*(..)) && args(boName,..)")
	public void updateBusinessObjectField(String boName){}
	
	@Pointcut("execution(* com.sap.grc.bod.service.BusinessObjectField*.delete*(..)) && args(boName,..)")
	public void deleteBusinessObjectField(String boName){}
	
	@After("createBusinessObjectField(boName)")
	public void createMessagePublish(String boName){
		this.messagePublish(boName);
	}
	
	@After("updateBusinessObjectField(boName)")
	public void updateMessagePublish(String boName){
		this.messagePublish(boName);
	}
	
	@After("deleteBusinessObjectField(boName)")
	public void deleteMessagePublish(String boName){
		this.messagePublish(boName);
	}
	
	private void messagePublish(String boName){
//		messageProducer.send(boName);
	}
}
