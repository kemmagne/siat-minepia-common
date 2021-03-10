/*
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.NoResultException;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.UserAuthorityFileTypeDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthorityFileType;
import org.guce.siat.common.utils.SiatUtils;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserAuthorityFileTypeDaoImpl.
 */
@Repository("userAuthorityFileTypeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UserAuthorityFileTypeDaoImpl extends AbstractJpaDaoImpl<UserAuthorityFileType> implements UserAuthorityFileTypeDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserAuthorityFileTypeDaoImpl.class);

    /**
     * Instantiates a new user authority file type dao impl.
     */
    public UserAuthorityFileTypeDaoImpl() {
        super();
        setClasse(UserAuthorityFileType.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.ct.dao.UserAuthorityFileTypeDao#getFileTypeAndAuthorityByUser(org.guce.siat.common.model.User)
     */
    @Override
    public List<UserAuthorityFileType> getFileTypeAndAuthorityByUser(final User usr) {
        try {
            if (usr != null) {
                final StringBuilder hqlBuilder = new StringBuilder();

                hqlBuilder.append("FROM UserAuthorityFileType u ");
                hqlBuilder.append("WHERE u.primaryKey.userAuthority.user.id = :userId ");

                final TypedQuery<UserAuthorityFileType> query = entityManager.createQuery(hqlBuilder.toString(),
                        UserAuthorityFileType.class);
                query.setParameter("userId", usr.getId());
                return query.getResultList();
            }
        } catch (final Exception e) {
            LOG.info(e.getMessage(), e);
            throw new DAOException(e);
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.UserAuthorityFileTypeDao#findUserAuthorityFileTypeByFileTypeAndUserList(org.guce.siat
	 * .common.model.FileType, java.util.List)
     */
    @Override
    public List<UserAuthorityFileType> findUserAuthorityFileTypeByFileTypeAndUserList(final FileType fileType,
            final List<User> userList) {
        if (!Objects.equals(fileType, null) && CollectionUtils.isNotEmpty(userList)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("FROM UserAuthorityFileType ua ");
            hqlBuilder.append("WHERE ua.primaryKey.fileType.code = :fileTypeCode ");
            hqlBuilder.append("AND ua.primaryKey.userAuthority.user.id IN (:userListIds)");

            final TypedQuery<UserAuthorityFileType> query = super.entityManager.createQuery(hqlBuilder.toString(),
                    UserAuthorityFileType.class);

            query.setParameter("fileTypeCode", fileType.getCode());
            query.setParameter("userListIds", SiatUtils.getUserIds(userList));

            return query.getResultList();
        }
        return Collections.emptyList();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.UserAuthorityFileTypeDao#findAuthoritiesByFileTypeAndUser(org.guce.siat.common.model.
	 * FileType, org.guce.siat.common.model.User)
     */
    @Override
    public List<Authority> findAuthoritiesByFileTypeAndUser(final FileType fileType, final User user) {
        if (!Objects.equals(fileType, null) && user != null) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("SELECT ua.primaryKey.userAuthority.authorityGranted ");
            hqlBuilder.append("FROM UserAuthorityFileType ua ");
            hqlBuilder.append("WHERE ua.primaryKey.fileType.code= :fileTypeCode ");
            hqlBuilder.append("AND ua.primaryKey.userAuthority.user.id= :userId ");

            final TypedQuery<Authority> query = super.entityManager.createQuery(hqlBuilder.toString(), Authority.class);

            query.setParameter("fileTypeCode", fileType.getCode());
            query.setParameter("userId", user.getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserAuthorityFileTypeDao#findUserAuthorityFileTypeByUserList(java.util.List)
     */
    @Override
    public List<UserAuthorityFileType> findUserAuthorityFileTypeByUserList(final List<User> usersList) {
        if (CollectionUtils.isNotEmpty(usersList)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("FROM UserAuthorityFileType ua ");
            hqlBuilder.append("WHERE ua.primaryKey.userAuthority.user.id IN (:userListIds)");

            final TypedQuery<UserAuthorityFileType> query = super.entityManager.createQuery(hqlBuilder.toString(),
                    UserAuthorityFileType.class);

            query.setParameter("userListIds", SiatUtils.getUserIds(usersList));
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.ct.dao.UserAuthorityFileTypeDao#findUserByFileTypeAndStepAuthorities(org.guce.siat.core.ct.
	 * model.FileType, org.guce.siat.core.ct.model.Step, java.lang.Boolean)
     */
    @Override
    public List<UserAuthorityFileType> findUserByFileTypeAndStepAuthorities(final FileType fileType, final Step toStep,
            final Boolean isStepApDecesion) {
        if (!Objects.equals(fileType, null) && !Objects.equals(toStep, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("FROM UserAuthorityFileType ua ");
            hqlBuilder.append("WHERE ua.primaryKey.fileType.code = :codeFileType ");
            hqlBuilder.append("AND ua.primaryKey.userAuthority.authorityGranted IN (:authorityList)");

            final TypedQuery<UserAuthorityFileType> query = super.entityManager.createQuery(hqlBuilder.toString(), UserAuthorityFileType.class);

            query.setParameter("codeFileType", fileType.getCode());

            if (!isStepApDecesion) {
                final List<Authority> returnedListOfAuthority = new ArrayList<>();

                for (final Authority authority : toStep.getRoleList()) {
                    if (!AuthorityConstants.RESPONSABLE_TRAITEMENT.getCode().equals(authority.getRole())) {
                        returnedListOfAuthority.add(authority);
                    }
                }
                query.setParameter("authorityList", returnedListOfAuthority);
            } else {
                query.setParameter("authorityList", toStep.getRoleList());
            }
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public UserAuthorityFileType findByCurrentStepFileAndLoggedUser(Step currentStep, File currentFile, User loggedUser) {

        TypedQuery<UserAuthorityFileType> query = super.entityManager.createQuery("SELECT uaft FROM UserAuthorityFileType uaft, StepAuthority sa WHERE uaft.primaryKey.fileType = :fileType AND uaft.primaryKey.userAuthority.user = :loggedUser AND sa.primaryKey.authorityGranted = uaft.primaryKey.userAuthority.authorityGranted AND sa.primaryKey.step = :currentStep", UserAuthorityFileType.class);

        query.setParameter("fileType", currentFile.getFileType());
        query.setParameter("currentStep", currentStep);
        query.setParameter("loggedUser", loggedUser);

        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.UserAuthorityFileTypeDao#findFilesTypesByAuthorizedUser(org.guce.siat.common.model.User)
     */
    @Override
    public List<FileType> findFilesTypesByAuthorizedUser(final User user) {
        if (!Objects.equals(user, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("SELECT DISTINCT(u.primaryKey.fileType) ");
            hqlBuilder.append("FROM UserAuthorityFileType u ");
            hqlBuilder.append("WHERE u.primaryKey.userAuthority.user.id = :userId");

            final TypedQuery<FileType> hqlQuery = entityManager.createQuery(hqlBuilder.toString(), FileType.class);
            hqlQuery.setParameter("userId", user.getId());

            return hqlQuery.getResultList();
        }
        return Collections.emptyList();
    }

}
