package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

/**
 * The Class Flow.
 */
@Entity
@Table(name = "FLOW")
@XmlRootElement
public class Flow extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLOW_SEQ")
    @SequenceGenerator(name = "FLOW_SEQ", sequenceName = "FLOW_SEQ", allocationSize = 1, initialValue = 200)
    private Long id;

    /**
     * The code.
     */
    @Column(name = "CODE")
    private String code;

    /**
     * The label fr.
     */
    @Column(name = "LABELFR")
    private String labelFr;

    /**
     * The label en.
     */
    @Column(name = "LABELEN")
    private String labelEn;

    /**
     * The duration.
     */
    @Column(name = "DURATION")
    private Short duration;

    /**
     * The outgoing.
     */
    @Column(name = "OUTGOING")
    private Integer outgoing;

    /**
     * The is cota.
     */
    @Column(name = "IS_COTA")
    private Boolean isCota;

    /**
     * The to step.
     */
    @JoinColumn(name = "TO_STEP", referencedColumnName = "ID")
    @ManyToOne
    private Step toStep;

    /**
     * The from step.
     */
    @JoinColumn(name = "FROM_STEP", referencedColumnName = "ID")
    @ManyToOne
    private Step fromStep;

    /**
     * The data type list.
     */
    @OneToMany(mappedBy = "flow")
    private List<DataType> dataTypeList;

    /**
     * The copy recipients list.
     */
    @OneToMany(mappedBy = "flow")
    private List<CopyRecipient> copyRecipientsList;

    /**
     * The items flows list.
     */
    @OneToMany(mappedBy = "flow")
    private List<ItemFlow> itemsFlowsList;

    /**
     * The file type flow reports list.
     */
    @OneToMany(mappedBy = "flow")
    private List<FileTypeFlowReport> fileTypeFlowReportsList;

    /**
     * The redefined label fr.
     */
    @Transient
    private String redefinedLabelFr;

    /**
     * The redefined label en.
     */
    @Transient
    private String redefinedLabelEn;

    /**
     * Instantiates a new flow.
     */
    public Flow() {
    }

    public Flow(String code) {
        this.code = code;
    }

    /**
     * Instantiates a new flow.
     *
     * @param id the id
     * @param labelFr the label fr
     * @param labelEn the label en
     */
    public Flow(final Long id, final String labelFr, final String labelEn) {

        this.id = id;
        this.labelFr = labelFr;
        this.labelEn = labelEn;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Gets the label fr.
     *
     * @return the labelFr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * Sets the label fr.
     *
     * @param labelFr the labelFr to set
     */
    public void setLabelFr(final String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * Gets the label en.
     *
     * @return the labelEn
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * Sets the label en.
     *
     * @param labelEn the labelEn to set
     */
    public void setLabelEn(final String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public Short getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the duration to set
     */
    public void setDuration(final Short duration) {
        this.duration = duration;
    }

    /**
     * Gets the outgoing.
     *
     * @return the outgoing
     */
    public Integer getOutgoing() {
        return outgoing;
    }

    /**
     * Sets the outgoing.
     *
     * @param outgoing the outgoing to set
     */
    public void setOutgoing(final Integer outgoing) {
        this.outgoing = outgoing;
    }

    /**
     * Gets the checks if is cota.
     *
     * @return the isCota
     */
    public Boolean getIsCota() {
        return isCota;
    }

    /**
     * Sets the checks if is cota.
     *
     * @param isCota the isCota to set
     */
    public void setIsCota(final Boolean isCota) {
        this.isCota = isCota;
    }

    /**
     * Gets the data type list.
     *
     * @return the dataTypeList
     */
    public List<DataType> getDataTypeList() {
        return dataTypeList;
    }

    /**
     * Sets the data type list.
     *
     * @param dataTypeList the dataTypeList to set
     */
    public void setDataTypeList(final List<DataType> dataTypeList) {
        this.dataTypeList = dataTypeList;
    }

    /**
     * Gets the copy recipients list.
     *
     * @return the copyRecipientsList
     */
    public List<CopyRecipient> getCopyRecipientsList() {
        return copyRecipientsList;
    }

    /**
     * Sets the copy recipients list.
     *
     * @param copyRecipientsList the copyRecipientsList to set
     */
    public void setCopyRecipientsList(final List<CopyRecipient> copyRecipientsList) {
        this.copyRecipientsList = copyRecipientsList;
    }

    /**
     * Gets the items flows list.
     *
     * @return the itemsFlowsList
     */
    public List<ItemFlow> getItemsFlowsList() {
        return itemsFlowsList;
    }

    /**
     * Sets the items flows list.
     *
     * @param itemsFlowsList the itemsFlowsList to set
     */
    public void setItemsFlowsList(final List<ItemFlow> itemsFlowsList) {
        this.itemsFlowsList = itemsFlowsList;
    }

    /**
     * Gets the to step.
     *
     * @return the toStep
     */
    public Step getToStep() {
        return toStep;
    }

    /**
     * Sets the to step.
     *
     * @param toStep the toStep to set
     */
    public void setToStep(final Step toStep) {
        this.toStep = toStep;
    }

    /**
     * Gets the from step.
     *
     * @return the fromStep
     */
    public Step getFromStep() {
        return fromStep;
    }

    /**
     * Sets the from step.
     *
     * @param fromStep the fromStep to set
     */
    public void setFromStep(final Step fromStep) {
        this.fromStep = fromStep;
    }

    /**
     * Gets the file type flow reports list.
     *
     * @return the file type flow reports list
     */
    public List<FileTypeFlowReport> getFileTypeFlowReportsList() {
        return fileTypeFlowReportsList;
    }

    /**
     * Sets the file type flow reports list.
     *
     * @param fileTypeFlowReportsList the new file type flow reports list
     */
    public void setFileTypeFlowReportsList(final List<FileTypeFlowReport> fileTypeFlowReportsList) {
        this.fileTypeFlowReportsList = fileTypeFlowReportsList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Gets the redefined label fr.
     *
     * @return the redefined label fr
     */
    public String getRedefinedLabelFr() {
        if (StringUtils.isEmpty(redefinedLabelFr)) {
            redefinedLabelFr = labelFr;
        }
        return redefinedLabelFr;
    }

    /**
     * Sets the redefined label fr.
     *
     * @param redefinedLabelFr the new redefined label fr
     */
    public void setRedefinedLabelFr(final String redefinedLabelFr) {
        this.redefinedLabelFr = redefinedLabelFr;
    }

    /**
     * Gets the redefined label en.
     *
     * @return the redefined label en
     */
    public String getRedefinedLabelEn() {
        if (StringUtils.isEmpty(redefinedLabelEn)) {
            redefinedLabelEn = labelEn;
        }
        return redefinedLabelEn;
    }

    /**
     * Sets the redefined label en.
     *
     * @param redefinedLabelEn the new redefined label en
     */
    public void setRedefinedLabelEn(final String redefinedLabelEn) {
        this.redefinedLabelEn = redefinedLabelEn;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Flow)) {
            return false;
        }
        final Flow other = (Flow) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Flow [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelEn=");
        builder.append(labelEn);
        builder.append(", duration=");
        builder.append(duration);
        builder.append(", outgoing=");
        builder.append(outgoing);
        builder.append("]");
        return builder.toString();
    }
}
