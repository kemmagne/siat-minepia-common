package org.guce.siat.common.service;

import org.guce.siat.common.model.TransportMode;


/**
 * The Interface TransportModeService.
 */
public interface TransportModeService extends AbstractService<TransportMode>
{



	/**
	 * Find by transport mode code.
	 *
	 * @param modeTransportCode
	 *           the mode transport code
	 * @return the transport mode
	 */
	TransportMode findByTransportModeCode(final Long modeTransportCode);

}
