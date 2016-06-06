package org.guce.siat.common.service;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.Delegation;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;




/**
 * The Interface DelegationService.
 */
public interface DelegationService extends AbstractService<Delegation>
{

	/**
	 * Find delegation by date by from users.
	 *
	 * @param toUser
	 *           the to user
	 * @param bDate
	 *           the b date
	 * @param eDate
	 *           the e date
	 * @return the list
	 */
	List<Delegation> findDelegationByDateByFromUsers(User toUser, Date bDate, Date eDate);

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
	List<Delegation> findDelegationByDateByToUsers(final User user, final Date bDate, final Date eDate);
	
	/**
	 * Find by organism.
	 *
	 * @param organism the organism
	 * @return the list
	 */
	List<Delegation> findByOrganism(Organism organism);

	/**
	 * Find delegation by user and current date.
	 *
	 * @param user
	 *           the user
	 * @return the list
	 */
	List<Delegation> findDelegationByUserAndCurrentDate(User user);

	/**
	 * Find delegation users by user and current date.
	 *
	 * @param user
	 *           the user
	 * @return the list
	 */
	List<User> findDelegationUsersByUserAndCurrentDate(User user);

}
