package org.guce.siat.common.utils.ged;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
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
    public static void sendDocument(List<File> files, Session sessionCmisClient, String pathFolder) throws IOException {
        Folder folder = (Folder) sessionCmisClient.getObjectByPath(pathFolder);
        for (File file : files) {

            String contentType = Files.probeContentType(file.toPath());
            String endFile = file.getName().toUpperCase();
            if (endFile.endsWith("PDF")) {
                contentType = "application/pdf";
            }
            if (contentType == null || contentType.isEmpty()) {
                contentType = "application/octet-stream";
            }
            if (CmisClient.getDocumentByPath(sessionCmisClient, pathFolder + SPERATOR + file.getName()) != null) {
                CmisObject object = sessionCmisClient.getObjectByPath(folder.getPath() + SPERATOR + file.getName());
                Document delDoc = (Document) object;
                delDoc.delete(true);
            }
            CmisClient client = new CmisClient();
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
    public static boolean deleteDocument(List<String> files, String connectionName, String userNameRepo, String pwdRepo, String ipRepo, String urlAtomRepo, String idRepo, String pathFolder) {
        CmisClient cmisClient = new CmisClient();
        Session sessionCmisClient = cmisClient.getSession(connectionName, userNameRepo, pwdRepo, ipRepo + urlAtomRepo, idRepo);
        Folder folder = (Folder) sessionCmisClient.getObjectByPath(pathFolder);
        for (String delDocName : files) {
            try {
                LOG.error("Delete: {} {}", folder.getPath(), delDocName);
                CmisObject object = sessionCmisClient.getObjectByPath(folder.getPath() + SPERATOR + delDocName);
                Document delDoc = (Document) object;
                delDoc.delete(true);
            } catch (CmisObjectNotFoundException e) {
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
    public static boolean deleteDocument(List<String> files, Session sessionCmisClient, String pathFolder) {
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
    public static Folder createFolder(Folder target, String newFolderName) {
        try {
            final Map<String, String> props = new HashMap<>();
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
        for (CmisObject o : target.getChildren()) {
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
    public static Folder getRootFolder(Session sessionCmisClient, String pathFolder) {
        return (Folder) sessionCmisClient.getObjectByPath(pathFolder);
    }
}
