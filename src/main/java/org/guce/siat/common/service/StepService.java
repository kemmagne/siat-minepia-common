package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.StepCode;




/**
 * The Interface StepService.
 */
public interface StepService extends AbstractService<Step>
{

	/**
	 * Find by authoroties id.
	 *
	 * @param authority
	 *           the id authority
	 * @return the list
	 */
	List<Step> findByAuthority(Authority authority);
        
        Step findByStepCode(StepCode stepCode);

}
