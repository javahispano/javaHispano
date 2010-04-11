package org.javahispano.portal.service.impl;

import java.util.Date;

import org.javahispano.portal.data.AccountDao;
import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for all account-related operations
 * 
 * @author Sergi Almar i Graupera 
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	
	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	    
	@Transactional(propagation=Propagation.SUPPORTS)
    public Account getCurrentUser() {
    	
    	// Check we have security context 
    	if (SecurityContextHolder.getContext() != null &&
                SecurityContextHolder.getContext() instanceof SecurityContext) {
    		
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            if (auth != null && auth.getPrincipal() instanceof UserDetails) {
                Account user = (Account) auth.getPrincipal();
                return user;
            }    
        }
        
        // when not found...
        return null;
    }
	
    @Transactional(readOnly=true)
	public Account getAccountByUsername(String username) {
		return accountDao.getByUsername(username);
	}

	public void updateLastLogin(Account account, Date lastLogin) {
		accountDao.updateLastLogin(account.getId(), lastLogin);
	}
    
	public void saveAccount(Account account) {
		accountDao.save(account);	
	}

}
