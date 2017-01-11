package org.guce.siat.common.service;

import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.GuceSiatBureau;


/**
 * The Interface GuceSiatBureauService.
 */
public interface GuceSiatBureauService extends AbstractService<GuceSiatBureau>
{

	/**
	 * Find guce siat bureau by siat bureau.
	 *
	 * @param siatBureau
	 *           the siat bureau
	 * @return the guce siat bureau
	 */
	GuceSiatBureau findGuceSiatBureauBySiatBureau(Bureau siatBureau);


	/**
	 * Find guce siat bureau by guce bureau.
	 *
	 * @param guceBureau
	 *           the guce bureau
	 * @return the guce siat bureau
	 */
	GuceSiatBureau findGuceSiatBureauByGuceBureau(String guceBureau);
}
