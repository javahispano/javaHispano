package org.javahispano.portal.data;

import java.util.Date;

import org.javahispano.portal.domain.account.Account;

/**
 * Dao for Account
 * 
 * @author Sergi Almar i Graupera
 * @author Xavier Lluch Urrutia
 */
public interface AccountDao  extends GenericDao<Long, Account> {
	
	/**
	 * Retrieves an account based on the email
	 *
	 * @param  email the email of the account
	 * @return the account
	 */
	Account getByEmail(String email);
	
	/**
	 * Retrieves an account based on the openid
	 *
	 * @param  openid the openid of the account
	 * @return the account
	 */
	Account getByOpenId(String openId);
	
	/**
	 * Retrieves an account based on the username
	 *
	 * @param  email the email of the account
	 * @return the account
	 */
	Account getByUsername(String username);
	
	/**
	 * Updates the last login date / time
	 *
	 * @param accountId the id of the account to update
	 * @param lastLogin the exact date / time of the last login
	 */
	void updateLastLogin(long accountId, Date lastLogin);
}