package com.sap.grc.bod.config;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.sap.grc.bod.model.LastModifiedUser;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories( basePackages = { "com.sap.grc.bod.repository" } )
public class JpaConfiguration extends JpaBaseConfiguration
{
	@Autowired
    protected final JpaProperties properties;

    protected JpaConfiguration(
        DataSource dataSource,
        JpaProperties properties,
        ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
        ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers)
    {
        super(dataSource, properties, jtaTransactionManagerProvider,transactionManagerCustomizers);
        this.properties = properties;
    }

	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter()
	{
		return new EclipseLinkJpaVendorAdapter();
	}

	@Override
	protected Map<String, Object> getVendorProperties()
	{
        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.putAll(properties.getProperties());
        vendorProperties.put(WEAVING, "static");
        vendorProperties.put(CACHE_SHARED_DEFAULT, "false");
        vendorProperties.put(ALLOW_NATIVE_SQL_QUERIES, "true");
        
        vendorProperties.put("eclipselink.logging.level.sql", "FINER");
        vendorProperties.put("eclipselink.jdbc.cache-statements", "true");
        vendorProperties.put("eclipselink.logging.parameters", "true");

        vendorProperties.put(SESSION_CUSTOMIZER, "com.sap.grc.bod.config.DefaultSessionCustomizer");

        return vendorProperties;
	}
	
    @Bean
    AuditorAware<LastModifiedUser> auditorProvider()
    {
        return new LastModifiedUserAuditorAware();
    }
}
