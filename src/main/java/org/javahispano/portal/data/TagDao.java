package org.javahispano.portal.data;

import java.util.Collection;

import org.javahispano.portal.domain.content.Tag;

/**
 * Dao for Account
 * 
 * @author Sergi Almar i Graupera
 */
public interface TagDao extends GenericDao<Long, Tag> {
	
	/**
	 * Retrieve tag by its name
	 * 
	 * @param name the tag name
	 * @return the requested tag
	 */
	Tag getByName(String name);
	
	/**
	 * Retrieves all tags bound to a content id
	 * 
	 * @param contentId the if ob the content
	 * @return the collection of tags bound to the content
	 */
	Collection<Tag> getByContentId(long contentId);
	
	/**
	 * Increments the usage (times used) to the tags bound to the specified content
	 * 
	 * @param the content id 
	 */
	void incrementTagUsage(long contentId);
	
	/**
	 * Decrements the usage (times used) to the tags bound to the specified content
	 * 
	 * @param the content id
	 */
	void decrementTagUsage(long contentId);
}
