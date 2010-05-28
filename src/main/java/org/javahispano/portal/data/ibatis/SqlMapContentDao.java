package org.javahispano.portal.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.javahispano.portal.data.AccountDao;
import org.javahispano.portal.data.CommentDao;
import org.javahispano.portal.data.ContentDao;
import org.javahispano.portal.data.TagDao;
import org.javahispano.portal.domain.content.Content;
import org.javahispano.portal.domain.content.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/* ibatis 2 implementation of the content DAO 
*
* @author Sergi Almar i Graupera
* @author Xavier Lluch Urrutia
*/
@Repository
public class SqlMapContentDao extends SqlMapGenericDao<Long, Content> implements ContentDao {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private CommentDao commentDao;
	
	public SqlMapContentDao() {
		super(Content.class);
	}
	
	@Override
	public Content getById(Long contentId) {
		Content content = super.getById(contentId);
		
		// Retrieve tags
		if(content != null) {
			content.setTags(new HashSet<Tag>(tagDao.getByContentId(content.getId())));
			content.setComments(commentDao.getByContentId(content.getId()));
			content.setUser(accountDao.getById(content.getUser().getId()));
		}
		
		return content;
	}
	
	@SuppressWarnings("unchecked")
	public List<Content> getHighlightedContent() {
		return sqlMapClientTemplate.queryForList(this.getNamespace() + "getHighlightedContent");
	}
	
	@Override
	public void save(Content content) {
		
		// If it's an existing content, let's delete all previous tags
		if(!content.isNew()) {
			this.unbindContentTags(content);
		}
	
		// Save / update the content
		super.save(content);
		
		if(!content.getTags().isEmpty()) {
			// Retrieve the tags id's and create the new ones
			for(Tag tag : content.getTags()) {
				Tag existingTag = tagDao.getByName(tag.getName());
				if(existingTag == null) {
					tagDao.save(tag);
				} else {
					tag.setId(existingTag.getId());
				}
			}
			
			// Bind tags to content
			this.bindContentTags(content);
			
			// Update how many times the tag has been used
			tagDao.incrementTagUsage(content.getId());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void bindContentTags(final Content content) {
		sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                //Start batch
                executor.startBatch();
         
                Map<String, Long> params = new HashMap<String, Long>();
                params.put("contentId", content.getId());
                
                for(Tag tag : content.getTags()) {
                	params.put("tagId", tag.getId());
                    executor.insert(getNamespace() + "bindContentTag", params);
                }
                
                // Execute batch
                int rowsaffected = executor.executeBatch();
                return new Integer(rowsaffected);
            }
        });
	}
	
	private void unbindContentTags(final Content content) {
		tagDao.decrementTagUsage(content.getId());
		sqlMapClientTemplate.delete(this.getNamespace() + "unbindContentTags", content.getId());
	}
}