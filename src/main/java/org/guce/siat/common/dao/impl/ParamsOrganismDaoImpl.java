package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.ParamsOrganismDao;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.ParamsOrganism;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ParamsOrganismDaoImpl.
 */
@Repository("paramsOrganismDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ParamsOrganismDaoImpl extends AbstractJpaDaoImpl<ParamsOrganism> implements ParamsOrganismDao {

    /**
     * Instantiates a new params organism dao impl.
     */
    public ParamsOrganismDaoImpl() {
        super();
        setClasse(ParamsOrganism.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.gr.dao.ParamsOrganismDao#findParamsOrganismByOrganism(org.guce.siat.common.model.Organism)
     */
    @Override
    public List<ParamsOrganism> findParamsOrganismByOrganism(final Organism organism, final ParamsCategory category) {
        if (organism != null) {
            final String hqlQuery = "SELECT p FROM ParamsOrganism p WHERE p.organism.id = :id AND p.param.paramsCategory = :cat ";
            final TypedQuery<ParamsOrganism> query = super.entityManager.createQuery(hqlQuery, ParamsOrganism.class);
            query.setParameter("id", organism.getId());
            query.setParameter("cat", category);
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.ParamsOrganismDao#findParamsOrganismByOrganismAndName(org.guce.siat.common.model.Organism
	 * , java.lang.String)
     */
    @Override
    public ParamsOrganism findParamsOrganismByOrganismAndName(final Organism organism, final String paramName) {
        if (organism != null) {
            final StringBuilder hqlQuery = new StringBuilder();
            hqlQuery.append("SELECT p FROM ParamsOrganism p WHERE p.organism.id = :organismId AND p.param.name = :paramName");
            final TypedQuery<ParamsOrganism> query = super.entityManager.createQuery(hqlQuery.toString(), ParamsOrganism.class);
            query.setParameter("organismId", organism.getId());
            query.setParameter("paramName", paramName);
            final List<ParamsOrganism> paramsOrganismList = query.getResultList();
            return CollectionUtils.isNotEmpty(paramsOrganismList) ? paramsOrganismList.get(0) : null;
        } else {
            return null;
        }
    }

}
