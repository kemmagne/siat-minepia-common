package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import org.apache.commons.lang.StringUtils;

import org.guce.siat.common.dao.AttachmentDao;
import org.guce.siat.common.model.Attachment;
import org.guce.siat.common.model.File;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AttachmentDaoImpl.
 */
@Repository("attachmentDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AttachmentDaoImpl extends AbstractJpaDaoImpl<Attachment> implements AttachmentDao {

    /**
     * Instantiates a new attachment dao impl.
     */
    public AttachmentDaoImpl() {
        super();
        setClasse(Attachment.class);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.AttachmentDao#findAttachmentsByFile(org.guce.siat.core.ct.model.File)
     */
    @Override
    public List<Attachment> findAttachmentsByFile(final File file) {
        if (file != null) {
            final String hqlQuery = "SELECT a FROM Attachment a WHERE a.file.id = :fileId ";
            TypedQuery<Attachment> query = null;
            query = super.entityManager.createQuery(hqlQuery, Attachment.class);
            query.setParameter("fileId", file.getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public Attachment findAttachmentByFileAndAttachmentTypeAndDocumentNameAndDocumentPath(File file, String attachmentType, String documentName, String documentPath) {
        try {
            if (attachmentType != null && !StringUtils.isEmpty(attachmentType) && documentName != null && !StringUtils.isEmpty(documentName) && documentPath != null && !StringUtils.isEmpty(documentPath)) {
                final String hqlQuery = "SELECT a FROM Attachment a WHERE a.file.id = :fileId AND a.attachmentType = :attachmentType AND a.documentName = :documentName AND a.path = :documentPath";
                TypedQuery<Attachment> query = super.entityManager.createQuery(hqlQuery, Attachment.class);
                query.setParameter("fileId", file.getId());
                query.setParameter("attachmentType", attachmentType);
                query.setParameter("documentName", documentName);
                query.setParameter("documentPath", documentPath);
                return query.getSingleResult();
            }
            return null;
        } catch (Exception ex) {
            return null;
        }

    }
}
