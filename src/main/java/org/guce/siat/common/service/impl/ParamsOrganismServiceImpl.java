package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.ParamsOrganismDao;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.model.ParamsOrganism;
import org.guce.siat.common.service.ParamsOrganismService;
import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ParamsOrganismServiceImpl.
 */
@Service("paramsOrganismService")
@Transactional(readOnly = true)
public class ParamsOrganismServiceImpl extends AbstractServiceImpl<ParamsOrganism> implements ParamsOrganismService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ParamsOrganismServiceImpl.class);

    /**
     * The params organism dao.
     */
    @Autowired
    private ParamsOrganismDao paramsOrganismDao;

    /**
     * The params dao.
     */
    @Autowired
    private ParamsDao paramsDao;

    /**
     * Instantiates a new params organism service impl.
     */
    public ParamsOrganismServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<ParamsOrganism> getJpaDao() {
        return paramsOrganismDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<ParamsOrganism> jpaDao) {
        this.paramsOrganismDao = (ParamsOrganismDao) jpaDao;
    }

    /**
     * Gets the params dao.
     *
     * @return the paramsDao
     */
    public ParamsDao getParamsDao() {
        return paramsDao;
    }

    /**
     * Sets the params dao.
     *
     * @param paramsDao the paramsDao to set
     */
    public void setParamsDao(final ParamsDao paramsDao) {
        this.paramsDao = paramsDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.gr.service.ParamsOrganismService#findParamsOrganismByOrganism(org.guce.siat.common.model.Organism
	 * )
     */
    @Override
    public List<ParamsOrganism> findParamsOrganismByOrganism(final Organism organism, final ParamsCategory category) {

        final List<ParamsOrganism> retrievedParams = paramsOrganismDao.findParamsOrganismByOrganism(organism, category);
        final List<Params> paramsList = paramsDao.findParamsByCategory(category);
        final List<ParamsOrganism> paramsOrganismList = new ArrayList<>();
        ParamsOrganism newParam;
        Boolean found;
        Long id = Long.valueOf(Constants.ZERO);
        if (CollectionUtils.isNotEmpty(retrievedParams)) {
            for (final Params param : paramsList) {
                found = false;
                for (final ParamsOrganism paramsOrganism : retrievedParams) {
                    if (param.getName().equalsIgnoreCase(paramsOrganism.getParam().getName())) {
                        paramsOrganismList.add(paramsOrganism);
                        found = true;
                        break;
                    }

                }
                if (!found) {
                    newParam = new ParamsOrganism();
                    newParam.setId(--id);
                    newParam.setParam(param);
                    newParam.setOrganism(organism);
                    newParam.setValue(param.getValue());
                    paramsOrganismList.add(newParam);
                }

            }
        } else {
            for (final Params param : paramsList) {
                newParam = new ParamsOrganism();
                newParam.setId(--id);
                newParam.setParam(param);
                newParam.setOrganism(organism);
                newParam.setValue(param.getValue());
                paramsOrganismList.add(newParam);
            }
        }
        return paramsOrganismList;

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.ParamsOrganismService#findParamsOrganismByOrganismAndName(org.guce.siat.common.model
	 * .Organism, java.lang.String)
     */
    @Override
    public ParamsOrganism findParamsOrganismByOrganismAndName(final Organism organism, final String paramName) {
        return paramsOrganismDao.findParamsOrganismByOrganismAndName(organism, paramName);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.ParamsOrganismService#findLongParamsOrganismByOrganismAndName(org.guce.siat.common
	 * .model.Organism, java.lang.String)
     */
    @Override
    public Long findLongParamsOrganismByOrganismAndName(final Organism organism, final String paramName) {
        try {
            final ParamsOrganism nbrRDDParam = paramsOrganismDao.findParamsOrganismByOrganismAndName(organism, paramName);

            if (nbrRDDParam != null) {
                return Long.parseLong(nbrRDDParam.getValue());
            } else {
                return Long.parseLong(paramsDao.findParamsByName(paramName).getValue());
            }
        } catch (final NumberFormatException | NullPointerException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

}
