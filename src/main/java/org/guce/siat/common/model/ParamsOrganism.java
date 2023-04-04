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
 * The Class ParamsOrganism.
 */
@Entity
@Table(name = "PARAMS_ORGANISM")
@XmlRootElement
public class ParamsOrganism extends AbstractModel implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMS_ORGANISM_SEQ")
    @SequenceGenerator(name = "PARAMS_ORGANISM_SEQ", sequenceName = "PARAMS_ORGANISM_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The value.
     */
    @Column(name = "VALUE")
    private String value;

    /**
     * The param.
     */
    @JoinColumn(name = "PARAM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Params param;

    /**
     * The organism id.
     */
    @ManyToOne
    private Organism organism;

    /**
     * Instantiates a new params organisms.
     */
    public ParamsOrganism() {
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
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Gets the param.
     *
     * @return the param
     */
    public Params getParam() {
        return param;
    }

    /**
     * Sets the param.
     *
     * @param param the param to set
     */
    public void setParam(final Params param) {
        this.param = param;
    }

    /**
     * Gets the organism.
     *
     * @return the organism
     */
    public Organism getOrganism() {
        return organism;
    }

    /**
     * Sets the organism.
     *
     * @param organism the organism to set
     */
    public void setOrganism(final Organism organism) {
        this.organism = organism;
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
        if (!(object instanceof ParamsOrganism)) {
            return false;
        }
        final ParamsOrganism other = (ParamsOrganism) object;
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
        builder.append("ParamsOrganism [id=");
        builder.append(id);
        builder.append(", value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }

}
