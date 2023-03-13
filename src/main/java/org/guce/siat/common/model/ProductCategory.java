package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Product Category.
 */
@Entity
@Table(name = "PRODUCT_CATEGORY")
@XmlRootElement
public class ProductCategory extends AbstractModel implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_CATEGORY_SEQ")
    @SequenceGenerator(name = "PRODUCT_CATEGORY_SEQ", sequenceName = "PRODUCT_CATEGORY_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The coce.
     */
    @Column(name = "CODE")
    private String code;

    /**
     * The label.
     */
    @Column(name = "LABEL")
    private String label;

    /**
     * The description.
     */
    @Column(name = "DESCRIPTION", length = 3000)
    private String description;

    /**
     * The active.
     */
    @Column(name = "ACTIVE")
    private boolean active;   
    

    /**
     * Instantiates a new product category.
     */
    public ProductCategory() {

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        if (!(object instanceof ProductCategory)) {
            return false;
        }
        final ProductCategory other = (ProductCategory) object;
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
        builder.append("ProductCategory [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", label=");
        builder.append(label);
        builder.append(", description=");
        builder.append(description);
        builder.append(']');
        return builder.toString();
    }

}
