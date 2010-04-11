package org.javahispano.portal.domain.account;

import org.javahispano.portal.domain.IdentifiableObject;
import org.springframework.security.core.GrantedAuthority;

/**
 * Class representing an account role
 * 
 * @author Sergi Almar i Graupera
 */
public class Role extends IdentifiableObject implements GrantedAuthority {

	private String name;
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthority() {
		return name;
	}

}
