package org.javahispano.portal.domain.content;

import org.javahispano.portal.domain.IdentifiableObject;

/**
 * Class representing a tag
 * 
 * @author Sergi Almar i Graupera
 */
public class Tag extends IdentifiableObject {
	private String name;
	private int timesUsed;
	
	public Tag() {	
	}
	
	public Tag(String name) {
		this.name = name.trim();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimesUsed() {
		return timesUsed;
	}
	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}
	
	public String toString() {
		return name;
	}

}
