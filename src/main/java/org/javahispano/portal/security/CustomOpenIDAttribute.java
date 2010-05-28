package org.javahispano.portal.security;

import org.springframework.security.openid.OpenIDAttribute;

@SuppressWarnings("serial")
public class CustomOpenIDAttribute extends OpenIDAttribute {

	public CustomOpenIDAttribute(String name, String type) {
		super(name, type);
		this.setRequired(true);
	}

}
