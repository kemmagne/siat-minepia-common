package org.guce.siat.common.utils.ged;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CmisUtils.
 */
public final class CmisUtils {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CmisUtils.class);

	/**
	 * The Constant SPERATOR.
	 */
	private static final String SPERATOR = "/";

	/**
	 * Send document.
	 *
	 * @param files the files
	 * @param sessionCmisClient the session cmis client
	 * @param pathFolder the path folder
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void sendDocument(final List<File> files, final Session sessionCmisClient, final String pathFolder)
			throws IOException {

		final Folder folder = (Folder) sessionCmisClient.getObjectByPath(pathFolder);
		for (final File file : files) {

			String contentType = Files.probeContentType(file.toPath());
			final String endFile = file.getName().toUpperCase();
			if (endFile.endsWith("PDF")) {
				contentType = "application/pdf";
			}
			if (contentType == null || contentType.isEmpty()) {
				contentType = "application/octet-stream";
			}
			if (CmisClient.getDocumentByPath(sessionCmisClient, pathFolder + SPERATOR + file.getName()) != null) {
				final CmisObject object = sessionCmisClient.getObjectByPath(folder.getPath() + SPERATOR + file.getName());
				final Document delDoc = (Document) object;
				delDoc.delete(true);
			}
			final CmisClient client = new CmisClient();
			client.createDocument(sessionCmisClient, folder, file, contentType);
		}
	}

	/**
	 * Delete document.
	 *
	 * @param files the files
	 * @param connectionName the connection name
	 * @param userNameRepo the user name repo
	 * @param pwdRepo the pwd repo
	 * @param ipRepo the ip repo
	 * @param urlAtomRepo the url atom repo
	 * @param idRepo the id repo
	 * @param pathFolder the path folder
	 * @return true, if successful
	 */
	public static boolean deleteDocument(final List<String> files, final String connectionName, final String userNameRepo,
			final String pwdRepo, final String ipRepo, final String urlAtomRepo, final String idRepo, final String pathFolder) {
		final CmisClient cmisClient = new CmisClient();
		final Session sessionCmisClient = cmisClient
				.getSession(connectionName, userNameRepo, pwdRepo, ipRepo + urlAtomRepo, idRepo);
		final Folder folder = (Folder) sessionCmisClient.getObjectByPath(pathFolder);
		for (final String delDocName : files) {
			try {
				LOG.error("Delete: {} {}", folder.getPath(), delDocName);
				final CmisObject object = sessionCmisClient.getObjectByPath(folder.getPath() + SPERATOR + delDocName);
				final Document delDoc = (Document) object;
				delDoc.delete(true);
			} catch (final CmisObjectNotFoundException e) {
				LOG.error("Document is not found: " + delDocName, e);
				return false;
			}
		}
		return true;

	}

	/**
	 * Delete document.
	 *
	 * @param files the files
	 * @param sessionCmisClient the session cmis client
	 * @param pathFolder the path folder
	 * @return true, if successful
	 */
	public static boolean deleteDocument(final List<String> files, final Session sessionCmisClient, final String pathFolder) {
		final Folder folder = (Folder) sessionCmisClient.getObjectByPath(pathFolder);
		for (final String delDocName : files) {
			try {
				final CmisObject object = sessionCmisClient.getObjectByPath(folder.getPath() + SPERATOR + delDocName);
				final Document delDoc = (Document) object;
				delDoc.delete(true);
			} catch (final CmisConnectionException | CmisObjectNotFoundException e) {
				LOG.error("Document is not found: " + delDocName, e);
				return false;
			}
		}
		return true;

	}

	/**
	 * Create test folder directly under target folder.
	 *
	 * @param target the target
	 * @param newFolderName the new folder name
	 * @return newly created folder
	 */
	public static Folder createFolder(final Folder target, final String newFolderName) {
		try {
			final Map<String, String> props = new HashMap<String, String>();
			props.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
			props.put(PropertyIds.NAME, newFolderName);
			final Folder newFolder = target.createFolder(props);
			return newFolder;
		} catch (final CmisContentAlreadyExistsException e) {
			LOG.info(newFolderName + " already exist ", e);
			return null;
		}
	}

	/**
	 * List folder.
	 *
	 * @param target the target
	 */
	public static void listFolder(final Folder target) {
		for (final Iterator<CmisObject> it = target.getChildren().iterator(); it.hasNext();) {
			final CmisObject o = it.next();
			if (BaseTypeId.CMIS_DOCUMENT.equals(o.getBaseTypeId())) {
				LOG.info("[Docment] {}", o.getName());
			} else if (BaseTypeId.CMIS_FOLDER.equals(o.getBaseTypeId())) {
				LOG.info("[Folder] {} ", o.getName());
				listFolder((Folder) o);
			}
		}
	}

	/**
	 * Gets the root folder.
	 *
	 * @param sessionCmisClient the session cmis client
	 * @param pathFolder the path folder
	 * @return the root folder
	 */
	public static Folder getRootFolder(final Session sessionCmisClient, final String pathFolder) {

		return (Folder) sessionCmisClient.getObjectByPath(pathFolder);
	}
}
