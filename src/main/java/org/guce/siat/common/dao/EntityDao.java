package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.utils.enums.PositionType;


/**
 * The Interface EntityDao.
 */
public interface EntityDao extends AbstractJpaDao<Entity>
{

	/**
	 * Find active entities by service.
	 *
	 * @param service
	 *           the service
	 * @return the list
	 */
	List<Entity> findActiveEntitiesByService(Service service);


	/**
	 * Find non affected entity by service and position.
	 *
	 * @param service
	 *           the service
	 * @param position
	 *           the position
	 * @return the list
	 */
	List<Entity> findNonAffectedEntityByServiceAndPosition(final Service service, PositionType position);


}
