package org.javahispano.portal.service;

import java.util.List;

import org.javahispano.portal.domain.account.Role;

/**
 * Interface for all role-related operations
 * 
 * @author Xavier Lluch Urrutia 
 */
public interface RoleService {
	
	/**
	 * Retrieves a role based on the name
	 *
	 * @param  name the name of the role
	 * @return the role
	 */
	Role getByName(String name);
	/**
	 * Retrieves a role based on the description
	 *
	 * @param  description the description of the role
	 * @return the role
	 */
	Role getByDescription(String descriptions);
	/**
	 * Retrieves a role based on an account id
	 *
	 * @param  accountId the id of the account
	 * @return the collection of account roles
	 */
	List<Role> getByAccountId(long accountId);

}
