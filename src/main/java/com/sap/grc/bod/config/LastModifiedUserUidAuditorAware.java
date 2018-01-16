package com.sap.grc.bod.config;

import org.springframework.data.domain.AuditorAware;

public class LastModifiedUserUidAuditorAware implements AuditorAware<String>
{
    @Override
    public String getCurrentAuditor()
    {
        /*
         * TODO get uid from security context
         */
        return "gdpr_uid";
    }
}
