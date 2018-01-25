package com.sap.grc.bod.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.grc.bod.constant.MessageConstant;

@Configuration
public class RabbitMqConfiguration
{
	private static final String VCAP_SERVICES = "VCAP_SERVICES";
	private static final String RABBITMQ = "rabbitmq";
	private static final String CREDENTIALS = "credentials";
	private static final String HOSTNAME = "hostname";
	private static final String PORT = "port";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	
	@Bean
	Queue queue()
	{
		return new Queue(MessageConstant.QUEUE_NAME, false);
	}

	@Bean
	FanoutExchange fanoutExchange()
	{
		return new FanoutExchange(MessageConstant.EXCHANGE_NAME);
	}

	@Bean
	Binding binding( Queue queue, FanoutExchange fanoutExchange )
	{
		return BindingBuilder.bind(queue).to(fanoutExchange);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	@Profile("cloud")
	public ConnectionFactory connectionFactory() throws Exception {  
		Properties properties = this.getConnectionPropertiesFromEnv();
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(properties.getProperty(HOSTNAME));
		connectionFactory.setPort(Integer.parseInt(properties.getProperty(PORT)));
		connectionFactory.setUsername(properties.getProperty(USERNAME));  
		connectionFactory.setPassword(properties.getProperty(PASSWORD));  
		connectionFactory.setVirtualHost("/");  
		return connectionFactory;  
	}
	
	@Bean
	@Profile("cloud")
	public RabbitTemplate rabbitTemplate() throws Exception {  
		RabbitTemplate template = new RabbitTemplate(connectionFactory());  
		return template;  
	}

	@SuppressWarnings( "unchecked" )
	private Properties getConnectionPropertiesFromEnv() throws Exception{
		Properties properties = new Properties();
		String servicesString = System.getenv(VCAP_SERVICES);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, List<Map<String, Object>>> rawServices = new HashMap<String, List<Map<String, Object>>>();
		rawServices = objectMapper.readValue(servicesString, Map.class);
		if(Objects.nonNull(rawServices)){
			List<Map<String, Object>> rabbitmqService = rawServices.get(RABBITMQ);
			if(Objects.nonNull(rabbitmqService)){
				for(Map<String,Object> content: rabbitmqService){
					if(Objects.nonNull(content.get(CREDENTIALS))){
						Map<String,String> credentialsContent = objectMapper.convertValue(content.get(CREDENTIALS), Map.class);
						if(Objects.nonNull(credentialsContent)){
							properties.setProperty(HOSTNAME, credentialsContent.get(HOSTNAME));
							properties.setProperty(PORT, credentialsContent.get(PORT));
							properties.setProperty(USERNAME, credentialsContent.get(USERNAME));
							properties.setProperty(PASSWORD, credentialsContent.get(PASSWORD));
						}
					}
				}
			}
		}
		return properties;
	}
}
