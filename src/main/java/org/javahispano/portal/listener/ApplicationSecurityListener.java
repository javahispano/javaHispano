package org.javahispano.portal.listener;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Listener for the authentication event. Used to update the last login
 * 
 * @author Sergi Almar i Graupera 
 */
@Component
public class ApplicationSecurityListener  implements ApplicationListener {
    private static final Log log =  LogFactory.getLog( ApplicationSecurityListener.class );
    
    @Autowired
    private AccountService accountService;
    
    public void onApplicationEvent( ApplicationEvent event ) {
 	
    	if (event instanceof AuthenticationSuccessEvent) {
    		AuthenticationSuccessEvent authenticationSuccessEvent = ( AuthenticationSuccessEvent ) event;
            
    		// Update last login in case of successful authentication
            Object principal = authenticationSuccessEvent.getAuthentication().getPrincipal();
            if (principal instanceof Account){
            	accountService.updateLastLogin((Account) principal, new Date());
            }
        }
    }
}