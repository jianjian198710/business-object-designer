package com.sap.grc.bod.util;

import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;

public class EmbeddedAMQPBroker
{
	private static final String INITIAL_CONFIG_PATH = "/initial-config.json";
	public static final String BROKER_PORT = "4321";
	
	private final Broker broker = new Broker();
	
	public void startBroker() throws Exception{
		BrokerOptions brokerOptions = new BrokerOptions();
		brokerOptions.setConfigProperty("qpid.amqp_port", BROKER_PORT);
		brokerOptions.setConfigProperty("qpid.broker.defaultPreferenceStoreAttributes", "{\"type\": \"Noop\"}");
		brokerOptions.setConfigProperty("qpid.vhost","default");
		brokerOptions.setConfigurationStoreType("Memory");
		brokerOptions.setInitialConfigurationLocation(getClass().getResource(INITIAL_CONFIG_PATH).toString());
		broker.startup(brokerOptions);
	}
	
	public void stopBroker(){
		broker.shutdown();
	}
}
