/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AppointmentDao;
import org.guce.siat.common.model.Appointment;
import org.guce.siat.common.model.AppointmentItemFlow;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AppointmentDaoImpl.
 */
@Repository("appointmentDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AppointmentDaoImpl extends AbstractJpaDaoImpl<Appointment> implements AppointmentDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AppointmentDaoImpl.class);

    /**
     * Instantiates a new appointment DAO impl.
     */
    public AppointmentDaoImpl() {
        super();
        setClasse(Appointment.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#delete(org.guce.siat.common.model.AppointmentItemFlow)
     */
    @Override
    public void delete(final AppointmentItemFlow appointmentItemFlow) {
        entityManager.remove(appointmentItemFlow);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#findAppointmentsByControllerList(java.util.List)
     */
    @Override
    public List<Appointment> findAppointmentsByControllerList(final List<User> controllerList) {

        if (CollectionUtils.isNotEmpty(controllerList)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT a FROM Appointment a ");
            hqlBuilder.append("WHERE a.controller IN (:controllerList) ");
            hqlBuilder.append("AND (SELECT DISTINCT i.appointment.id FROM InspectionReport i ");
            hqlBuilder.append("WHERE i.appointment.id=a.id) IS NULL ");
            hqlBuilder.append("AND a.deleted=false");

            final TypedQuery<Appointment> query = super.entityManager.createQuery(hqlBuilder.toString(), Appointment.class);
            query.setParameter("controllerList", controllerList);
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.AppointmentDao#finAppointmentsByItemFlowList(java.util.List)
     */
    @Override
    public Appointment findAppointmentByItemFlowList(final List<ItemFlow> itemFlows) {
        if (CollectionUtils.isNotEmpty(itemFlows)) {
            try {
                final StringBuilder hqlBuilder = new StringBuilder();
                hqlBuilder.append("SELECT a.primaryKey.appointment FROM AppointmentItemFlow a ");
                hqlBuilder.append("WHERE a.deleted=false AND a.primaryKey.itemFlow  ");
                hqlBuilder.append("IN (:itemFlows) AND a.primaryKey.appointment.deleted=false");

                final TypedQuery<Appointment> query = super.entityManager.createQuery(hqlBuilder.toString(), Appointment.class);
                query.setParameter("itemFlows", itemFlows);

                return query.getSingleResult();

            } catch (final Exception e) {
                LOG.error(Objects.toString(e));
            }

        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#findAppointmentByItemFlow(org.guce.siat.common.model.ItemFlow)
     */
    @Override
    public Appointment findAppointmentByItemFlow(final ItemFlow itemFlow) {
        if (!Objects.equals(itemFlow, null)) {
            try {
                final StringBuilder hqlBuilder = new StringBuilder();
                hqlBuilder.append("SELECT a.primaryKey.appointment FROM AppointmentItemFlow a ");
                hqlBuilder.append("WHERE a.deleted=false AND a.primaryKey.itemFlow.id  ");
                hqlBuilder.append("= :itemFlowId AND a.primaryKey.appointment.deleted=false");

                final TypedQuery<Appointment> query = super.entityManager.createQuery(hqlBuilder.toString(), Appointment.class);
                query.setParameter("itemFlowId", itemFlow.getId());

                return query.getSingleResult();

            } catch (final Exception e) {
                LOG.error(Objects.toString(e));
            }

        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.AppointmentDao#findAppointmentItemFlowByItemFlow(org.guce.siat.common.model.ItemFlow)
     */
    @Override
    public AppointmentItemFlow findAppointmentItemFlowByItemFlow(final ItemFlow itemFlow) {
        if (!Objects.equals(itemFlow, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT a FROM AppointmentItemFlow a ");
            hqlBuilder.append("WHERE a.deleted=false AND a.primaryKey.itemFlow.id  ");
            hqlBuilder.append("=:itemFlowId AND a.primaryKey.appointment.deleted=false");

            final TypedQuery<AppointmentItemFlow> query = super.entityManager.createQuery(hqlBuilder.toString(),
                    AppointmentItemFlow.class);
            query.setParameter("itemFlowId", itemFlow.getId());
            try {
                return query.getSingleResult();
            } catch (NoResultException | NonUniqueResultException e) {
                LOG.error(Objects.toString(e));
            }

        }
        return null;
    }

    @Override
    public List<AppointmentItemFlow> findAppointmentItemFlowsByItemFlows(List<ItemFlow> itemFlows) {

        TypedQuery<AppointmentItemFlow> query = super.entityManager.createQuery("SELECT a FROM AppointmentItemFlow a WHERE a.deleted=false AND a.primaryKey.itemFlow IN (:itemFlows)", AppointmentItemFlow.class);

        query.setParameter("itemFlows", itemFlows);

        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.AppointmentDao#findDeletedAppointmentItemFlowAppointmentByItemFlow(org.guce.siat.common.
	 * model.ItemFlow)
     */
    @Override
    public AppointmentItemFlow findDeletedAppointmentItemFlowAppointmentByAppointment(final Appointment appointment) {
        if (!Objects.equals(appointment, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT a FROM AppointmentItemFlow a ");
            hqlBuilder.append("WHERE a.deleted=true AND a.primaryKey.appointment.id= :appointmentId ");
            hqlBuilder.append("AND a.primaryKey.appointment.deleted=false");

            final TypedQuery<AppointmentItemFlow> query = super.entityManager.createQuery(hqlBuilder.toString(),
                    AppointmentItemFlow.class);
            query.setParameter("appointmentId", appointment.getId());

            try {
                return query.getResultList().get(0);
            } catch (final Exception e) {
                LOG.error(Objects.toString(e));
            }
        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#findAppointmentsByItemFlow(org.guce.siat.common.model.ItemFlow)
     */
    @Override
    public Appointment findAppointmentsByItemFlow(final ItemFlow itemFlow) {
        if (!Objects.equals(itemFlow, null)) {
            try {
                final String hqlString = "SELECT a.primaryKey.appointment FROM AppointmentItemFlow a "
                        + "WHERE a.deleted=false AND a.primaryKey.itemFlow.id = :itemFlowId AND a.primaryKey.appointment.deleted=false";
                final TypedQuery<Appointment> query = super.entityManager.createQuery(hqlString, Appointment.class);
                query.setParameter("itemFlowId", itemFlow.getId());

                return query.getSingleResult();

            } catch (NoResultException | NonUniqueResultException e) {
                LOG.error(Objects.toString(e));
            }

        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#findAppoitmentByFileItemAndController(java.util.List,
	 * org.guce.siat.common.model.User)
     */
    @Override
    public Appointment findAppoitmentByFileItemAndController(final List<FileItem> fileItems, final User controller) {
        final StringBuilder hqlBuilder = new StringBuilder();
        hqlBuilder.append("SELECT a.primaryKey.appointment FROM AppointmentItemFlow a  ");
        hqlBuilder.append("WHERE a.deleted=false AND a.primaryKey.appointment.deleted=false ");
        hqlBuilder.append("AND a.primaryKey.appointment.controller.id= :controllerId ");
        hqlBuilder.append("AND a.primaryKey.itemFlow.fileItem IN (:fileItems)");

        try {
            final TypedQuery<Appointment> query = entityManager.createQuery(hqlBuilder.toString(), Appointment.class);
            query.setParameter("controllerId", controller.getId());
            query.setParameter("fileItems", fileItems);
            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#update(org.guce.siat.common.model.AppointmentItemFlow)
     */
    @Override
    public void update(final AppointmentItemFlow appointmentItemFlow) {
        entityManager.merge(appointmentItemFlow);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AppointmentDao#findLastAppointmentByItemFlow(org.guce.siat.common.model.ItemFlow)
     */
    @Override
    public Appointment findLastAppointmentByItemFlow(final ItemFlow itemFlow) {
        if (!Objects.equals(itemFlow, null)) {
            try {
                final String hqlString = "SELECT a.primaryKey.appointment FROM AppointmentItemFlow a WHERE a.primaryKey.itemFlow.id = :itemFlowId AND a.primaryKey.appointment.deleted = false";
                final TypedQuery<Appointment> query = super.entityManager.createQuery(hqlString, Appointment.class);
                query.setParameter("itemFlowId", itemFlow.getId());

                return query.getSingleResult();

            } catch (NoResultException | NonUniqueResultException e) {
                LOG.error(Objects.toString(e));
            }

        }
        return null;
    }

    @Override
    public Appointment findAppointmentByFileItem(FileItem fileItem) {

        if (Objects.equals(fileItem, null)) {
            return null;
        }

        TypedQuery<Appointment> query = super.entityManager.createQuery("SELECT DISTINCT a.primaryKey.appointment FROM AppointmentItemFlow a WHERE a.primaryKey.itemFlow.fileItem.id = :fileItemId AND a.primaryKey.appointment.deleted = false", Appointment.class);

        query.setParameter("fileItemId", fileItem.getId());
        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public Appointment findDeletedAppointmentByFileItem(FileItem fileItem) {

        if (Objects.equals(fileItem, null)) {
            return null;
        }

        TypedQuery<Appointment> query = super.entityManager.createQuery("SELECT DISTINCT a.primaryKey.appointment FROM AppointmentItemFlow a WHERE a.primaryKey.itemFlow.fileItem.id = :fileItemId AND a.primaryKey.appointment.deleted = true", Appointment.class);

        query.setParameter("fileItemId", fileItem.getId());
        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Appointment> findRelatedAppointments(File currentFile, FileTypeCode... fileTypeCodes) {

        TypedQuery<Appointment> query = super.entityManager.createQuery("SELECT DISTINCT a.primaryKey.appointment FROM AppointmentItemFlow a WHERE a.primaryKey.itemFlow.fileItem.file.numeroDemande = :numeroDemande AND a.primaryKey.itemFlow.fileItem.file.numeroDossier <> :numeroDossier AND a.primaryKey.itemFlow.fileItem.file.fileType.code IN (:fileTypeCodes) AND a.primaryKey.appointment.deleted = false", Appointment.class);

        query.setParameter("numeroDemande", currentFile.getNumeroDemande());
        query.setParameter("numeroDossier", currentFile.getNumeroDossier());
        query.setParameter("fileTypeCodes", Arrays.asList(fileTypeCodes));

        return query.getResultList();
    }

}
