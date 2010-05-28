package org.javahispano.portal.service;

import java.util.Date;
import java.util.List;

import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.domain.account.Role;

/**
 * Interface for all account-related operations
 * 
 * @author Sergi Almar i Graupera 
 * @author Xavier Lluch Urrutia 
 */
public interface AccountService {
	/**
	 * Retrieves the current user from the security context
	 * 
	 * @return the <code>Account</code> 
	 */
	Account getCurrentUser();
	
	/**
	 * Retrieves an account by the email
	 * 
	 * @param email the email of the account
	 * @return the requested Account 
	 */
	Account getAccountByEmail(String email);
	/**
	 * Retrieves an account by the id
	 * 
	 * @param accountId the id of the account
	 * @return the requested Account 
	 */
	Account getAccountById(long accountId);
	/**
	 * Retrieves an account by the openId
	 * 
	 * @param openId the openId of the account
	 * @return the requested Account 
	 */
	Account getAccountByOpenId(String openId);
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
		
	/**
	 * Retrieves all roles
	 * 
	 * @return a collection with all <code>Role</code> 
	 */
	List<Role> getAllRoles();
}
