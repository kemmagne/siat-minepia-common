package org.guce.siat.common.dao.impl;

import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.QRCodeHistoryDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.QRCodeHistory;

/**
 *
 * @author tadzotsa
 */
public class QRCodeHistoryDaoImpl extends AbstractJpaDaoImpl<QRCodeHistory> implements QRCodeHistoryDao {

    public QRCodeHistoryDaoImpl() {
        super();
        setClasse(QRCodeHistory.class);
    }

    @Override
    public QRCodeHistory findByFile(File file) {
        if (null == file) {
            return null;
        }

        TypedQuery<QRCodeHistory> query = entityManager.createQuery("SELECT qh FROM QRCodeHistory qh WHERE qh.qrCodeFile.id = :fileId", QRCodeHistory.class);
        query.setParameter("fileId", file.getId());
        return query.getSingleResult();
    }

}
