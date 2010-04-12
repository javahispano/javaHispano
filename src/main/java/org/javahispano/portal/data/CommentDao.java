package org.javahispano.portal.data;

import java.util.List;

import org.javahispano.portal.domain.content.Comment;

/**
 * Dao for Comment
 * 
 * @author Sergi Almar i Graupera
 */
public interface CommentDao extends GenericDao<Long, Comment> {
	List<Comment> getByContentId(long contentId);
}
