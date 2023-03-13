/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author boris.tomfeu
 */
@Entity
@Table(name = "DECLARATION")
public class Declaration extends AbstractModel implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECLARATION_SEQ_GEN")
    @SequenceGenerator(name = "DECLARATION_SEQ_GEN", sequenceName = "DECLARATION_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "COMMERCIAL_NAME", length = 35)
    private String commercialName;

    @Column(name = "BOTANIC_NAME", length = 55)
    private String botanicName;

    @Column(name = "REQUIREMENT_REFERENCE", length = 255)
    private String requirementReference;

    @Column(name = "REQUIREMENT_LABEL", length = 1000)
    private String requirementLabel;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getBotanicName() {
        return botanicName;
    }

    public void setBotanicName(String botanicName) {
        this.botanicName = botanicName;
    }

    public String getRequirementReference() {
        return requirementReference;
    }

    public void setRequirementReference(String requirementReference) {
        this.requirementReference = requirementReference;
    }

    public String getRequirementLabel() {
        return requirementLabel;
    }

    public void setRequirementLabel(String requirementLabel) {
        this.requirementLabel = requirementLabel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Declaration other = (Declaration) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
