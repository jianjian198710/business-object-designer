package com.sap.grc.bod.config;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;

public class DefaultSessionCustomizer implements SessionCustomizer
{
	private static final String PROPERTY_NAME_TENANT = "eclipselink.tenant-id";

	@Override
	public void customize( Session session ) throws Exception{
		session.getEventManager().addListener(new TenantSessionListener());
	}

	private class TenantSessionListener extends SessionEventAdapter
	{
		@Override
		public void postAcquireClientSession( final SessionEvent event )
		{
			event.getSession().setProperty(PROPERTY_NAME_TENANT, getTenantId());
		}

		private String getTenantId()
		{
			return "gdpr_tenant";
		}

	}
}
