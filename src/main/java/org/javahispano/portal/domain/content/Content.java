package org.javahispano.portal.domain.content;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.javahispano.portal.domain.IdentifiableObject;
import org.javahispano.portal.domain.account.Account;

/**
 * Class representing a content
 * 
 * @author Sergi Almar i Graupera
 */
public class Content extends IdentifiableObject {

	private Account user;
	private ContentType type;
	private String title;
	private String sluggedTitle;
	private String body;
	private boolean highlighted;
	private int highlightOrder;
	private boolean draft;
	private int numVisits;
	private int numComments;
	private Date lastComment;
	private Date creationDate;
	private Date modificationDate;
	
	private Set<Tag> tags;
	private List<Comment> comments;

	public Content() {
		user = new Account();
	}
	
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}
	
	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSluggedTitle() {
		return sluggedTitle;
	}

	public void setSluggedTitle(String sluggedTitle) {
		this.sluggedTitle = sluggedTitle;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public int getHighlightOrder() {
		return highlightOrder;
	}

	public void setHighlightOrder(int highlightOrder) {
		this.highlightOrder = highlightOrder;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	
	public int getNumVisits() {
		return numVisits;
	}

	public void setNumVisits(int numVisits) {
		this.numVisits = numVisits;
	}

	public int getNumComments() {
		return numComments;
	}

	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}

	public Date getLastComment() {
		return lastComment;
	}

	public void setLastComment(Date lastComment) {
		this.lastComment = lastComment;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
}
