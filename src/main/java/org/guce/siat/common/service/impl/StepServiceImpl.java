package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.StepDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.service.StepService;
import org.guce.siat.common.utils.enums.StepCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class StepServiceImpl.
 */
@Service("stepService")
@Transactional(readOnly = true)
public class StepServiceImpl extends AbstractServiceImpl<Step> implements StepService {

    /**
     * The step dao.
     */
    @Autowired
    private StepDao stepDao;

    /**
     * Instantiates a new step service impl.
     */
    public StepServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<Step> getJpaDao() {
        return stepDao;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<Step> jpaDao) {
        this.stepDao = (StepDao) jpaDao;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.common.service.StepService#findByAuthorotiesId(org.guce.siat.common.model.Authority)
     */
    @Override
    public List<Step> findByAuthority(final Authority authority) {
        return stepDao.findByAuthority(authority);
    }

    @Override
    public Step findByStepCode(StepCode stepCode) {
        return stepDao.findByStepCode(stepCode);
    }

}
