package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.PositionType;




/**
 * The Interface EntityService.
 */
public interface EntityService extends AbstractService<Entity>
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

	/**
	 * Find entity by user.
	 *
	 * @param user
	 *           the user
	 * @return the entity
	 */
	Entity findEntityByUser(User user);

}
