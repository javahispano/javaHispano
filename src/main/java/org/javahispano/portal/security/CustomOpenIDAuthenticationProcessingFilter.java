package org.javahispano.portal.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javahispano.portal.domain.account.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * Customized OpenId Filter to get OpenId attributes and put them to User for
 * signup processing.
 * 
 * @author Xavier Lluch Urrutia
 */
public class CustomOpenIDAuthenticationProcessingFilter extends OpenIDAuthenticationFilter {

	public CustomOpenIDAuthenticationProcessingFilter() {
		super(); 
	}

	/**
	 * Customized attempt authentication method that handle the Usernotfound exception 
	 * from super class and retrieve all the openid attributes to the request.
	 * 
	 * @param request the http servlet request
	 * @param response the http servlet response
	 * @return the authentication returned by the super class attemptAuthentication method
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
	                     										throws AuthenticationException, IOException {
		Authentication newAuthentication = null;
		try {
			newAuthentication = super.attemptAuthentication(request, response);
		}
		catch (UsernameNotFoundException unfe) {
			OpenIDAuthenticationToken token = (OpenIDAuthenticationToken) unfe.getAuthentication();
			if (token.getStatus() == OpenIDAuthenticationStatus.SUCCESS) {
				List<OpenIDAttribute> attributes = token.getAttributes();
				
				Account account = new Account();
				account.setOpenid(token.getIdentityUrl());
				
				for (OpenIDAttribute attribute : attributes) {
					if ("username".equalsIgnoreCase(attribute.getName())) {
				        account.setUsername(attribute.getValues().get(0));
					}
					if ("email".equalsIgnoreCase(attribute.getName())) {
				        account.setEmail(attribute.getValues().get(0));
					}
					if ("firstname".equalsIgnoreCase(attribute.getName())) {
				        account.setFirstName(attribute.getValues().get(0));
					}
					if ("lastname".equalsIgnoreCase(attribute.getName())) {
				        account.setLastName(attribute.getValues().get(0));
					}
				}
				request.setAttribute("account", account);
				throw unfe;
			}
		}
		return newAuthentication;
	}
}