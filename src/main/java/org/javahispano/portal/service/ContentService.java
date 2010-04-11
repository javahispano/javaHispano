package org.javahispano.portal.service;

import java.util.Collection;

import org.javahispano.portal.domain.content.Content;

/**
 * Interface for all content-related operations
 * 
 * @author Sergi Almar i Graupera 
 */
public interface ContentService {
	/**
	 * Retrieves a content by its id
	 * 
	 * @param contentId the id of the content
	 * @return the requested <code>Content</code> 
	 */
	Content getContentById(long contentId);
	
	/**
	 * Retrieves the highlighted content
	 * 
	 * @return a collection with the highlighted <code>Content</code> 
	 */
	Collection<Content> getHighlightedContent();
	
	/**
	 * Retrieves all content
	 * 
	 * @return a collection with all <code>Content</code> 
	 */
	Collection<Content> getAllContent();
	
	/**
	 * Saves a <code>Content</code>
	 * 
	 * @param content the content to save
	 */
	void saveContent(Content content);
} 
