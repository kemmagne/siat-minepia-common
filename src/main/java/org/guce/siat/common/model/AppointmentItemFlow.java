package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class AppointmentItemFlow.
 */
@Entity
@Table(name = "Appointment_Item_Flow")
@XmlRootElement
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.appointment", joinColumns = @JoinColumn(name = "APPOINTMENT_ID")),
    @AssociationOverride(name = "primaryKey.itemFlow", joinColumns = @JoinColumn(name = "ITEM_FLOW_ID"))
})
public class AppointmentItemFlow extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1661540340062149431L;

    /**
     * The primary key.
     */
    @EmbeddedId
    private AppointmentItemFlowId primaryKey = new AppointmentItemFlowId();

    /**
     * The deleted.
     */
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted;

    /**
     * The begin time.
     */
    @Column(name = "APPOINTMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    /**
     * Instantiates a new appointment item flow.
     */
    public AppointmentItemFlow() {
    }

    /**
     * Instantiates a new appointment item flow.
     *
     * @param primaryKey the primary key
     */
    public AppointmentItemFlow(final AppointmentItemFlowId primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the primary key.
     *
     * @return the primary key
     */
    public AppointmentItemFlowId getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primary key.
     *
     * @param primaryKey the new primary key
     */
    public void setPrimaryKey(final AppointmentItemFlowId primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the appointment.
     *
     * @return the appointment
     */
    @Transient
    public Appointment getAppointment() {
        return primaryKey.getAppointment();
    }

    /**
     * Gets the item flow.
     *
     * @return the item flow
     */
    @Transient
    public ItemFlow getItemFlow() {
        return primaryKey.getItemFlow();
    }

    /**
     * Sets the appointment.
     *
     * @param appointment the new appointment
     */
    public void setAppointment(final Appointment appointment) {
        primaryKey.setAppointment(appointment);
    }

    /**
     * Sets the item flow.
     *
     * @param itemFlow the new item flow
     */
    public void setItemFlow(final ItemFlow itemFlow) {
        primaryKey.setItemFlow(itemFlow);
    }

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist() {
        this.deleted = Boolean.FALSE;
//        appointmentDate = primaryKey.getAppointment().getBeginTime();
    }

}
