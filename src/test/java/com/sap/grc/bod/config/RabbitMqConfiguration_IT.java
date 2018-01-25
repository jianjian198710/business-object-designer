package com.sap.grc.bod.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sap.grc.bod.util.EmbeddedAMQPBroker;

@Profile("integration_test")
@Configuration
public class RabbitMqConfiguration_IT
{
	@Bean
	public ConnectionFactory connectionFactory(){  
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setPort(Integer.parseInt(EmbeddedAMQPBroker.BROKER_PORT));
		connectionFactory.setVirtualHost("default");  
		return connectionFactory;  
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(){  
		RabbitTemplate template = new RabbitTemplate(connectionFactory());  
		return template;  
	}
}
