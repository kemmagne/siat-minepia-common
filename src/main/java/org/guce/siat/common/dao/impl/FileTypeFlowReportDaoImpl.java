package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FileTypeFlowReportDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Flow;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileTypeFlowReportDaoImpl.
 */
@Repository("fileTypeFlowReport")
@Transactional(propagation = Propagation.REQUIRED)
public class FileTypeFlowReportDaoImpl extends AbstractJpaDaoImpl<FileTypeFlowReport> implements FileTypeFlowReportDao {

    /**
     * Instantiates a new file type flow report dao impl.
     */
    public FileTypeFlowReportDaoImpl() {
        super();
        setClasse(FileTypeFlowReport.class);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.FileTypeFlowReportDao#
	 * findReportClassNameByFlowAndFileType(org.guce.siat.common.model .Flow,
	 * org.guce.siat.common.model.FileType)
     */
    @Override
    public List<FileTypeFlowReport> findReportClassNameByFlowAndFileType(
            final Flow flow, final FileType fileType) {
        if (!Objects.equals(flow, null) && !Objects.equals(fileType, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("FROM FileTypeFlowReport ffa ");
            hqlBuilder.append("WHERE ffa.fileType = :fileType ");
            hqlBuilder.append("AND ffa.flow.code = :flowCode ");

            final TypedQuery<FileTypeFlowReport> query = super.entityManager
                    .createQuery(hqlBuilder.toString(),
                            FileTypeFlowReport.class);

            query.setParameter("fileType", fileType);
            query.setParameter("flowCode", flow.getCode());

            return query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public FileTypeFlowReport findReportClassNameByFileType(final FileType fileType) {
        if (!Objects.equals(fileType, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("FROM FileTypeFlowReport ffa ");
            hqlBuilder.append("WHERE ffa.fileType = :fileType ");

            final TypedQuery<FileTypeFlowReport> query = super.entityManager
                    .createQuery(hqlBuilder.toString(),
                            FileTypeFlowReport.class);

            query.setParameter("fileType", fileType);

            List<FileTypeFlowReport> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                return resultList.get(0);
            }
            return null;

        }
        return null;
    }

    @Override
    public FileTypeFlowReport findReportClassNameByFileFieldName(String fileFieldName) {
        if (!Objects.equals(fileFieldName, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("FROM FileTypeFlowReport ffa ");
            hqlBuilder.append("WHERE ffa.fileFieldName = :fileFieldName ");

            final TypedQuery<FileTypeFlowReport> query = super.entityManager
                    .createQuery(hqlBuilder.toString(),
                            FileTypeFlowReport.class);

            query.setParameter("fileFieldName", fileFieldName.toUpperCase());

            List<FileTypeFlowReport> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                return resultList.get(0);
            }
            return null;
        }
        return null;
    }
}
