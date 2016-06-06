package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileTypeStepDao;
import org.guce.siat.common.dao.UserAuthorityDao;
import org.guce.siat.common.dao.UserAuthorityFileTypeDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.model.UserAuthorityFileType;
import org.guce.siat.common.service.UserAuthorityFileTypeService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.BureauType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserAuthorityFileTypeServiceImpl.
 */
@Service("userAuthorityFileTypeService")
@Transactional(readOnly = true)
public class UserAuthorityFileTypeServiceImpl extends
		AbstractServiceImpl<UserAuthorityFileType> implements
		UserAuthorityFileTypeService {

	/** The authority file type dao. */
	@Autowired
	private UserAuthorityFileTypeDao userAuthorityFileTypeDao;

	/** The user authority dao. */
	@Autowired
	private UserAuthorityDao userAuthorityDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/** The file type step dao. */
	@Autowired
	FileTypeStepDao fileTypeStepDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<UserAuthorityFileType> getJpaDao() {
		return userAuthorityFileTypeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce
	 * .siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<UserAuthorityFileType> jpaDao) {
		this.userAuthorityFileTypeDao = (UserAuthorityFileTypeDao) jpaDao;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * getFileTypeAndAuthorityByUser(org.guce.siat.common. model.User)
	 */
	@Override
	public List<UserAuthorityFileType> getFileTypeAndAuthorityByUser(
			final User usr) {
		try {
			return userAuthorityFileTypeDao.getFileTypeAndAuthorityByUser(usr);
		} catch (final DAOException e) {
			throw new BusinessException(e,
					BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * updateListUserAuthorityFileType(org.guce.siat.common .model.User,
	 * java.util.List)
	 */
	@Override
	public void updateListUserAuthorityFileType(final User user,
			final List<UserAuthorityFileType> userAuthFileTypes) {
		try {
			final List<UserAuthorityFileType> authorityFileTypes = userAuthorityFileTypeDao
					.getFileTypeAndAuthorityByUser(user);
			final List<UserAuthority> userAuthorities = new ArrayList<UserAuthority>();
			userDao.updateUser(user);
			if (CollectionUtils.isNotEmpty(authorityFileTypes)) {
				for (final UserAuthorityFileType userAuthorityFileType : authorityFileTypes) {
					userAuthorityFileTypeDao.delete(userAuthorityFileType);
				}
			}
			if (CollectionUtils.isNotEmpty(userAuthFileTypes)) {
				for (final UserAuthorityFileType userAuthorityFileType : userAuthFileTypes) {

					final UserAuthority userAuthority = userAuthorityDao
							.findByUserAndAuthoriy(user, userAuthorityFileType
									.getUserAuthority().getAuthorityGranted());
					if (userAuthority != null) {
						userAuthorityFileType.setUserAuthority(userAuthority);
						userAuthorities.add(userAuthority);
					} else {
						userAuthorityDao.save(userAuthorityFileType
								.getUserAuthority());
						userAuthorities.add(userAuthorityFileType
								.getUserAuthority());
					}
					userAuthorityFileTypeDao.save(userAuthorityFileType);
				}
				userAuthorityDao.removeUnusedAuthrities(user, userAuthorities);
			}
		} catch (final DAOException e) {
			throw new BusinessException(e,
					BusinessExceptionSeverity.SEVERITY_ERROR);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * saveListUserAuthorityFileType(java.util.List)
	 */
	@Override
	public void saveListUserAuthorityFileType(
			final List<UserAuthorityFileType> userAuthorityFileTypes,
			final User user) {
		try {
			final List<UserAuthority> userAuthorities = user
					.getUserAuthorityList();
			userDao.createUser(user);
			for (final UserAuthority userAuthority : userAuthorities) {
				userAuthorityDao.save(userAuthority);
			}
			if (CollectionUtils.isNotEmpty(userAuthorityFileTypes)) {
				for (final UserAuthorityFileType userAuthorityFileType : userAuthorityFileTypes) {
					final UserAuthority userAuthority = userAuthorityDao
							.findByUserAndAuthoriy(user, userAuthorityFileType
									.getUserAuthority().getAuthorityGranted());
					if (userAuthority != null) {
						userAuthorityFileType.setUserAuthority(userAuthority);
					}
					userAuthorityFileTypeDao.save(userAuthorityFileType);
				}

			}
		} catch (final DAOException e) {
			throw new BusinessException(e,
					BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.UserAuthorityFileTypeService#
	 * findUserAuthorityFileTypeByFileTypeAndUserList(org.guce
	 * .siat.common.model.FileType, java.util.List)
	 */
	@Override
	public List<UserAuthorityFileType> findUserAuthorityFileTypeByFileTypeAndUserList(
			final FileType fileType, final List<User> userList) {
		return userAuthorityFileTypeDao
				.findUserAuthorityFileTypeByFileTypeAndUserList(fileType,
						userList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.UserAuthorityFileTypeService#
	 * findAuthoritiesByFileTypeAndUser(org.guce.siat.common .model.FileType,
	 * org.guce.siat.common.model.User)
	 */
	@Override
	public List<Authority> findAuthoritiesByFileTypeAndUser(
			final FileType fileType, final User user) {
		return userAuthorityFileTypeDao.findAuthoritiesByFileTypeAndUser(
				fileType, user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.UserAuthorityFileTypeService#
	 * findUserAuthorityFileTypeByUserList(java.util.List)
	 */
	@Override
	public List<UserAuthorityFileType> findUserAuthorityFileTypeByUserList(
			final List<User> usersList) {
		return userAuthorityFileTypeDao
				.findUserAuthorityFileTypeByUserList(usersList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * findUserByFileTypeAndStepAuthorities(org.guce.siat.
	 * core.ct.model.FileType, org.guce.siat.core.ct.model.Step,
	 * org.guce.siat.core.ct.model.File)
	 */
	@Override
	public List<User> findUserByFileTypeAndStepAuthorities(
			final FileType fileType, final Step toStep, final File file) {
		final List<User> returnedList = new ArrayList<User>();

		final List<User> userByFtStAut = new ArrayList<User>();

		final Boolean isStepApDecision = fileTypeStepDao
				.isApDecisionByFileTypeAndStep(fileType, toStep);

		List<UserAuthorityFileType> userAuthorities = userAuthorityFileTypeDao
				.findUserByFileTypeAndStepAuthorities(fileType, toStep,
						isStepApDecision);

		for (final UserAuthorityFileType authorityFileType : userAuthorities) {
			userByFtStAut.add(authorityFileType.getUserAuthority().getUser());
		}

		for (final User user : userByFtStAut) {
			if (user.getAdministration() instanceof Ministry) {
				if (user.getAdministration()
						.getId()
						.equals(file.getBureau().getService()
								.getSubDepartment().getOrganism().getMinistry()
								.getId())) {
					returnedList.add(user);
				}
			} else if (user.getAdministration() instanceof Organism) {
				if (user.getAdministration()
						.getId()
						.equals(file.getBureau().getService()
								.getSubDepartment().getOrganism().getId())) {
					returnedList.add(user);
				}
			} else if (user.getAdministration() instanceof SubDepartment) {
				if (user.getAdministration()
						.getId()
						.equals(file.getBureau().getService()
								.getSubDepartment().getId())) {
					returnedList.add(user);
				}
			} else if (user.getAdministration() instanceof org.guce.siat.common.model.Service) {
				if (user.getAdministration().getId()
						.equals(file.getBureau().getService().getId())) {
					returnedList.add(user);
				}
			} else if (user.getAdministration() instanceof Bureau
					&& (((Bureau) user.getAdministration()).getService()
							.getId()
							.equals(file.getBureau().getService().getId())
							&& (((Bureau) user.getAdministration())
									.getBureauType()
									.equals(BureauType.BUREAU_CENTRAL)) || ((((Bureau) user
							.getAdministration()).getId().equals(file
							.getBureau().getId())))
							&& ((Bureau) user.getAdministration())
									.getBureauType().equals(
											BureauType.BUREAU_REGIONAL))) {
				returnedList.add(user);
			}
		}

		return returnedList;

	}

}
