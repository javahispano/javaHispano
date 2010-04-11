package org.javahispano.portal.domain;

/**
 * Base class for the domain objects
 * 
 * @author Sergi Almar 
 */
public class IdentifiableObject {

	protected Long id;

	public boolean isNew() {
		return id == null;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("[");
		builder.append(this.getId());
		builder.append("]");
		return builder.toString();
	}
	
}
