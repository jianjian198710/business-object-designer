package com.sap.grc.bod.config;

import static org.eclipse.persistence.config.PersistenceUnitProperties.ALLOW_NATIVE_SQL_QUERIES;
import static org.eclipse.persistence.config.PersistenceUnitProperties.CACHE_SHARED_DEFAULT;
import static org.eclipse.persistence.config.PersistenceUnitProperties.WEAVING;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.EntityManagerProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.jta.JtaTransactionManager;

@Profile( { "unit_test" } )
@Configuration
public class JpaConfiguration_UT extends JpaConfiguration
{
	public static final String TEST_FAKE_TENANTID = "gdpr_test_tenant";
	
	protected JpaConfiguration_UT(
		DataSource dataSource,
		JpaProperties properties,
		ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
		ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers)
	{
		super(dataSource, properties, jtaTransactionManagerProvider,transactionManagerCustomizers);
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

		vendorProperties.put(EntityManagerProperties.MULTITENANT_PROPERTY_DEFAULT, TEST_FAKE_TENANTID);

		return vendorProperties;
	}
}
