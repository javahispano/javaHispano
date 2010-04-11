package org.javahispano.portal.data;

import java.util.Collection;

import org.javahispano.portal.domain.content.Content;

/**
 * Dao for Account
 * 
 * @author Sergi Almar i Graupera
 */
public interface ContentDao extends GenericDao<Long, Content> {
	Collection<Content> getHighlightedContent();
}
