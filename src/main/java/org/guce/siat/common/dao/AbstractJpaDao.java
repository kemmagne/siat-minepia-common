package org.guce.siat.common.dao;

import java.io.Serializable;
import java.util.List;


/**
 * The Interface AbstractJpaDao.
 *
 * @param <T>
 *           the generic type
 */
public interface AbstractJpaDao<T extends Serializable>
{

	/**
	 * Sets the class.
	 *
	 * @param classeToSet
	 *           the new class
	 */
	void setClasse(final Class<T> classeToSet);

	/**
	 * Find.
	 *
	 * @param id
	 *           the id
	 * @return the t
	 */
	T find(final Long id);

	/**
	 * Find.
	 *
	 * @param id
	 *           the id
	 * @return the t
	 */
	T find(final String id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();


	/**
	 * Find active items.
	 *
	 * @return the list
	 */
	List<T> findActiveItems();

	/**
	 * Save.
	 *
	 * @param entity
	 *           the entity
	 * @return the t
	 */
	T save(final T entity);

	/**
	 * Update.
	 *
	 * @param entity
	 *           the entity
	 */
	void update(final T entity);

	/**
	 * Delete.
	 *
	 * @param entity
	 *           the entity
	 */
	void delete(final T entity);

	/**
	 * Delete by id.
	 *
	 * @param entityId
	 *           the entity id
	 */
	void deleteById(final Long entityId);

	/**
	 * Save or update list.
	 *
	 * @param entitiesList
	 *           the entities list
	 * @return the list
	 */
	List<T> saveOrUpdateList(final List<T> entitiesList);


	/**
	 * Delete list.
	 *
	 * @param entitiesList
	 *           the entities list
	 */
	void deleteList(final List<T> entitiesList);

	/**
	 * Save list.
	 *
	 * @param entitiesList
	 *           the entities list
	 * @return the list
	 */
	List<T> saveList(List<T> entitiesList);

	/**
	 * Clear.
	 */
	void clear();
}
