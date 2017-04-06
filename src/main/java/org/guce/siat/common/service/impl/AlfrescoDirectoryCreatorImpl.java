package org.guce.siat.common.service.impl;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.guce.siat.common.model.File;
import org.guce.siat.common.service.AlfrescoDirectoryCreator;
import org.guce.siat.common.utils.ged.CmisClient;
import org.guce.siat.common.utils.ged.CmisSession;
import org.guce.siat.common.utils.ged.CmisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AlfrescoDirectoryCreatorImpl.
 */
@Service("alfrescoDirectoryCreator")
@Transactional(readOnly = true)
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class AlfrescoDirectoryCreatorImpl implements AlfrescoDirectoryCreator {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AlfrescoDirectoryCreatorImpl.class);

	/**
	 * The connection name.
	 */
	@Value("${connectionName}")
	private String connectionName;

	/**
	 * The user name repo.
	 */
	@Value("${userNameRepo}")
	private String userNameRepo;

	/**
	 * The pwd repo.
	 */
	@Value("${pwdRepo}")
	private String pwdRepo;

	/**
	 * The ip repo.
	 */
	@Value("${ipRepo}")
	private String ipRepo;

	/**
	 * The url atom repo.
	 */
	@Value("${urlAtomRepo}")
	private String urlAtomRepo;

	/**
	 * The id repo.
	 */
	@Value("${idRepo}")
	private String idRepo;

	/**
	 * The app name.
	 */
	@Value("${appName}")
	private String appName;

	/**
	 * The directory.
	 */
	public static final String GED_DIRECTORY = "/siat";

	/**
	 * The Constant SLASH.
	 */
	public static final String SLASH = "/";


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoDirectoryCreator#createDirectory(org.guce.siat.common.model.File)
	 */
	@Override
	public void createDirectory(final File siatFile) {
		if (siatFile.getBureau() != null) {
			try {
				final Session sessionCmisClient = CmisSession.getInstance();
				final Folder rootFolder = CmisUtils.getRootFolder(sessionCmisClient, GED_DIRECTORY);
				Folder appFolder = CmisUtils.createFolder(rootFolder, appName);
				if (appFolder == null) {
					appFolder = CmisUtils.getRootFolder(sessionCmisClient, GED_DIRECTORY + SLASH + appName);
				}

				final StringBuilder ministryPathBuilder = new StringBuilder();
				ministryPathBuilder.append(GED_DIRECTORY + SLASH + appName);
				Folder ministryFolder = null;
				try {
					ministryPathBuilder.append(SLASH);
					ministryPathBuilder.append(siatFile.getBureau().getService().getSubDepartment().getOrganism().getMinistry()
							.getLabelFr());
					ministryFolder = CmisUtils.getRootFolder(sessionCmisClient, ministryPathBuilder.toString());
				} catch (final CmisObjectNotFoundException e) {
					ministryFolder = CmisUtils.createFolder(appFolder, siatFile.getBureau().getService().getSubDepartment()
							.getOrganism().getMinistry().getLabelFr());
				}
				final StringBuilder organismPathBuilder = new StringBuilder();
				organismPathBuilder.append(ministryPathBuilder.toString());
				Folder organismFolder = null;
				try {
					organismPathBuilder.append(SLASH);
					organismPathBuilder.append(siatFile.getBureau().getService().getSubDepartment().getOrganism().getAbreviation());
					organismFolder = CmisUtils.getRootFolder(sessionCmisClient, organismPathBuilder.toString());
				} catch (final CmisObjectNotFoundException e) {
					organismFolder = CmisUtils.createFolder(ministryFolder, siatFile.getBureau().getService().getSubDepartment()
							.getOrganism().getAbreviation());
				}
				final StringBuilder subDepartmentPathBuilder = new StringBuilder();
				subDepartmentPathBuilder.append(organismPathBuilder.toString());
				Folder subDepartmentFolder = null;

				try {
					subDepartmentPathBuilder.append(SLASH);
					subDepartmentPathBuilder.append(siatFile.getBureau().getService().getSubDepartment().getAbreviation());
					subDepartmentFolder = CmisUtils.getRootFolder(sessionCmisClient, subDepartmentPathBuilder.toString());
				} catch (final CmisObjectNotFoundException e) {
					subDepartmentFolder = CmisUtils.createFolder(organismFolder, siatFile.getBureau().getService().getSubDepartment()
							.getAbreviation());
				}

				final StringBuilder servicePathBuilder = new StringBuilder();
				servicePathBuilder.append(subDepartmentPathBuilder.toString());
				Folder serviceFolder = null;
				try {
					servicePathBuilder.append(SLASH);
					servicePathBuilder.append(siatFile.getBureau().getService().getAbreviation());
					serviceFolder = CmisUtils.getRootFolder(sessionCmisClient, servicePathBuilder.toString());
				} catch (final CmisObjectNotFoundException e) {
					serviceFolder = CmisUtils.createFolder(subDepartmentFolder, siatFile.getBureau().getService().getAbreviation());
				}
				//////////////////////////////
				final StringBuilder bureauPathBuilder = new StringBuilder();
				bureauPathBuilder.append(servicePathBuilder.toString());
				try {
					bureauPathBuilder.append(SLASH);
					bureauPathBuilder.append(siatFile.getBureau().getService().getAbreviation());
					CmisUtils.getRootFolder(sessionCmisClient, bureauPathBuilder.toString());
				} catch (final CmisObjectNotFoundException e) {
					CmisUtils.createFolder(serviceFolder, siatFile.getBureau().getService().getAbreviation());
					LOG.info("{} folder already exist", bureauPathBuilder.toString());
				}

				LOG.info("folder successfully created");

			} catch (final CmisConnectionException e) {
				LOG.error("UNABLE TO CONNECT TO ALFRESCO REPOSITORY ON " + ipRepo, e);
			}
			//		final List<Bureau> bureaux = SiatUtils.findCombinedBureausByAdministrationList(ministries);}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoDirectoryCreator#attachmentExist(java.lang.String)
	 */
	@Override
	public boolean attachmentExist(final String path) {
		final Session sessionCmisClient = CmisSession.getInstance();
		try {
			return CmisClient.getDocumentByPath(sessionCmisClient, path) != null;
		} catch (final Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoDirectoryCreator#generateAlfrescoPath(org.guce.siat.common.model.File)
	 */
	@Override
	public String generateAlfrescoPath(final File siatFile) {

		final StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(GED_DIRECTORY + SLASH + appName);
		if (siatFile.getBureau() != null) {
			pathBuilder.append(SLASH);
			pathBuilder.append(siatFile.getBureau().getService().getSubDepartment().getOrganism().getMinistry().getLabelFr());
			pathBuilder.append(SLASH);
			pathBuilder.append(siatFile.getBureau().getService().getSubDepartment().getOrganism().getAbreviation());
			pathBuilder.append(SLASH);
			pathBuilder.append(siatFile.getBureau().getService().getSubDepartment().getAbreviation());
			pathBuilder.append(SLASH);
			pathBuilder.append(siatFile.getBureau().getService().getAbreviation());
			pathBuilder.append(SLASH);
			pathBuilder.append(siatFile.getBureau().getCode());
		}
		return pathBuilder.toString();
	}

	/**
	 * Gets the connection name.
	 *
	 * @return the connection name
	 */
	public String getConnectionName() {
		return connectionName;
	}

	/**
	 * Sets the connection name.
	 *
	 * @param connectionName the new connection name
	 */
	public void setConnectionName(final String connectionName) {
		this.connectionName = connectionName;
	}

	/**
	 * Gets the user name repo.
	 *
	 * @return the user name repo
	 */
	public String getUserNameRepo() {
		return userNameRepo;
	}

	/**
	 * Sets the user name repo.
	 *
	 * @param userNameRepo the new user name repo
	 */
	public void setUserNameRepo(final String userNameRepo) {
		this.userNameRepo = userNameRepo;
	}

	/**
	 * Gets the pwd repo.
	 *
	 * @return the pwd repo
	 */
	public String getPwdRepo() {
		return pwdRepo;
	}

	/**
	 * Sets the pwd repo.
	 *
	 * @param pwdRepo the new pwd repo
	 */
	public void setPwdRepo(final String pwdRepo) {
		this.pwdRepo = pwdRepo;
	}

	/**
	 * Gets the ip repo.
	 *
	 * @return the ip repo
	 */
	public String getIpRepo() {
		return ipRepo;
	}

	/**
	 * Sets the ip repo.
	 *
	 * @param ipRepo the new ip repo
	 */
	public void setIpRepo(final String ipRepo) {
		this.ipRepo = ipRepo;
	}

	/**
	 * Gets the url atom repo.
	 *
	 * @return the url atom repo
	 */
	public String getUrlAtomRepo() {
		return urlAtomRepo;
	}

	/**
	 * Sets the url atom repo.
	 *
	 * @param urlAtomRepo the new url atom repo
	 */
	public void setUrlAtomRepo(final String urlAtomRepo) {
		this.urlAtomRepo = urlAtomRepo;
	}

	/**
	 * Gets the id repo.
	 *
	 * @return the id repo
	 */
	public String getIdRepo() {
		return idRepo;
	}

	/**
	 * Sets the id repo.
	 *
	 * @param idRepo the new id repo
	 */
	public void setIdRepo(final String idRepo) {
		this.idRepo = idRepo;
	}

	/**
	 * Gets the app name.
	 *
	 * @return the app name
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Sets the app name.
	 *
	 * @param appName the new app name
	 */
	public void setAppName(final String appName) {
		this.appName = appName;
	}

}
