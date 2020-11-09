package org.guce.siat.common.utils.ged;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.RepositoryCapabilities;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisUnauthorizedException;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CMIS Client Utilities for creating attached documents.
 */
public class CmisClient {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CmisClient.class);

    /**
     * The connections.
     */
    private static final Map<String, Session> CONNECTIONS = new ConcurrentHashMap<>();

    /**
     * Instantiates a new cmis client.
     */
    public CmisClient() {
    }

    /**
     * Gets the session.
     *
     * @param connectionName the connection name
     * @param username the username
     * @param pwd the pwd
     * @param urlRepo the url repo
     * @param idRepo the id repo
     * @return the session
     */
    public Session getSession(final String connectionName, final String username, final String pwd, final String urlRepo,
            final String idRepo) {
        Session session = CONNECTIONS.get(connectionName);
        if (session == null) {
            LOGGER.info("Not connected, creating new connection to" + " Alfresco with the connection id (" + connectionName + ")");
            // No connection to Alfresco available, create a new one
            final SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
            final Map<String, String> parameters = new HashMap<>();
            parameters.put(SessionParameter.USER, username);
            parameters.put(SessionParameter.PASSWORD, pwd);
            parameters.put(SessionParameter.ATOMPUB_URL, urlRepo);
            parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
            parameters.put(SessionParameter.COMPRESSION, "true");
            parameters.put(SessionParameter.CACHE_TTL_OBJECTS, "0");
            parameters.put(SessionParameter.REPOSITORY_ID, idRepo);
            session = sessionFactory.createSession(parameters);
            // Save connection for reuse
            CONNECTIONS.put(connectionName, session);
        } else {
            LOGGER.info("Already connected to Alfresco with the " + "connection id (" + connectionName + ")");
        }
        return session;
    }

    /**
     * List repo capabilities.
     *
     * @param repositoryInfo the repository info
     */
    public void listRepoCapabilities(final RepositoryInfo repositoryInfo) {
        final RepositoryCapabilities repoCapabilities = repositoryInfo.getCapabilities();
        LOGGER.info("aclCapability = " + repoCapabilities.getAclCapability().name());
        LOGGER.info("changesCapability = " + repoCapabilities.getChangesCapability().name());
        LOGGER.info("contentStreamUpdatable = " + repoCapabilities.getContentStreamUpdatesCapability().name());
        LOGGER.info("joinCapability = " + repoCapabilities.getJoinCapability().name());
        LOGGER.info("queryCapability = " + repoCapabilities.getQueryCapability().name());
        LOGGER.info("renditionCapability = " + repoCapabilities.getRenditionsCapability().name());
        LOGGER.info("allVersionsSearchable? = " + repoCapabilities.isAllVersionsSearchableSupported());
        LOGGER.info("getDescendantSupported? = " + repoCapabilities.isGetDescendantsSupported());
        LOGGER.info("getFolderTreeSupported? = " + repoCapabilities.isGetFolderTreeSupported());
        LOGGER.info("multiFilingSupported? = " + repoCapabilities.isMultifilingSupported());
        LOGGER.info("privateWorkingCopySearchable? = " + repoCapabilities.isPwcSearchableSupported());
        LOGGER.info("pwcUpdateable? = " + repoCapabilities.isPwcUpdatableSupported());
        LOGGER.info("unfilingSupported? = " + repoCapabilities.isUnfilingSupported());
        LOGGER.info("versionSpecificFilingSupported? = " + repoCapabilities.isVersionSpecificFilingSupported());
    }

    /**
     * List top folder.
     *
     * @param session the session
     */
    public void listTopFolder(final Session session) {
        final Folder root = session.getRootFolder();
        final ItemIterable<CmisObject> contentItems = root.getChildren();
        for (final CmisObject contentItem : contentItems) {
            listProperties(contentItem);
            if (contentItem instanceof Document) {
                final Document docMetadata = (Document) contentItem;
                final ContentStream docContent = docMetadata.getContentStream();
                LOGGER.info(docMetadata.getName() + " [size=" + docContent.getLength() + "][Mimetype=" + docContent.getMimeType()
                        + "][type=" + docMetadata.getType().getDisplayName() + "]");
            } else {
                LOGGER.info(contentItem.getName() + "[type=" + contentItem.getType().getDisplayName() + "]");
            }
        }
    }

    /**
     * List properties.
     *
     * @param cmisObject the cmis object
     */
    private void listProperties(final CmisObject cmisObject) {
        for (final Property<?> p : cmisObject.getProperties()) {
            if (PropertyType.DATETIME == p.getType()) {
                final Calendar calValue = (Calendar) p.getValue();
                LOGGER.info(" - "
                        + p.getId()
                        + " = "
                        + (calValue != null ? new SimpleDateFormat(DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS_Z).format(calValue.getTime())
                                : StringUtils.EMPTY));
            } else {
                LOGGER.info(" - " + p.getId() + " = " + p.getValue());
            }
        }
    }

    /**
     * List top folder with paging and prop filter.
     *
     * @param session the session
     */
    public void listTopFolderWithPagingAndPropFilter(final Session session) {
        final Folder root = session.getRootFolder();
        final OperationContext operationContext = new OperationContextImpl();
        final Set<String> propertyFilter = new HashSet<>();
        propertyFilter.add(PropertyIds.CREATED_BY);
        propertyFilter.add(PropertyIds.NAME);
        operationContext.setFilter(propertyFilter);

        final int maxItemsPerPage = Constants.FIVE;
        operationContext.setMaxItemsPerPage(maxItemsPerPage);
        final ItemIterable<CmisObject> contentItems = root.getChildren(operationContext);
        final long numerOfPages = Math.abs(contentItems.getTotalNumItems() / maxItemsPerPage);
        int pageNumber = 1;
        boolean finishedPaging = false;
        int count = 0;
        while (!finishedPaging) {
            LOGGER.info("Page " + pageNumber + " (" + numerOfPages + ")");
            final ItemIterable<CmisObject> currentPage = contentItems.skipTo(count).getPage();
            for (final CmisObject contentItem : currentPage) {
                LOGGER.info(contentItem.getName() + " [type=" + contentItem.getType().getDisplayName() + "]");
                count++;
            }
            pageNumber++;
            if (!currentPage.getHasMoreItems()) {
                finishedPaging = true;
            }
        }
    }

    /**
     * Gets the object.
     *
     * @param session the session
     * @param parentFolder the parent folder
     * @param objectName the object name
     * @return the object
     */
    private CmisObject getObject(final Session session, final Folder parentFolder, final String objectName) {
        CmisObject object = null;
        try {
            String path2Object = parentFolder.getPath();
            if (!path2Object.endsWith("/")) {
                path2Object += "/";
            }
            path2Object += objectName;
            object = session.getObjectByPath(path2Object);
        } catch (final CmisObjectNotFoundException nfe0) {
            LOGGER.info(nfe0.getMessage(), nfe0);
        }
        return object;
    }

    /**
     * Date2 string.
     *
     * @param date the date
     * @return the string
     */
    private String date2String(final Date date) {
        return new SimpleDateFormat(DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS_Z).format(date);
    }

    /**
     * Creates the structure.
     *
     * @param session the session
     * @param folderName the folder name
     * @param listFolders the list folders
     */
    public void createStructure(final Session session, final String folderName, final Map<String, List<String>> listFolders) {

        final Folder parentFolder = createFolder(session, null, folderName);
        for (final String key : listFolders.keySet()) {
            final Folder folder = createFolder(session, parentFolder, key);
            for (final Iterator<String> iterator = listFolders.get(key).iterator(); iterator.hasNext();) {
                final String service = iterator.next();
                createFolder(session, folder, service);
            }
        }
    }

    /**
     * Creates the folder.
     *
     * @param session the session
     * @param parentFolder the parent folder
     * @param folderName the folder name
     * @return the folder
     */
    public Folder createFolder(final Session session, final Folder parentFolder, final String folderName) {
        Folder pFolder = parentFolder;
        if (pFolder == null) {
            pFolder = session.getRootFolder();
        }
        // Make sure the user is allowed to create a folder
        // under the root folder
        if (!pFolder.getAllowableActions().getAllowableActions().contains(Action.CAN_CREATE_FOLDER)) {
            throw new CmisUnauthorizedException("Current user does not have permission to create a " + "sub-folder in "
                    + pFolder.getPath());
        }
        // Check if folder already exist, if not create it
        Folder newFolder = (Folder) getObject(session, pFolder, folderName);
        if (newFolder == null) {
            final Map<String, Object> newFolderProps = new HashMap<>();
            newFolderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
            newFolderProps.put(PropertyIds.NAME, folderName);
            newFolder = pFolder.createFolder(newFolderProps);
            LOGGER.info("Created new folder: " + newFolder.getPath() + " [creator=" + newFolder.getCreatedBy() + "][created="
                    + date2String(newFolder.getCreationDate().getTime()) + "]");
        } else {
            LOGGER.info("Folder already exist: " + newFolder.getPath());
        }
        return newFolder;
    }

    /**
     * Creates the folder with custom type.
     *
     * @param session the session
     */
    public void createFolderWithCustomType(final Session session) {
        final String folderName = "OpenCMISTest2";
        final Folder parentFolder = session.getRootFolder();
        // Check if folder already exist, if not create it
        Folder newFolder = (Folder) getObject(session, parentFolder, folderName);
        if (newFolder == null) {
            final Map<String, Object> newFolderProps = new HashMap<>();
            newFolderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
            newFolderProps.put(PropertyIds.NAME, folderName);
            newFolderProps.put(PropertyIds.BASE_TYPE_ID, "PROJ001");
            newFolder = parentFolder.createFolder(newFolderProps);
            LOGGER.info("Created new folder: " + newFolder.getPath() + " [creator=" + newFolder.getCreatedBy() + "][created="
                    + date2String(newFolder.getCreationDate().getTime()) + "]");
        } else {
            LOGGER.info("Folder already exist: " + newFolder.getPath());
        }
    }

    /**
     * Creates the document.
     *
     * @param session the session
     * @param parentFolder the parent folder
     * @param file the file
     * @param fileType the file type
     * @return the document
     */
    public Document createDocument(final Session session, final Folder parentFolder, final File file, final String fileType) {
        final String documentName = file.getName();
        // Make sure the user is allowed to create a document
        // in the passed in folder
        if (!parentFolder.getAllowableActions().getAllowableActions().contains(Action.CAN_CREATE_DOCUMENT)) {
            throw new CmisUnauthorizedException("Current user does not " + "have permission to create a document in "
                    + parentFolder.getPath());
        }
        // Check if document already exist, if not create it
        Document newDocument = (Document) getObject(session, parentFolder, documentName);
        if (newDocument == null) {
            // Setup document metadata
            final Map<String, Object> newDocumentProps = new HashMap<>();
            newDocumentProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
            newDocumentProps.put(PropertyIds.NAME, documentName);
            // Setup document content

            InputStream iStream = null;
            try {
                iStream = new FileInputStream(file);

                final ContentStream contentStream = session.getObjectFactory().createContentStream(file.getName(), file.length(),
                        fileType, iStream);
                // Create versioned document object
                newDocument = parentFolder.createDocument(newDocumentProps, contentStream, VersioningState.MAJOR);
                LOGGER.info("Created new document: " + getDocumentPath(newDocument) + " [version=" + newDocument.getVersionLabel()
                        + "][creator=" + newDocument.getCreatedBy() + "][created="
                        + date2String(newDocument.getCreationDate().getTime()) + "]");
            } catch (final FileNotFoundException e) {

                LOGGER.error(e.getMessage());
            } finally {
                try {
                    if (iStream != null) {
                        iStream.close();
                    }
                } catch (final IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } else {
            LOGGER.info("Document already exist: " + getDocumentPath(newDocument));
        }
        return newDocument;
    }

    /**
     * Gets the document path.
     *
     * @param document the document
     * @return the document path
     */
    private String getDocumentPath(final Document document) {
        String path2Doc = getParentFolderPath(document);
        if (!path2Doc.endsWith("/")) {

            path2Doc += "/";
        }
        path2Doc += document.getName();
        return path2Doc;
    }

    /**
     * Gets the parent folder path.
     *
     * @param document the document
     * @return the parent folder path
     */
    private String getParentFolderPath(final Document document) {
        final Folder parentFolder = getDocumentParentFolder(document);
        return parentFolder == null ? "Un-filed" : parentFolder.getPath();
    }

    /**
     * Gets the document parent folder.
     *
     * @param document the document
     * @return the document parent folder
     */
    private Folder getDocumentParentFolder(final Document document) {
        // Get all the parent folders (could be more than one
        // if multi-filed)
        final List<Folder> parentFolders = document.getParents();
        // Grab the first parent folder
        if (!parentFolders.isEmpty()) {
            if (parentFolders.size() > 1) {
                LOGGER.info("The " + document.getName() + " has more than one parent folder, it is multi-filed");
            }
            return parentFolders.get(0);
        } else {
            LOGGER.info("Document " + document.getName() + " is un-filed and does not have a parent folder");
            return null;
        }
    }

    /**
     * Gets the document by path.
     *
     * @param session the session
     * @param path the path
     * @return the document by path
     */
    public static ContentStream getDocumentByPath(final Session session, final String path) {
        try {
            LOGGER.info("Getting object by path " + path);
            Document doc = (Document) session.getObjectByPath(path);
            return doc.getContentStream();
        } catch (CmisObjectNotFoundException confe) {
            LOGGER.error(confe.getMessage(), confe);
            return null;
        }
    }

    /**
     * Gets the document by Id.
     *
     * @param session the session
     * @param id the id
     * @return the document by id
     */
    public ContentStream getDocumentById(final Session session, final String id) {
        LOGGER.info("Getting object by Id " + id);
        Document doc = (Document) session.getObject(new ObjectIdImpl(id));
        return doc.getContentStream();
    }

    /**
     * Retrieves the folder with the given name. Returns null if the folder does
     * not exist.
     *
     * @param session the session
     * @param folderName the folder name
     * @return the folder
     */
    public static Folder getFolder(final Session session, final String folderName) {
        final ObjectType type = session.getTypeDefinition("cmis:folder");
        final PropertyDefinition<?> objectIdPropDef = type.getPropertyDefinitions().get(PropertyIds.OBJECT_ID);
        final String objectIdQueryName = objectIdPropDef.getQueryName();

        final ItemIterable<QueryResult> results = session.query("SELECT * FROM cmis:folder WHERE cmis:name='" + folderName + "'",
                false);
        for (final QueryResult qResult : results) {
            final String objectId = qResult.getPropertyValueByQueryName(objectIdQueryName);
            return (Folder) session.getObject(session.createObjectId(objectId));
        }
        return null;
    }

    /**
     * Retrieves the document with the given name. Returns null if the document
     * does not exist.
     *
     * @param session the session
     * @param documentName the document name
     * @return the document by name
     */
    public static Document getDocumentByName(final Session session, final String documentName) {
        final ObjectType type = session.getTypeDefinition("cmis:document");
        final PropertyDefinition<?> objectIdPropDef = type.getPropertyDefinitions().get(PropertyIds.OBJECT_ID);
        final String objectIdQueryName = objectIdPropDef.getQueryName();

        final ItemIterable<QueryResult> results = session.query("SELECT * FROM cmis:document WHERE cmis:name='" + documentName
                + "'", false);
        for (final QueryResult qResult : results) {
            final String objectId = qResult.getPropertyValueByQueryName(objectIdQueryName);
            return (Document) session.getObject(session.createObjectId(objectId));
        }
        return null;
    }

}
