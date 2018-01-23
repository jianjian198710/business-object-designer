package com.sap.grc.bod.mq;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.grc.bod.constant.MessageConstant;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.service.BusinessObjectFieldService;

@Component
public class MessageProducer
{
	@Autowired
	BusinessObjectFieldService bofService;
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send(String boName){
		List<BusinessObjectField> businessObjectFieldList = bofService.findAllBusinessObjectField(boName);
		rabbitTemplate.convertAndSend(MessageConstant.EXCHANGE_NAME, null, businessObjectFieldList);
	}
}
