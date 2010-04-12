package org.javahispano.portal.data.ibatis;

import java.util.List;

import org.javahispano.portal.data.CommentDao;
import org.javahispano.portal.domain.content.Comment;
import org.springframework.stereotype.Repository;

/**
* iBatis 2 implementation of the comment DAO 
*
* @author Sergi Almar i Graupera
*/
@Repository
public class SqlMapCommentDao extends SqlMapGenericDao<Long, Comment> implements CommentDao {

	public SqlMapCommentDao() {
		super(Comment.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByContentId(long contentId) {
		return sqlMapClientTemplate.queryForList(this.getNamespace() + "getCommentsByContentId", contentId);
	}
}
