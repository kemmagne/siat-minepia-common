package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ServiceDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.ServiceService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.BureauType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ServiceServiceImpl.
 */
@org.springframework.stereotype.Service("serviceService")
@Transactional(readOnly = true)
public class ServiceServiceImpl extends AbstractServiceImpl<Service> implements ServiceService {

    /**
     * The service dao.
     */
    @Autowired
    private ServiceDao serviceDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Instantiates a new service service impl.
     */
    public ServiceServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<Service> getJpaDao() {
        return serviceDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<Service> jpaDao) {
        this.serviceDao = (ServiceDao) jpaDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#find(java.lang.Long)
     */
    @Override
    public Service find(final Long id) {
        final Service service = getJpaDao().find(id);

        if (service != null) {
            for (final Entity entity : service.getEntityList()) {
                if (entity instanceof Bureau && BureauType.BUREAU_CENTRAL.equals(((Bureau) entity).getBureauType())) {
                    service.setCentralBureau((Bureau) entity);
                    break;
                }
            }
            final List<User> headServices = userDao.findUsersByAdministrationsIds(id);
            final List<User> headOffices = userDao.findUsersByAdministrationsIds(service.getCentralBureau().getId());

            if (CollectionUtils.isNotEmpty(headServices)) {
                service.setHeadService(headServices.get(0));
            }
            if (CollectionUtils.isNotEmpty(headOffices)) {
                service.getCentralBureau().setHeadOffice(headOffices.get(0));
            }
        }
        return service;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#findAll()
     */
    @Override
    public List<Service> findAll() {
        final List<Service> servicesList = getJpaDao().findAll();
        final List<Long> serviceOfficialsIds = new ArrayList<Long>();

        for (final Service service : servicesList) {
            serviceOfficialsIds.add(service.getId());
            serviceOfficialsIds.add(service.getCentralBureau().getId());

        }

        final List<User> users = userDao.findUsersByAdministrationsIds(serviceOfficialsIds.toArray(new Long[serviceOfficialsIds
                .size()]));

        for (final Service service : servicesList) {
            for (final Entity entity : service.getEntityList()) {
                if (entity instanceof Bureau && BureauType.BUREAU_CENTRAL.equals(((Bureau) entity).getBureauType())) {
                    service.setCentralBureau((Bureau) entity);
                    break;
                }
            }
        }

        for (final Service service : servicesList) {
            for (final User user : users) {
                if (service.getId().equals(user.getAdministration().getId())) {
                    service.setHeadService(user);
                } else if (service.getCentralBureau().getId().equals(user.getAdministration().getId())) {
                    service.getCentralBureau().setHeadOffice(user);
                }
            }
        }

        return servicesList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#findActiveItems()
     */
    @Override
    public List<Service> findActiveItems() {
        final List<Service> servicesList = getJpaDao().findActiveItems();

        final List<Long> serviceOfficialsIds = new ArrayList<Long>();

        for (final Service service : servicesList) {
            serviceOfficialsIds.add(service.getId());
            serviceOfficialsIds.add(service.getCentralBureau().getId());

        }

        final List<User> users = userDao.findUsersByAdministrationsIds(serviceOfficialsIds.toArray(new Long[serviceOfficialsIds
                .size()]));

        for (final Service service : servicesList) {
            for (final Entity entity : service.getEntityList()) {
                if (entity instanceof Bureau && BureauType.BUREAU_CENTRAL.equals(((Bureau) entity).getBureauType())) {
                    service.setCentralBureau((Bureau) entity);
                    break;
                }
            }
        }

        for (final Service service : servicesList) {
            for (final User user : users) {
                if (service.getId().equals(user.getAdministration().getId())) {
                    service.setHeadService(user);
                } else if (service.getCentralBureau().getId().equals(user.getAdministration().getId())) {
                    service.getCentralBureau().setHeadOffice(user);
                }
            }
        }

        return servicesList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ServiceService#findServiceByUser(org.guce.siat.common.model.User)
     */
    @Override
    public Service findServiceByUser(final User user) {
        Service service = null;

        if (user != null) {
            if (user.getAdministration() instanceof Service) {
                service = (Service) user.getAdministration();
            } else if (user.getAdministration() instanceof Entity) {
                service = ((Entity) user.getAdministration()).getService();
            }
        }
        return service;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ServiceService#findServicesByOrganism(org.guce.siat.common.model.Organism)
     */
    @Override
    public List<Service> findServicesByOrganism(final Organism organism) {
        final List<Service> services = serviceDao.findServicesByOrganism(organism);
        //final List<Service> services = organism.getSubDepartmentsList().g
        final List<Long> headServicesAndOfficesIds = new ArrayList<Long>();
        List<Entity> entities = null;

        for (final Service service : services) {
            headServicesAndOfficesIds.add(service.getId());
            entities = service.getEntityList();
            for (final Entity entity : entities) {
                if (entity instanceof Bureau && ((Bureau) entity).getBureauType().equals(BureauType.BUREAU_CENTRAL)) {
                    headServicesAndOfficesIds.add(entity.getId());
                    service.setCentralBureau((Bureau) entity);
                }
            }
        }

        final List<User> users = userDao.findUsersByAdministrationsIds(headServicesAndOfficesIds
                .toArray(new Long[headServicesAndOfficesIds.size()]));

        for (final Service service : services) {
            for (final User user : users) {
                if (service.getId().equals(user.getAdministration().getId())) {
                    service.setHeadService(user);
                } else if (service.getCentralBureau() != null
                        && service.getCentralBureau().getId().equals(user.getAdministration().getId())) {
                    service.getCentralBureau().setHeadOffice(user);
                }
            }
        }

        return services;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.ServiceService#findNonAffectedBySubDepartment(org.guce.siat.common.model.SubDepartment
	 * )
     */
    @Override
    public List<Service> findNonAffectedServicesBySubDepartment(final SubDepartment subDepartment) {
        try {
            return serviceDao.findNonAffectedServicesBySubDepartment(subDepartment);
        } catch (final Exception e) {
            throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.ServiceService#findServicesIdsByAdministration(org.guce.siat.common.model.Administration
	 * )
     */
    @Override
    public List<Long> findServicesIdsByAdministration(final Administration administration) {
        final List<Long> servicesListIds = new ArrayList<Long>();

        if (administration != null) {
            final List<Service> servicesList = new ArrayList<Service>();

            if (administration instanceof Ministry) {
                for (final Organism organism : ((Ministry) administration).getOrganismsList()) {
                    for (final SubDepartment subDep : organism.getSubDepartmentsList()) {
                        servicesList.addAll(subDep.getServicesList());
                    }
                }
            } else if (administration instanceof Organism) {
                for (final SubDepartment subDep : ((Organism) administration).getSubDepartmentsList()) {
                    servicesList.addAll(subDep.getServicesList());
                }
            } else if (administration instanceof SubDepartment) {
                servicesList.addAll(((SubDepartment) administration).getServicesList());
            } else if (administration instanceof Service) {
                servicesList.add((Service) administration);
            } else if (administration instanceof Entity) {
                servicesList.add(((Entity) administration).getService());
            }

            for (final Service service : servicesList) {
                servicesListIds.add(service.getId());
            }
        }
        return servicesListIds;
    }

    @Override
    public List<Service> findServicesByAdministration(final Administration administration) {

        if (administration != null) {
            final List<Service> servicesList = new ArrayList<Service>();

            if (administration instanceof Ministry) {
                for (final Organism organism : ((Ministry) administration).getOrganismsList()) {
                    for (final SubDepartment subDep : organism.getSubDepartmentsList()) {
                        servicesList.addAll(subDep.getServicesList());
                    }
                }
            } else if (administration instanceof Organism) {
                for (final SubDepartment subDep : ((Organism) administration).getSubDepartmentsList()) {
                    servicesList.addAll(subDep.getServicesList());
                }
            } else if (administration instanceof SubDepartment) {
                servicesList.addAll(((SubDepartment) administration).getServicesList());
            } else if (administration instanceof Service) {
                servicesList.add((Service) administration);
            } else if (administration instanceof Entity) {
                servicesList.add(((Entity) administration).getService());
            }

            return servicesList;
        }
        return null;
    }

    @Override
    public List<Service> findServicesByAdministration(FileType fileType) {
        return serviceDao.findServiceByFileType(fileType);
    }

}
