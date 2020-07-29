package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.BureauDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.BureauService;
import org.guce.siat.common.utils.enums.BureauType;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class BureauServiceImpl.
 */
@org.springframework.stereotype.Service("bureauService")
@Transactional(readOnly = true)
public class BureauServiceImpl extends AbstractServiceImpl<Bureau> implements BureauService {

    /**
     * The bureau dao.
     */
    @Autowired
    private BureauDao bureauDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Instantiates a new bureau service impl.
     */
    public BureauServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.BureauService#findBureauByTypeAndOrganism(org.guce.siat.common.utils.enums.BureauType
	 * , org.guce.siat.common.model.Organism)
     */
    @Override
    public List<Bureau> findBureauByTypeAndOrganism(final BureauType type, final Organism organism) {

        List<Bureau> bureaus = bureauDao.findBureauByTypeAndOrganism(type, organism);

        final List<Long> headOfficesIds = new ArrayList<>();

        for (final Bureau bureau : bureaus) {
            headOfficesIds.add(bureau.getId());
        }

        final List<User> users = userDao.findUsersByAdministrationsIds(headOfficesIds.toArray(new Long[headOfficesIds.size()]));

        for (final Bureau bureau : bureaus) {

            for (final User user : users) {
                if (bureau.getId().equals(user.getAdministration().getId()) && PositionType.CHEF_BUREAU.equals(user.getPosition())) {
                    bureau.setHeadOffice(user);
                    break;
                }
            }
        }

        return bureaus;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<Bureau> getJpaDao() {
        return bureauDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<Bureau> jpaDao) {
        this.bureauDao = (BureauDao) jpaDao;

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#find(java.lang.Long)
     */
    @Override
    public Bureau find(final Long id) {
        final List<Long> headServicesAndOfficesIds = new ArrayList<>();
        final Bureau bureau = getJpaDao().find(id);
        headServicesAndOfficesIds.add(id);
        headServicesAndOfficesIds.add(bureau.getService().getId());
        final List<User> users = userDao.findUsersByAdministrationsIds(headServicesAndOfficesIds.toArray(new Long[headServicesAndOfficesIds.size()]));
        for (final User user : users) {
            if (bureau.getId().equals(user.getAdministration().getId())) {
                bureau.setHeadOffice(user);
            } else if (bureau.getService().getId().equals(user.getAdministration().getId())) {
                bureau.getService().setHeadService(user);
            }
        }
        return bureau;
    }

}
