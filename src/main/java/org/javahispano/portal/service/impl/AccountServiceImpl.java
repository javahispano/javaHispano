package org.javahispano.portal.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.javahispano.portal.data.AccountDao;
import org.javahispano.portal.data.RoleDao;
import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.domain.account.Role;
import org.javahispano.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**	
 * Service implementation for all account-related operations
 * 
 * @author Sergi Almar i Graupera 
 * @author Xavier Lluch Urrutia
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountDao 	accountDao;
	private RoleDao 	roleDao;
	
	@Autowired
	public AccountServiceImpl(AccountDao accountDao, RoleDao roleDao) {
		this.accountDao = accountDao;
		this.roleDao = roleDao;
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
	public Account getAccountByEmail(String email) {
    	return accountDao.getByEmail(email);
	}

    @Transactional(readOnly=true)
	public Account getAccountById(long accountId) {
    	return accountDao.getById(accountId);
	}

    @Transactional(readOnly=true)
	public Account getAccountByOpenId(String openId) {
    	return accountDao.getByOpenId(openId);    	
	}
    
    @Transactional(readOnly=true)
	public Account getAccountByUsername(String username) {
		return accountDao.getByUsername(username);
	}

	public void updateLastLogin(Account account, Date lastLogin) {
		accountDao.updateLastLogin(account.getId(), lastLogin);
	}
    
	public void saveAccount(Account account) {

		account.setGravatar(DigestUtils.md5Hex(account.getEmail().toLowerCase()));
		if(account.isNew()) {
			// Applying basic roles.
			Role role = roleDao.getByName("ROLE_USER");
			account.addRole(role);
			account.setSignupDate(new Date());
			
			accountDao.save(account);

			// Place the new Authentication object in the security context.
			Authentication authentication = createNewAuthentication(account);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} else {
			account.setLastModification(new Date());
			accountDao.save(account);	
		}
	}
	
	private OpenIDAuthenticationToken createNewAuthentication(Account account) {
		return new OpenIDAuthenticationToken(account, account.getAuthorities(), 
				account.getOpenid(), null);
	}
	
	@Transactional(readOnly=true)
	public List<Role> getAllRoles() {
		List<Role> roles = roleDao.getAll();
		return roles != null ? roles : Collections.<Role>emptyList();
	}
}