package org.javahispano.portal.data.ibatis;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.javahispano.portal.data.GenericDao;
import org.javahispano.portal.domain.IdentifiableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
* iBatis 2 implementation of the generic DAO 
*
* @author Sergi Almar i Graupera
*/
public abstract class SqlMapGenericDao<K, T extends IdentifiableObject> implements GenericDao<K, T> {

	protected static final String QUERY_SELECT_PREFIX      = "get";
	protected static final String QUERY_SELECT_ALL_SUFFIX  = "s";
	protected static final String QUERY_SELECT_BYID_SUFFIX = "ById";
	protected static final String QUERY_UPDATE_PREFIX      = "update";
	protected static final String QUERY_INSERT_PREFIX      = "save";
	protected static final String QUERY_DELETE_PREFIX      = "delete";
	protected static final String NAMESPACE_SEPARATION	   = ".";
	
	@Autowired
	protected SqlMapClientTemplate sqlMapClientTemplate;
	private Class<T> persistentClass;
	private String persistentClassShortName;
	private String 	namespace;
	
    /**
     *	Constructor that takes in a class to see which type of entity to persist.
     *
     * @param persistentClass the class type you'd like to persist
     */
	public SqlMapGenericDao(final Class<T> persistentClass) {
		this.persistentClass     = persistentClass;
		persistentClassShortName = ClassUtils.getShortClassName(getPersistentClass().getName());
		namespace                = persistentClassShortName + NAMESPACE_SEPARATION;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String queryName = QUERY_SELECT_PREFIX + persistentClassShortName + QUERY_SELECT_ALL_SUFFIX;
		
		return this.sqlMapClientTemplate.queryForList(namespace + queryName);
	}
	
	@SuppressWarnings("unchecked")
	public T getById(final K id) {
		String queryName = QUERY_SELECT_PREFIX + persistentClassShortName + QUERY_SELECT_BYID_SUFFIX;
		
		return (T) this.sqlMapClientTemplate.queryForObject(namespace + queryName, id);
	}
	
	public void delete(final K id) {
		String queryName = QUERY_DELETE_PREFIX + persistentClassShortName;
		
		this.sqlMapClientTemplate.delete(namespace + queryName, id);
	}

	public void save(final T entity) {
		String queryName = null;
		
		// Identify if needs to be updated or created
		if(entity instanceof IdentifiableObject && ((IdentifiableObject) entity).isNew()) {
			queryName = QUERY_INSERT_PREFIX + persistentClassShortName;
			this.sqlMapClientTemplate.insert(namespace + queryName, entity);
		} else {
			queryName = QUERY_UPDATE_PREFIX + persistentClassShortName;
			this.sqlMapClientTemplate.update(namespace + queryName, entity);
		}
	}
	
	public void save(final T entity, final Map<String, Object> params) {
		
		String paramName = persistentClassShortName.toLowerCase();
		String queryName = null;
		
		params.put(paramName, entity);
		
		// Identify if needs to be updated or created
		if(entity instanceof IdentifiableObject && ((IdentifiableObject) entity).isNew()) {
			queryName = QUERY_INSERT_PREFIX + persistentClassShortName;
			this.sqlMapClientTemplate.insert(namespace + queryName, params);
		} else {
			queryName = QUERY_UPDATE_PREFIX + persistentClassShortName;
			this.sqlMapClientTemplate.update(namespace + queryName, params);
		}
	}

	@SuppressWarnings("unchecked")
	public void save(final Collection<T> entities) {
		
		sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                //Start batch
                executor.startBatch();
         
                for(T entity : entities) {
                    executor.insert(namespace + (entity.isNew() ? QUERY_INSERT_PREFIX : QUERY_UPDATE_PREFIX) + persistentClassShortName, entity);
                }
                
                // Execute batch
                int rowsaffected = executor.executeBatch();
                return new Integer(rowsaffected);
            }
        });
	}
	
	@SuppressWarnings("unchecked")
	public void save(final Collection<T> entities, final Map<String, Object> params) {
		
		final String paramName = persistentClassShortName.toLowerCase();
		
		sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                //Start batch
                executor.startBatch();
         
                for(T entity : entities) {
                	params.put(paramName, entity);
                    executor.insert(namespace + (entity.isNew() ? QUERY_INSERT_PREFIX : QUERY_UPDATE_PREFIX) +  persistentClassShortName, params);
                }
                
                // Execute batch
                int rowsaffected = executor.executeBatch();
                return new Integer(rowsaffected);
            }
        });
	}
	
	/**
	 * Returns the persistent class
	 * 
	 * @return the class this DAO takes care of
	 */
	public final Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	/**
	 * Returns class short name 
	 * i.e Short name for org.javahispano.portal.data.ibatis.SqlMapGenericDao turns is SqlMapGenericDao
	 * 
	 * @return the class short name
	 */
	public final String getPersistentClassShortName() {
		return persistentClassShortName;
	}
	
	/**
	 * Returns the namespace of the sql map 
	 * 
	 * @return the namespace
	 */
	public final String getNamespace() {
		return namespace;
	}
}
