package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Step;



/**
 * The Interface StepDao.
 */
public interface StepDao extends AbstractJpaDao<Step>
{

	/**
	 * Find by authoroties id.
	 *
	 * @param authority
	 *           the id authority
	 * @return the list
	 */
	List<Step> findByAuthority(Authority authority);

}
