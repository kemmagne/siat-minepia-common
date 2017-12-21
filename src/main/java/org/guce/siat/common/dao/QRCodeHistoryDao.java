package org.guce.siat.common.dao;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.QRCodeHistory;

/**
 *
 * @author tadzotsa
 */
public interface QRCodeHistoryDao extends AbstractJpaDao<QRCodeHistory> {

    QRCodeHistory findByFile(File file);

}
