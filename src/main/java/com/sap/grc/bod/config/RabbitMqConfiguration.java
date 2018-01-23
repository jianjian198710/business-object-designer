package com.sap.grc.bod.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.grc.bod.constant.MessageConstant;

@Configuration
public class RabbitMqConfiguration
{
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
}
