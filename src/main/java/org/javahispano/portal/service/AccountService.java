package org.javahispano.portal.service;

import java.util.Date;

import org.javahispano.portal.domain.account.Account;

/**
 * Interface for all account-related operations
 * 
 * @author Sergi Almar i Graupera 
 */
public interface AccountService {
	/**
	 * Retrieves the current user from the security context
	 * 
	 * @return the <code>Account</code> 
	 */
	Account getCurrentUser();
	
	/**
	 * Retrieves an account by the username
	 * 
	 * @param username the username of the account
	 * @return the requested <code>Account</code> 
	 */
	Account getAccountByUsername(String username);
	
	/**
	 * Updates the last login of the user
	 * 
	 * @param account the account to update
	 * @param lastLogin the last login date
	 */
	void updateLastLogin(Account account, Date lastLogin);
	
	/**
	 * Saves an account
	 * 
	 * @param account the account to save
	 */
	void saveAccount(Account account);
}
