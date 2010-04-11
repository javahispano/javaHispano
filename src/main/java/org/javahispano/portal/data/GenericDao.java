package org.javahispano.portal.data;

import java.util.Collection;
import java.util.Map;

import org.javahispano.portal.domain.IdentifiableObject;

/**
 * Generic Dao
 * 
 * @author Sergi Almar i Graupera
 */
public interface GenericDao<K, T extends IdentifiableObject> {
	
	/**
	 * Retrieve all object of a certain type
	 * @return a list of requested object
	 */
	Collection<T> getAll();
	
	/**
	 * Retrieve an object by its id
	 * @param id the id of the object to retrieve
	 * @return the requested object
	 */
	T getById(final K id);
	
	/**
	 * Deletes an object from the database
	 * @param id the id of the object to delete
	 */
	
	void delete(final K id);
	
	/**
	 * Saves or updates an object
	 * @param entity The object to save or update
	 */
	void save(final T entity);
	
	/**
	 * Saves or updates an object passing some parameters
	 * @param entity the object to save or update
	 * @param params the additional parameters to pass
	 */
	void save(final T entity, final Map<String, Object> params);
	
	/**
	 * Saves a collection of objects
	 * @param entities the collection of objects to save
	 */
	void save(final Collection<T> entities);
	
	/**
	 * Saves a collection of objects passing some parameters
	 * @param entities the collection of objects to save
	 * @param params the additional parameters to pass
	 */
	void save(final Collection<T> entities, final Map<String, Object> params);
}
