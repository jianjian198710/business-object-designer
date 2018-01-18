package com.sap.grc.bod.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudDataBaseConfiguration extends AbstractCloudConfig
{
	@Bean
	public DataSource dataSource()
	{
		List<String> dataSourceNames =
			Arrays.asList(
				"BasicDbcpPooledDataSourceCreator",
				"TomcatJdbcPooledDataSourceCreator",
				"HikariCpPooledDataSourceCreator",
				"TomcatDbcpPooledDataSourceCreator");
		DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
		return connectionFactory().dataSource(dbConfig);
	}
}
