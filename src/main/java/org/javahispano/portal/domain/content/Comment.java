package org.javahispano.portal.domain.content;

import java.util.Date;

import org.javahispano.portal.domain.IdentifiableObject;
import org.javahispano.portal.domain.account.Account;

/**
 * Class representing a content comment
 * 
 * @author Sergi Almar i Graupera
 */
public class Comment extends IdentifiableObject {
	
	private Content content;
	private Account user;
	private String body;
	private boolean deleted;
	private Date creationDate;
	
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	

}
