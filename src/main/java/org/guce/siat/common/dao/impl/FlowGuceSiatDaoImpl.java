package org.guce.siat.common.dao.impl;

import java.util.List;
import java.util.Objects;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.FlowGuceSiatDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FlowGuceSiat;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class BureauDaoImpl.
 */
@Repository("flowGuceSiatDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FlowGuceSiatDaoImpl extends AbstractJpaDaoImpl<FlowGuceSiat> implements FlowGuceSiatDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FlowGuceSiatDaoImpl.class);

    /**
     * Instantiates a new bureau dao impl.
     */
    public FlowGuceSiatDaoImpl() {
        super();
        setClasse(FlowGuceSiat.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowGuceSiatDao#findFlowGuceSiatByFlowGuce(java.lang.String)
     */
    @Override
    public FlowGuceSiat findFlowGuceSiatByFlowGuce(final String flowGuce) {
        try {
            final String hqlString = "SELECT b FROM FlowGuceSiat b WHERE b.flowGuce = :flowGuce";
            final TypedQuery<FlowGuceSiat> query = super.entityManager.createQuery(hqlString, FlowGuceSiat.class);
            query.setParameter("flowGuce", flowGuce);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    @Override
    public FlowGuceSiat findFlowGuceSiatByFlowGuceAndFileType(final String flowGuce, final FileType fileType) {
        try {
            final TypedQuery<FlowGuceSiat> query = super.entityManager.createQuery("SELECT b FROM FlowGuceSiat b WHERE b.flowGuce = :flowGuce AND b.fileType.id = :fileTypeId", FlowGuceSiat.class);
            query.setParameter("flowGuce", flowGuce);
            query.setParameter("fileTypeId", fileType.getId());
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    @Override
    public List<FlowGuceSiat> findAllFlowGuceSiatsByFlowGuceAndFileType(final String flowGuce, final FileType fileType) {
        TypedQuery<FlowGuceSiat> query = super.entityManager.createQuery("SELECT b FROM FlowGuceSiat b WHERE b.flowGuce = :flowGuce AND b.fileType.id = :fileTypeId", FlowGuceSiat.class);
        query.setParameter("flowGuce", flowGuce);
        query.setParameter("fileTypeId", fileType.getId());
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowGuceSiatDao#findFlowGuceSiatByFlowSiat(java.lang.String)
     */
    @Override
    public FlowGuceSiat findFlowGuceSiatByFlowSiatAndFileType(final String flowSiat, final Long fileTypeId) {
        try {
            //final String hqlString = "FROM FlowGuceSiat b WHERE b.flowSiat = :flowSiat AND b.fileType.id = :fileTypeId";
            final String hqlString = "SELECT b FROM FlowGuceSiat b WHERE b.flowSiat = :flowSiat AND b.fileType.id = :fileTypeId";
            final TypedQuery<FlowGuceSiat> query = super.entityManager.createQuery(hqlString, FlowGuceSiat.class);
            query.setParameter("flowSiat", flowSiat);
            query.setParameter("fileTypeId", fileTypeId);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    @Override
    public FlowGuceSiat findFlowGuceSiatFlowGuceAndFileType(final String flowGuce, final FileTypeCode fileType) {
        try {
            final String hqlString = "SELECT b FROM FlowGuceSiat b WHERE b.flowGuce = :flowGuce and b.fileType.code = :fileType";
            final TypedQuery<FlowGuceSiat> query = super.entityManager.createQuery(hqlString, FlowGuceSiat.class);
            query.setParameter("flowGuce", flowGuce);
            query.setParameter("fileType", fileType);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }    
    }
    
    
    
    
}
