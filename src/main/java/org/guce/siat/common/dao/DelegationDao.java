package org.guce.siat.common.dao;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.Delegation;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;




/**
 * The Interface DelegationDao.
 */
public interface DelegationDao extends AbstractJpaDao<Delegation>
{

	/**
	 * Find delegation by date by from users.
	 *
	 * @param user
	 *           the user
	 * @param bDate
	 *           the b date
	 * @param eDate
	 *           the e date
	 * @return the list
	 */
	List<Delegation> findDelegationByDateByFromUsers(User user, Date bDate, Date eDate);

	/**
	 * Find delegation by date by to users.
	 *
	 * @param user
	 *           the user
	 * @param bDate
	 *           the b date
	 * @param eDate
	 *           the e date
	 * @return the list
	 */
	List<Delegation> findDelegationByDateByToUsers(User user, Date bDate, Date eDate);

	/**
	 * Find delegation by user and current date.
	 *
	 * @param user
	 *           the user
	 * @return the list
	 */
	List<Delegation> findDelegationByUserAndCurrentDate(User user);
	
	/**
	 * Find by organism.
	 *
	 * @param organism the organism
	 * @return the list
	 */
	List<Delegation> findByOrganism(Organism organism);

}
