package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.utils.enums.StepCode;
import org.hibernate.annotations.Cascade;

/**
 * The Class Step.
 */
@Entity
@Table(name = "STEP")
@XmlRootElement
public class Step extends AbstractModel implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STEP_SEQ")
    @SequenceGenerator(name = "STEP_SEQ", sequenceName = "STEP_SEQ", allocationSize = 1, initialValue = 200)
    private Long id;

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
     * The is final.
     */
    @Column(name = "IS_FINAL")
    private Boolean isFinal;

    /**
     * The file items list.
     */
    @OneToMany(mappedBy = "step")
    private List<FileItem> fileItemsList;

    /**
     * The to step flows list.
     */
    @OneToMany(mappedBy = "toStep")
    private List<Flow> toStepFlowsList;

    /**
     * The from step flows list.
     */
    @OneToMany(mappedBy = "fromStep")
    private List<Flow> fromStepFlowsList;

    /**
     * The step code.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "CODE")
    private StepCode stepCode;

    /**
     * The role list.
     */
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "STEP_AUTHORITY", joinColumns
            = {
                @JoinColumn(name = "STEP_ID", referencedColumnName = "ID")}, inverseJoinColumns
            = {
                @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> roleList;

    /**
     * Instantiates a new step.
     */
    public Step() {
    }

    /**
     * Gets the role list.
     *
     * @return the role list
     */
    public List<Authority> getRoleList() {
        return roleList;
    }

    /**
     * Sets the role list.
     *
     * @param roleList the new role list
     */
    public void setRoleList(final List<Authority> roleList) {
        this.roleList = roleList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
     */
    @Override
    public Long getId() {
        return id;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the label fr.
     *
     * @return the label fr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * Sets the label fr.
     *
     * @param labelFr the new label fr
     */
    public void setLabelFr(final String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * Gets the label en.
     *
     * @return the label en
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * Sets the label en.
     *
     * @param labelEn the new label en
     */
    public void setLabelEn(final String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * Gets the checks if is final.
     *
     * @return the checks if is final
     */
    public Boolean getIsFinal() {
        return isFinal;
    }

    /**
     * Sets the checks if is final.
     *
     * @param isFinal the new checks if is final
     */
    public void setIsFinal(final Boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Gets the file items list.
     *
     * @return the file items list
     */
    public List<FileItem> getFileItemsList() {
        return fileItemsList;
    }

    /**
     * Sets the file items list.
     *
     * @param fileItemsList the new file items list
     */
    public void setFileItemsList(final List<FileItem> fileItemsList) {
        this.fileItemsList = fileItemsList;
    }

    /**
     * Gets the to step flows list.
     *
     * @return the to step flows list
     */
    public List<Flow> getToStepFlowsList() {
        return toStepFlowsList;
    }

    /**
     * Sets the to step flows list.
     *
     * @param toStepFlowsList the new to step flows list
     */
    public void setToStepFlowsList(final List<Flow> toStepFlowsList) {
        this.toStepFlowsList = toStepFlowsList;
    }

    /**
     * Gets the from step flows list.
     *
     * @return the from step flows list
     */
    public List<Flow> getFromStepFlowsList() {
        return fromStepFlowsList;
    }

    /**
     * Sets the from step flows list.
     *
     * @param fromStepFlowsList the new from step flows list
     */
    public void setFromStepFlowsList(final List<Flow> fromStepFlowsList) {
        this.fromStepFlowsList = fromStepFlowsList;
    }

    /**
     * Gets the step code.
     *
     * @return the step code
     */
    public StepCode getStepCode() {
        return stepCode;
    }

    /**
     * Sets the step code.
     *
     * @param stepCode the new step code
     */
    public void setStepCode(final StepCode stepCode) {
        this.stepCode = stepCode;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Step [id=");
        builder.append(id);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelEn=");
        builder.append(labelEn);
        builder.append(", isFinal=");
        builder.append(isFinal);
        builder.append(", stepCode=");
        builder.append(stepCode);
        builder.append("]");
        return builder.toString();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Step)) {
            return false;
        }
        final Step other = (Step) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

}
