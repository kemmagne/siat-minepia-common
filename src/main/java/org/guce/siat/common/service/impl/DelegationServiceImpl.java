package org.guce.siat.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.DelegationDao;
import org.guce.siat.common.model.Delegation;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class DelegationServiceImpl.
 */
@Service("delegationService")
@Transactional(readOnly = true)
public class DelegationServiceImpl extends AbstractServiceImpl<Delegation>
		implements DelegationService {

	/** The delegation dao. */
	@Autowired
	private DelegationDao delegationDao;

	/**
	 * Instantiates a new delegation service impl.
	 */
	public DelegationServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Delegation> getJpaDao() {
		return delegationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce
	 * .siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Delegation> jpaDao) {
		this.delegationDao = (DelegationDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.DelegationService#
	 * findDelegationByDateByFromUsers(org.guce.siat.common.model.User,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<Delegation> findDelegationByDateByFromUsers(final User toUser,
			final Date bDate, final Date eDate) {
		return delegationDao.findDelegationByDateByFromUsers(toUser, bDate,
				eDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.DelegationService#findDelegationByDateByToUsers
	 * (org.guce.siat.common.model.User, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Delegation> findDelegationByDateByToUsers(final User user,
			final Date bDate, final Date eDate) {
		return delegationDao.findDelegationByDateByToUsers(user, bDate, eDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.DelegationService#
	 * findDelegationByUserAndCurrentDate(org.guce.siat.common.model.User)
	 */
	@Override
	public List<Delegation> findDelegationByUserAndCurrentDate(final User user) {
		return delegationDao.findDelegationByUserAndCurrentDate(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.DelegationService#
	 * findDelegationUsersByUserAndCurrentDate(org.guce.siat.common.model .User)
	 */
	@SuppressWarnings("unchecked")
	public List<User> findDelegationUsersByUserAndCurrentDate(final User user) {
		final List<Delegation> delegationList = delegationDao
				.findDelegationByUserAndCurrentDate(user);

		return (List<User>) CollectionUtils.collect(delegationList,
				new Transformer() {
					@Override
					public Object transform(final Object delegation) {
						return ((Delegation) delegation).getFromUser();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.DelegationService#findByOrganism(org.guce
	 * .siat.common.model.Organism)
	 */
	@Override
	public List<Delegation> findByOrganism(Organism organism) {
		return delegationDao.findByOrganism(organism);
	}

}
