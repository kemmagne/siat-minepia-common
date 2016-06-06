/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.PositionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class UserDaoImpl.
 */
@Repository("userDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDaoImpl extends AbstractJpaDaoImpl<User> implements UserDao, UserDetailsService
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	/** The encoder. */
	@Autowired
	private ShaPasswordEncoder encoder;

	/** The params dao. */
	@Autowired
	private ParamsDao paramsDao;

	/**
	 * Instantiates a new user dao impl.
	 */
	public UserDaoImpl()
	{
		super();
		setClasse(User.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
	{
		try
		{
			final User domainUser = getUserByLogin(username);
			final boolean enabled = true;
			final boolean accountNonExpired = true;
			final boolean credentialsNonExpired = true;

			final org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					domainUser.getLogin(), domainUser.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired,
					domainUser.getAccountNonLocked(), domainUser.getUserAuthorityList());
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Returned Password : {}", user.getPassword());
			}

			return user;

		}
		catch (final Exception exception)
		{
			LOG.error("Error : {}", exception.getMessage());
			throw new DAOException(exception.getMessage(), exception);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#createUser(org.guce.siat.common.model.User)
	 */
	@Transactional(readOnly = false)
	public void createUser(final User user)
	{
		final User user1 = user;
		if (LOG.isDebugEnabled())
		{
			LOG.debug("initial password : {}", user1.getPassword());
		}

		// encode password before sending to database.
		user1.setPassword(encoder.encodePassword(user.getPassword(), user.getLogin()));
		if (LOG.isDebugEnabled())
		{
			LOG.debug("encrypted password : {}", user1.getPassword());
		}

		save(user1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#updateUser(org.guce.siat.common.model.User)
	 */
	@Transactional(readOnly = false)
	public void updateUser(final User user)
	{
		final User user1 = user;

		if (LOG.isDebugEnabled())
		{
			LOG.debug("initial password : {}", user1.getPassword());
		}

		// encode password before sending to database.
		user1.setPassword(encoder.encodePassword(user.getPassword(), user.getLogin()));

		if (LOG.isDebugEnabled())
		{
			LOG.debug("encrypted password : {}", user1.getPassword());
		}
		update(user1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#getUserByLogin(java.lang.String)
	 */
	@Override
	public User getUserByLogin(final String username)
	{
		try
		{
			final String qlString = "SELECT u FROM User u WHERE u.login = :login AND u.deleted = false AND u.enabled = true";
			final TypedQuery<User> query = super.entityManager.createQuery(qlString, User.class);
			query.setParameter("login", username);

			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#findByMail(java.lang.String)
	 */
	@Override
	public User findByMail(final String email)
	{
		try
		{
			final String qlString = "SELECT u FROM User u WHERE u.email = :email";
			final TypedQuery<User> query = super.entityManager.createQuery(qlString, User.class);
			query.setParameter("email", email);

			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#updateFailAttempts(org.guce.siat.common.model.User)
	 */
	@Override
	public User updateFailAttempts(final User user)
	{

		// retrieve the max number of failed connection before locking the user account
		Integer maxAttempts;
		final Params maxAttemptsUserConnexion = paramsDao.findParamsByName("MaxAttemptsUserConnexion");
		try
		{
			maxAttempts = Integer.parseInt(maxAttemptsUserConnexion.getValue());
		}
		catch (final NumberFormatException nfe)
		{
			LOG.info(Objects.toString(nfe));
			maxAttempts = Constants.THREE;
		}

		if (user.getAttempts() < maxAttempts - 1)
		{
			user.setAttempts(user.getAttempts() + 1);
			user.setLastAttemptsTime(new Date());
			update(user);
		}
		else
		{
			user.setAttempts(user.getAttempts() + 1);
			user.setAccountNonLocked(false);
			user.setLastAttemptsTime(new Date());
			update(user);
		}

		return user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#findUsersByAuthorities(java.lang.String[])
	 */
	@Override
	public List<User> findUsersByAuthorities(final String... authoritiesList)
	{
		final StringBuilder hqlQuery = new StringBuilder();

		hqlQuery.append("SELECT DISTINCT u FROM User u FULL JOIN u.userAuthorityList aut ");
		hqlQuery.append("WHERE aut.authorityGranted.role IN (:authoritiesList) ");
		hqlQuery.append("AND u.deleted = false ");

		final TypedQuery<User> query = super.entityManager.createQuery(hqlQuery.toString(), User.class);
		query.setParameter("authoritiesList", Arrays.asList(authoritiesList));

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#findUsersByAdministrationsAndPositions(java.util.List,
	 * org.guce.siat.common.utils.enums.PositionType[])
	 */
	@Override
	public List<User> findUsersByAdministrationsAndPositions(final List<Long> administrationIds,
			final PositionType... positionList)
	{
		if (CollectionUtils.isNotEmpty(administrationIds))
		{
			final StringBuilder hqlQuery = new StringBuilder();

			hqlQuery.append("FROM User u ");
			hqlQuery.append("WHERE u.position IN (:listPostes) ");
			hqlQuery.append("AND u.administration.id IN (:listAdministrationIds) ");
			hqlQuery.append("ORDER BY u.id");

			final TypedQuery<User> query = super.entityManager.createQuery(hqlQuery.toString(), User.class);

			query.setParameter("listPostes", Arrays.asList(positionList));
			query.setParameter("listAdministrationIds", administrationIds);

			return query.getResultList();
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#findUsersByAdministrationIds(java.lang.Long[])
	 */
	@Override
	public List<User> findUsersByAdministrationsIds(final Long... administrationIds)
	{
		if (CollectionUtils.isNotEmpty(Arrays.asList(administrationIds)))
		{
			final StringBuilder hqlQuery = new StringBuilder();

			hqlQuery.append("SELECT u FROM User u  ");
			hqlQuery.append("WHERE u.administration.id IN (:administrationIds)");
			final TypedQuery<User> query = super.entityManager.createQuery(hqlQuery.toString(), User.class);
			query.setParameter("administrationIds", Arrays.asList(administrationIds));
			return query.getResultList();
		}
		return Collections.emptyList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.UserDao#findUsersByAdministrationAndPositions(org.guce.siat.common.model.Administration,
	 * org.guce.siat.common.utils.enums.PositionType[])
	 */
	@Override
	public List<User> findUsersByAdministrationsAndPositions(final Administration administration,
			final PositionType... positionList)
	{
		StringBuilder hqlQuery;
		TypedQuery<User> query;
		final List<User> usersList = new ArrayList<User>();


		if (administration instanceof Organism)
		{
			//Select users attached to the organism
			hqlQuery = new StringBuilder();
			hqlQuery.append("SELECT u FROM User u JOIN TREAT(u.administration AS Organism) o WHERE o.id = :administrationId ");
			hqlQuery.append("AND u.position IN (:listPostes) ");
			hqlQuery.append("ORDER BY u.id");
			query = super.entityManager.createQuery(hqlQuery.toString(), User.class);

			query.setParameter("listPostes", Arrays.asList(positionList));
			query.setParameter("administrationId", administration.getId());
			usersList.addAll(query.getResultList());

			//Select users attached to the organism subDepertments
			hqlQuery = new StringBuilder();
			hqlQuery
					.append("SELECT u FROM User u JOIN CAST(u.administration, SubDepartment) s WHERE s.organism.id = :administrationId ");
			hqlQuery.append("AND u.position IN (:listPostes) ");
			hqlQuery.append("ORDER BY u.id");
			query = super.entityManager.createQuery(hqlQuery.toString(), User.class);

			query.setParameter("listPostes", Arrays.asList(positionList));
			query.setParameter("administrationId", administration.getId());
			usersList.addAll(query.getResultList());


			//Select users attached to the organism servicves
			hqlQuery = new StringBuilder();
			hqlQuery
					.append("SELECT u FROM User u JOIN CAST(u.administration, Service) s WHERE s.subDepartment.organism.id = :administrationId ");
			hqlQuery.append("AND u.position IN (:listPostes) ");
			hqlQuery.append("ORDER BY u.id");
			query = super.entityManager.createQuery(hqlQuery.toString(), User.class);

			query.setParameter("listPostes", Arrays.asList(positionList));
			query.setParameter("administrationId", administration.getId());
			usersList.addAll(query.getResultList());


			//Select users attached to the bureau
			hqlQuery = new StringBuilder();
			hqlQuery
					.append("SELECT u FROM User u JOIN CAST(u.administration, Entity) e WHERE e.service.subDepartment.organism.id = :administrationId ");
			hqlQuery.append("AND u.position IN (:listPostes) ");
			hqlQuery.append("ORDER BY u.id");
			query = super.entityManager.createQuery(hqlQuery.toString(), User.class);

			query.setParameter("listPostes", Arrays.asList(positionList));
			query.setParameter("administrationId", administration.getId());
			usersList.addAll(query.getResultList());
		}

		return usersList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#removeAllGrantedAuthorities(org.guce.siat.common.model.User)
	 */
	@Override
	public void removeAllGrantedAuthorities(final User user)
	{
		try
		{
			final StringBuilder builder = new StringBuilder();
			builder.append("DELETE FROM UserAuthority WHERE user.id=:userId");
			entityManager.createQuery(builder.toString()).setParameter("userId", user.getId()).executeUpdate();
		}
		catch (final Exception e)
		{
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.UserDao#findUsersByAdministrationsAndAuthorities(org.guce.siat.common.model.Administration
	 * , org.guce.siat.common.utils.enums.AuthorityConstants[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUsersByAdministrationAndAuthorities(final List<Long> administrationIds, final String... authorities)
	{
		final StringBuilder hqlQuery = new StringBuilder();
		final Map<String, Object> params = new HashedMap();

		hqlQuery.append("SELECT DISTINCT u FROM User u INNER JOIN u.userAuthorityList aut ");
		hqlQuery.append("WHERE u.deleted = false ");
		if (CollectionUtils.isNotEmpty(Arrays.asList(authorities)))
		{
			hqlQuery.append("AND aut.authorityGranted.role IN (:authorities) ");
			params.put("authorities", Arrays.asList(authorities));
		}
		if (CollectionUtils.isNotEmpty(administrationIds))
		{
			hqlQuery.append("AND u.administration.id IN (:administrationIds) ");
			params.put("administrationIds", administrationIds);
		}

		final TypedQuery<User> query = super.entityManager.createQuery(hqlQuery.toString(), User.class);

		for (final Entry<String, Object> entry : params.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#findByStepAndFileTypeAndAdministration(java.lang.Long, java.lang.Long,
	 * java.util.List)
	 */
	public List<User> findByStepAndFileTypeAndAdministration(final Long stepId, final Long fileTypeId,
			final List<Bureau> bureauList)
	{

		List<User> users = null;

		final List<Long> bureauIdList = new ArrayList<Long>();
		for (final Bureau bureau : bureauList)
		{
			bureauIdList.add(bureau.getId());
		}

		final StringBuilder sqlQuery = new StringBuilder();

		sqlQuery
				.append("SELECT usrs.PREFERED_LANGUAGE, usrs.EMAIL, usrs.FIRST_NAME FROM user_authority ua, step_authority sa, file_type_step ftp, USERS usrs ");
		sqlQuery.append("WHERE ua.AUTHORITY_ID = sa.AUTHORITY_ID ");
		sqlQuery.append("AND sa.STEP_ID = ftp.STEP_ID ");
		sqlQuery.append("AND usrs.ID = ua.USER_ID AND usrs.ADMINISTRATION_ID IN (:bureauList) ");
		sqlQuery.append("AND ftp.STEP_ID = :stepId ");
		sqlQuery.append("AND ftp.FILE_TYPE_ID = :fileTypeId ");

		final Query query = super.entityManager.createNativeQuery(sqlQuery.toString());
		query.setParameter("stepId", stepId);
		query.setParameter("fileTypeId", fileTypeId);
		query.setParameter("bureauList", bureauIdList);

		@SuppressWarnings("unchecked")
		final List<Object[]> resultList = query.getResultList();
		users = new ArrayList<User>();
		for (final Object[] usr : resultList)
		{
			final User user = new User();
			user.setPreferedLanguage(String.valueOf(usr[0]));
			user.setEmail(String.valueOf(usr[1]));
			user.setFirstName(String.valueOf(usr[2]));
			users.add(user);
		}

		return users;

	}
	
	/* (non-Javadoc)
	 * @see org.guce.siat.common.dao.UserDao#findSuperUserByFileType(org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	public List<User> findSuperUserByFileType(FileTypeCode fileTypeCode, Long bureauId){
		
		List<User> users = null;
		
		final StringBuilder sqlQuery = new StringBuilder();

		sqlQuery.append("select usrs.EMAIL, usrs.FIRST_NAME, usrs.ADMINISTRATION_ID, usrs.PREFERED_LANGUAGE ");
		sqlQuery.append(" from users usrs, user_authority ua, AUTHORITY au , USER_AUTHORIRTY_FILE_TYPE uaft, file_type ft");
		sqlQuery.append(" where au.ROLE = 'SUPER' ");
		sqlQuery.append(" and ua.AUTHORITY_ID = au.ID");
		sqlQuery.append(" and uaft.USER_AUTHORITY_ID = ua.ID");
		sqlQuery.append(" and uaft.FILE_TYPE_ID = ft.ID");
		sqlQuery.append(" and ua.USER_ID = usrs.ID");
		sqlQuery.append(" and ft.CODE = :fileTypeCode ");
		sqlQuery.append(" and  usrs.ADMINISTRATION_ID in (select ORGANISM.id as organism");
		sqlQuery.append(" from entity, SERVICE, SUB_DEPARTMENT, ORGANISM");
		sqlQuery.append(" where entity.id= :bureauId ");
		sqlQuery.append(" and service.id=entity.service_id");
		sqlQuery.append(" and SERVICE.SUB_DEPARTMENT_ID=SUB_DEPARTMENT.id ");
		sqlQuery.append(" and ORGANISM.id= SUB_DEPARTMENT.ORGANISM_ID)");
		
		final Query query = super.entityManager.createNativeQuery(sqlQuery.toString());
		query.setParameter("fileTypeCode", fileTypeCode.name());
		query.setParameter("bureauId", bureauId);
		
		@SuppressWarnings("unchecked")
		final List<Object[]> resultList = query.getResultList();
		users = new ArrayList<User>();
		for (final Object[] usr : resultList)
		{
			final User user = new User();
			user.setEmail(String.valueOf(usr[0]));
			user.setFirstName(String.valueOf(usr[1]));
			user.setAdministration(new Administration(Long.valueOf(usr[2]+"")));
			user.setPreferedLanguage(String.valueOf(usr[3]));
			users.add(user);
		}

		return users;
	}
}
