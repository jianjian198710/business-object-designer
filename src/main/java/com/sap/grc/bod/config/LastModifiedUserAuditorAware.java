package com.sap.grc.bod.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;

import com.sap.grc.bod.model.LastModifiedUser;

public class LastModifiedUserAuditorAware implements AuditorAware<LastModifiedUser>
{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public LastModifiedUser getCurrentAuditor()
    {
        /*
         * TODO get user from security context
         */
    	LastModifiedUser user = new LastModifiedUser();
    	user.setName("gdpr_admin");
    	user.setMail("gdpr.admin@sap.com");
        return user;
    }
}
