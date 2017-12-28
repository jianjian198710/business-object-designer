package com.sap.grc.bod.config;

import org.springframework.data.domain.AuditorAware;

import com.sap.grc.bod.model.CreatedUser;

public class CreatedUserAuditorAware implements AuditorAware<CreatedUser>
{

    @Override
    public CreatedUser getCurrentAuditor()
    {
        // TODO Auto-generated method stub
    	CreatedUser user = new CreatedUser();
    	user.setName("gdpr_admin");
    	user.setMail("gdpr.admin@sap.com");
        return user;
    }
}
