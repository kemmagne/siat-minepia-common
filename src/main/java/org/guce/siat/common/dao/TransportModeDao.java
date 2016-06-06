package org.guce.siat.common.dao;

import org.guce.siat.common.model.TransportMode;





/**
 * The Interface TransportModeDao.
 */
public interface TransportModeDao extends AbstractJpaDao<TransportMode>
{


	/**
	 * Find by transport mode code.
	 *
	 * @param transportModeCode
	 *           the transport mode code
	 * @return the transport mode
	 */
	TransportMode findByTransportModeCode(Long transportModeCode);

}
