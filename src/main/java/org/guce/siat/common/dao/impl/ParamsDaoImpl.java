package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.model.Params_;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ParamsDaoImpl.
 */
@Repository("paramsDao")
@Transactional
public class ParamsDaoImpl extends AbstractJpaDaoImpl<Params> implements ParamsDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ParamsDaoImpl.class);

    /**
     * Instantiates a new params dao impl.
     */
    public ParamsDaoImpl() {
        setClasse(Params.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ParamsDao#findParamsByName(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Params findParamsByName(final String name) {
        try {
            CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery(getClasse());
            Root<Params> root = cq.from(getClasse());
            cq.where(builder.equal(root.get(Params_.name), name));
            TypedQuery<Params> query = super.entityManager.createQuery(cq.select(root));
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ParamsDao#findParamsByCategory(org.guce.siat.common.utils.enums.ParamsCategory)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Params> findParamsByCategory(ParamsCategory category) {

        if (category == null) {
            return Collections.emptyList();
        }

        CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(getClasse());
        Root<Params> root = cq.from(getClasse());
        cq.where(builder.equal(root.get(Params_.paramsCategory), category));
        TypedQuery<Params> query = super.entityManager.createQuery(cq);
        return query.getResultList();
    }

}
