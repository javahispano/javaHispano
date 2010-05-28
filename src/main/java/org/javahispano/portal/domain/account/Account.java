package org.javahispano.portal.domain.account;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.javahispano.portal.domain.IdentifiableObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class representing an account
 * 
 * @author Sergi Almar
 */
public class Account extends IdentifiableObject implements UserDetails {

	private static final long serialVersionUID = -695880253279846247L;

	protected String userName;
	protected String password;
	protected String email;
	protected String openId;
	protected String gravatar;
	protected String linkedIn;
	protected String twitter;

	protected String firstName;
	protected String lastName;

	protected AccountVisibility accountVisibility;
	protected Date lastLogin;
	protected Date lastModification;
	protected Date signupDate;

	// Security fields
	protected boolean deleted;
	protected boolean enabled;
	protected boolean accountNonExpired;
	protected boolean credentialsNonExpired;
	protected boolean accountNonLocked;

	protected Set<Role> roles = new HashSet<Role>();
	
	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOpenid(String openId) {
		this.openId = openId;
	}

	public String getOpenid() {
		return openId;
	}

	public void setGravatar(String gravatar) {
		this.gravatar = gravatar;
	}

	public String getGravatar() {
		return gravatar;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAccountVisibility(AccountVisibility accountVisibility) {
		this.accountVisibility = accountVisibility;
	}

	public AccountVisibility getAccountVisibility() {
		return accountVisibility;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (role != null) {
			roles.add(role);
		}
	}
	public Collection<GrantedAuthority> getAuthorities() {
		return new HashSet<GrantedAuthority>(roles);
	}

}
