package org.javahispano.portal.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationProvider;

/**
 * Customized OpenId Provider to inject the token with OpenId attributes 
 *  when the super.authentication method throws a UserNotFound Exception.
 * 
 * @author Xavier Lluch Urrutia
 */
public class CustomOpenIDAuthenticationProvider extends OpenIDAuthenticationProvider {
	
	public CustomOpenIDAuthenticationProvider(UserDetailsService userDetailsService) {
		this.setUserDetailsService(userDetailsService);
	}
	
	@Override
	public Authentication authenticate(final Authentication  authentication) throws AuthenticationException  {
		Authentication newAuthentication = null;		
		try {
			newAuthentication = super.authenticate(authentication);
		}
		catch (UsernameNotFoundException unfe) {
			unfe.setAuthentication(authentication);
			throw unfe;
		}
		return newAuthentication;
	}
}