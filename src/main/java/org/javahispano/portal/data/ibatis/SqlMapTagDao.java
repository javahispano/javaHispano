package org.javahispano.portal.data.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javahispano.portal.data.TagDao;
import org.javahispano.portal.domain.content.Tag;
import org.springframework.stereotype.Repository;

/**
* iBatis 2 implementation of the tag DAO 
*
* @author Sergi Almar i Graupera
*/
@Repository
public class SqlMapTagDao extends SqlMapGenericDao<Long, Tag> implements TagDao {

	public SqlMapTagDao() {
		super(Tag.class);
	}
	
	public Tag getByName(String name) {
		return (Tag) sqlMapClientTemplate.queryForObject(this.getNamespace() + "getTagByName", name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> getByContentId(long contentId) {
		return sqlMapClientTemplate.queryForList(this.getNamespace() + "getTagsByContentId" , contentId);
	}
	
	
	public void incrementTagUsage(long contentId) {
		this.updateTagUsage(contentId, 1);
	}

	public void decrementTagUsage(long contentId) {
		this.updateTagUsage(contentId, -1);
	}
	
	private void updateTagUsage(long contentId, int variation) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("variation", variation);
		params.put("contentId", contentId);
		
		sqlMapClientTemplate.update(this.getNamespace() + "updateTagUsage", params);
	}
}
