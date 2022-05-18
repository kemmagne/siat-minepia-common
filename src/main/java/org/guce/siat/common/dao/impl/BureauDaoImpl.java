package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.BureauDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.utils.enums.BureauType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class BureauDaoImpl.
 */
@Repository("bureauDao")
@Transactional(propagation = Propagation.REQUIRED)
public class BureauDaoImpl extends AbstractJpaDaoImpl<Bureau> implements BureauDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BureauDaoImpl.class);

    /**
     * Instantiates a new bureau dao impl.
     */
    public BureauDaoImpl() {
        super();
        setClasse(Bureau.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.BureauDao#findBureauByTypeAndOrganism(org.guce.siat.common.utils.enums.BureauType,
	 * org.guce.siat.common.model.Organism)
     */
    @Override
    public List<Bureau> findBureauByTypeAndOrganism(final BureauType bureauType, final Organism organism) {
        if (organism != null) {
            final StringBuilder hqlQuery = new StringBuilder();
            hqlQuery.append("FROM Bureau b WHERE b.deleted = false AND b.service.subDepartment.organism.id = :organismId ");
            if (bureauType != null) {
                hqlQuery.append("AND b.bureauType = :bureauType");
            }
            final TypedQuery<Bureau> query = super.entityManager.createQuery(hqlQuery.toString(), Bureau.class);
            query.setParameter("organismId", organism.getId());
            if (bureauType != null) {
                query.setParameter("bureauType", bureauType);
            }
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.BureauDao#findByServiceAndCode(org.guce.siat.common.model.Service, java.lang.String)
     */
    @Override
    public Bureau findByServiceAndCode(final Service service, final String codeBureau) {
        final TypedQuery<Bureau> query = entityManager.createQuery(
                "SELECT b FROM Bureau b WHERE b.service= :service AND b.code= :codeBureau", Bureau.class);
        query.setParameter("service", service);
        query.setParameter("codeBureau", codeBureau);
        try {
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOG.error(Objects.toString(e));
            return null;
        }
    }

    /**
     *
     * @param bureauCode
     * @return
     */
    @Override
    public Bureau findByCode(final String bureauCode) {
        final TypedQuery<Bureau> query = entityManager.createQuery("SELECT b FROM Bureau b WHERE b.code = :bureauCode",
                Bureau.class);
        query.setParameter("bureauCode", bureauCode);
        try {
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOG.error(Objects.toString(e));
            return null;
        }
    }

    @Override
    public List<Bureau> findBureauByOrganism(Organism organism) {
        if (organism != null) {
            final StringBuilder hqlQuery = new StringBuilder();
            hqlQuery.append("FROM Bureau b WHERE b.deleted = false AND b.service.subDepartment.organism.id = :organismId ");
            
            final TypedQuery<Bureau> query = super.entityManager.createQuery(hqlQuery.toString(), Bureau.class);
            query.setParameter("organismId", organism.getId());
            
            return query.getResultList();
        }
        return Collections.emptyList();
        
        
    }
}

