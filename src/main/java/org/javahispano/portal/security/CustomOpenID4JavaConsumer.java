package org.javahispano.portal.security;

import java.util.Arrays;

import org.openid4java.consumer.ConsumerException;
import org.springframework.security.openid.OpenID4JavaConsumer;
import org.springframework.security.openid.OpenIDAttribute;


/**
 * Customized class of OpenID4JavaConsumer to retrieve the OpenId Attributes
 * 
 * @author Xavier Lluch Urrutia
 */
public class CustomOpenID4JavaConsumer extends OpenID4JavaConsumer {
	
	private static final OpenIDAttribute EMAIL     = new CustomOpenIDAttribute("email", "http://axschema.org/contact/email"); 
	private static final OpenIDAttribute USERNAME  = new CustomOpenIDAttribute("username", "http://axschema.org/namePerson/friendly");
	private static final OpenIDAttribute FIRSTNAME = new CustomOpenIDAttribute("firstname", "http://axschema.org/namePerson/first");
	private static final OpenIDAttribute LASTNAME  = new CustomOpenIDAttribute("lastname", "http://axschema.org/namePerson/last");
	
	public CustomOpenID4JavaConsumer() throws ConsumerException {
		super(Arrays.asList(EMAIL,
							USERNAME,
							FIRSTNAME,
							LASTNAME));
	}
}