package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.OrganismDao;
import org.guce.siat.common.dao.SubDepartmentDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.service.AdministrationService;
import org.guce.siat.common.service.DelegationService;
import org.guce.siat.common.service.UserService;
import org.guce.siat.common.service.annotations.Audit;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class UserServiceImpl.
 */
@org.springframework.stereotype.Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService
{

	/** The user dao. */
	@Autowired
	UserDao userDao;

	/** The organism dao. */
	@Autowired
	OrganismDao organismDao;

	/** The administration service. */
	@Autowired
	AdministrationService administrationService;

	/** The delegation service. */
	@Autowired
	DelegationService delegationService;

	/** The sub department dao. */
	@Autowired
	SubDepartmentDao subDepartmentDao;


	/**
	 * Instantiates a new user service impl.
	 */
	public UserServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#createUser(org.guce.siat.common.model.User)
	 */
	@Override
	@Transactional(readOnly = false)
	@Audit(operationType = "SAVE")
	public void createUser(final User user)
	{
		userDao.createUser(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#updateUser(org.guce.siat.common.model.User)
	 */
	@Override
	@Transactional(readOnly = false)
	@Audit(operationType = "SAVE")
	public void updateUser(final User user)
	{
		userDao.updateUser(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findByLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User findByLogin(final String userName)
	{
		final User user = userDao.getUserByLogin(userName);
		if(user != null){
			return populateDelegationToLoggedUser(user);
		}else{
			return null;
		}
		
	}

	/**
	 * Populate delegation to logged user : delegated authorities, delegated users and delegated user authorities.
	 *
	 * @param user
	 *           the user
	 * @return the user
	 */
	private User populateDelegationToLoggedUser(final User user)
	{

		final List<User> delegatorUserList = delegationService.findDelegationUsersByUserAndCurrentDate(user);

		if (CollectionUtils.isNotEmpty(delegatorUserList))
		{
			// ************** Set delegator users to the logged user **************
			user.setDelegatorList(delegatorUserList);

			// ************** Set delegated authorities to the logged user **************
			final Set<Authority> delegatedAuthorityList = new HashSet<Authority>();

			for (final User dUser : delegatorUserList)
			{
				delegatedAuthorityList.addAll(dUser.getAuthorities());
			}
			user.setDelegatedAuthorityList(new ArrayList<Authority>(delegatedAuthorityList));

			// ************** Set delegated userAuthority to the logged user **************
			final List<UserAuthority> delegatedUserAuthorityList = new ArrayList<UserAuthority>();

			for (final User dUser : delegatorUserList)
			{
				delegatedUserAuthorityList.addAll(dUser.getUserAuthorityList());
			}
			user.setDelegatedUserAuthorityList(new ArrayList<UserAuthority>(delegatedUserAuthorityList));
		}

		return user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<User> getJpaDao()
	{
		return userDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<User> jpaDao)
	{
		this.userDao = (UserDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findAllActiveAdminsNotaffected()
	 */
	@Override
	public List<User> findAllActiveAdminsNotaffected()
	{
		final List<Organism> allOrganism = organismDao.findAll();
		final List<User> users = userDao.findUsersByAuthorities(AuthorityConstants.ADMIN_ORGANISME.getCode());
		final List<User> returnedUsers = new ArrayList<User>();

		for (final User user : users)
		{
			boolean found = false;
			for (final Organism organism : allOrganism)
			{
				if (organism.getAdminOrganism() != null && user.getId().equals(organism.getAdminOrganism().getId()))
				{
					found = true;
					break;
				}
			}

			if (!found)
			{
				returnedUsers.add(user);
			}
		}

		return returnedUsers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findByMail(java.lang.String)
	 */
	@Override
	public User findByMail(final String email)
	{
		return userDao.findByMail(email);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findControleursByService(org.guce.siat.common.model.Service)
	 */
	@Override
	public List<User> findControleursByService(final Service service)
	{
		final List<User> users = userDao.findUsersByAuthorities(AuthorityConstants.CONTROLEUR.getCode());
		final List<User> returnedUsers = new ArrayList<User>();
		for (final User user : users)
		{
			if (user.getAdministration() != null && user.getAdministration() instanceof Bureau && service != null
					&& ((Bureau) user.getAdministration()).getService().getId().equals(service.getId()))
			{
				returnedUsers.add(user);
			}
		}
		return returnedUsers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findInspectorsByService(org.guce.siat.common.model.Service)
	 */
	public List<User> findInspectorsByService(final Service service)
	{
		final List<User> users = userDao.findUsersByAuthorities(AuthorityConstants.INSPECTEUR.getCode());
		final List<User> returnedUsers = new ArrayList<User>();
		for (final User user : users)
		{
			if (user.getAdministration() != null && user.getAdministration() instanceof Bureau && service != null
					&& ((Bureau) user.getAdministration()).getService().getId().equals(service.getId()))
			{
				returnedUsers.add(user);
			}
		}
		return returnedUsers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#updateFailAttempts(org.guce.siat.common.model.User)
	 */
	@Override
	@Transactional(readOnly = false)
	@Audit(operationType = "BAD_CREDENTIALS")
	public User updateFailAttempts(final User user)
	{
		return userDao.updateFailAttempts(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findUsersByAuthorities(java.lang.String[])
	 */
	@Override
	public List<User> findUsersByAuthorities(final String... authoritiesList)
	{
		return userDao.findUsersByAuthorities(authoritiesList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.UserService#findUsersByOrganismAndAuthorities(org.guce.siat.common.model.Organism,
	 * java.lang.String[])
	 */
	@Override
	public List<User> findUsersByOrganismAndAuthorities(final Organism organism, final String... authoritiesList)
	{
		final List<Administration> subAdministrations = new ArrayList<Administration>();
		subAdministrations.add(organism);

		@SuppressWarnings("unchecked")
		final List<Long> subAdministrationIds = (List<Long>) CollectionUtils.collect(subAdministrations, new Transformer()
		{
			@Override
			public Object transform(final Object subAdministration)
			{
				return ((Administration) subAdministration).getId();
			}
		});

		return userDao.findUsersByAdministrationAndAuthorities(subAdministrationIds, authoritiesList);
	}

	/**
	 * Gets the all sub administration ids by min or org.
	 *
	 * @param administration
	 *           the administration
	 * @return the all sub administration ids by min or org
	 */
	private List<Long> getAllSubAdministrationIdsByMinOrOrg(final Administration administration)
	{
		final List<Administration> subAdministrations = new ArrayList<Administration>();

		if (administration instanceof Ministry)
		{
			subAdministrations.add(administration);
		}
		else if (administration instanceof Organism)
		{
			subAdministrations.add(administration);
			subAdministrations.addAll(((Organism) administration).getSubDepartmentsList());

			for (final SubDepartment subDep : ((Organism) administration).getSubDepartmentsList())
			{
				subAdministrations.addAll(subDep.getServicesList());

				for (final Service service : subDep.getServicesList())
				{
					subAdministrations.addAll(service.getEntityList());
				}
			}
		}

		final List<Long> subAdministrationsIds = new ArrayList<Long>();

		for (final Administration ad : subAdministrations)
		{
			subAdministrationsIds.add(ad.getId());
		}

		return subAdministrationsIds;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findUsersByAdministrationAndPositions(org.guce.siat.common.model.
	 * Administration, org.guce.siat.common.utils.enums.PositionType[])
	 */
	@Override
	public List<User> findUsersByAdministrationAndPositions(final Administration administration,
			final PositionType... positionList)
	{
		return userDao.findUsersByAdministrationsAndPositions(getAllSubAdministrationIdsByMinOrOrg(administration), positionList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.UserService#findAuthoritiesByAdministration(org.guce.siat.common.model.Administration
	 * , java.lang.String[])
	 */
	@Override
	public List<User> findUsersByAdministrationAndAuthorities(final Administration administration, final String... authorities)
	{
		final List<Administration> subAdministrations = administrationService
				.getRecursiveSubAdministrationByAdministration(administration);

		@SuppressWarnings("unchecked")
		final List<Long> subAdministrationIds = (List<Long>) CollectionUtils.collect(subAdministrations, new Transformer()
		{
			@Override
			public Object transform(final Object subAdministration)
			{
				return ((Administration) subAdministration).getId();
			}
		});

		return userDao.findUsersByAdministrationAndAuthorities(subAdministrationIds, authorities);
	}

	/**
	 * Gets the organism dao.
	 *
	 * @return the organism dao
	 */
	public OrganismDao getOrganismDao()
	{
		return organismDao;
	}


	/**
	 * Sets the organism dao.
	 *
	 * @param organismDao
	 *           the new organism dao
	 */
	public void setOrganismDao(final OrganismDao organismDao)
	{
		this.organismDao = organismDao;
	}
	
	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.UserService#findByStepAndFileTypeAndAdministration(java.lang.Long, java.lang.Long, java.util.List)
	 */
	public List<User> findByStepAndFileTypeAndAdministration(Long stepId, Long fileTypeId, List<Bureau> bureauList){
		return userDao.findByStepAndFileTypeAndAdministration(stepId, fileTypeId, bureauList);
	}
	
	
	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.UserService#findSuperUserByFileType(org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	public List<User> findSuperUserByFileType(FileTypeCode fileTypeCode, Long bureauId){
		return userDao.findSuperUserByFileType(fileTypeCode, bureauId);
	}

}
