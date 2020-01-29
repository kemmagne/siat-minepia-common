package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class DataType.
 */
@Entity
@Table(name = "DATA_TYPE")
@XmlRootElement
public class DataType extends AbstractModel implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DATA_TYPE_SEQ")
    @SequenceGenerator(name = "DATA_TYPE_SEQ", sequenceName = "DATA_TYPE_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The label.
     */
    @Column(name = "LABEL", length = 150)
    private String label;

    /**
     * The label.
     */
    @Column(name = "LABEL_EN", length = 150)
    private String labelEn;
    /**
     * The type.
     */
    @Column(name = "TYPE", length = 100)
    private String type;

    /**
     * The required.
     */
    @Column(name = "REQUIRED")
    private Boolean required;

    /**
     * The object type.
     */
    @Column(name = "OBJECT_TYPE")
    private String objectType;

    /**
     * The flow.
     */
    @JoinColumn(name = "FLOW_ID", referencedColumnName = "ID")
    @ManyToOne
    private Flow flow;

    /**
     * Others props of data type.
     */
    @Column(name = "PROPS", length = 1000)
    private String props;

    /**
     * unique code of data type.
     */
    @Column(name = "CODE", length = 100)
    private String code;

    @Column(name = "DISABLED")
    private Boolean disabled;

    /**
     * Instantiates a new data type.
     */
    public DataType() {
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
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label.
     *
     * @param label the label to set
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Gets the flow.
     *
     * @return the flow
     */
    public Flow getFlow() {
        return flow;
    }

    /**
     * Sets the flow.
     *
     * @param flow the flow to set
     */
    public void setFlow(final Flow flow) {
        this.flow = flow;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the required.
     *
     * @return the required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     * Sets the required.
     *
     * @param required the required to set
     */
    public void setRequired(final Boolean required) {
        this.required = required;
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

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
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
     * Sets the label en.
     *
     * @param code the unique reference to datatype
     */
    public void setCode(String code) {
        this.code = code;
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

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof DataType)) {
            return false;
        }
        final DataType other = (DataType) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /**
     * Gets the object type.
     *
     * @return the object type
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Sets the object type.
     *
     * @param objectType the new object type
     */
    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DataType [id=");
        builder.append(id);
        builder.append(", label=");
        builder.append(label);
        builder.append("]");
        return builder.toString();
    }

}
