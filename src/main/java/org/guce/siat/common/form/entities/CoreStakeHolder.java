package org.guce.siat.common.form.entities;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Koufana Crepin Sosthene
 */
//@Entity
//@Table(name = "CORE_STAKEHOLDER")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "ENTITY_TYPE")
//@Cacheable(false)
//@XmlRootElement
//@XmlAccessorType(XmlAccessType.PROPERTY)
//@XmlType(name = "", propOrder = {
//    "taxPayerNumber",
//    "name",
//    "aortic",
//    "address",
//    "phone",
//    "mobile",
//    "fax",
//    "otherContact"
//})
public class CoreStakeHolder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CORE_STAKEHOLDER_SEQ", sequenceName = "CORE_STAKEHOLDER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CORE_STAKEHOLDER_SEQ")
    @Column(name = "STAKEHOLDER_ID", length = 35)
    private String stakeholderId;

    @Column(name = "TAX_PAYER_NUMBER", length = 35)
    private String taxPayerNumber;
    @Column(name = "NAME", length = 35)

    private String name;
    @Column(name = "AORTIC", length = 255)
    private String aortic;

    @Embedded
    private CoreAddress address;
    
    @AttributeOverrides({
        @AttributeOverride(name = "indicatif",
                column = @Column(name = "PHONE_COUNTRY_CODE")),
        @AttributeOverride(name = "numero",
                column = @Column(name = "PHONE_NUMBER"))
    })
    @Embedded
    private CorePhone phone;
    
    @AttributeOverrides({
        @AttributeOverride(name = "indicatif",
                column = @Column(name = "PHONE_MOBILE_COUNTRY_CODE")),
        @AttributeOverride(name = "numero",
                column = @Column(name = "PHONE_MOBILE_NUMBER"))
    })
    @Embedded
    private CorePhone mobile;
    @AttributeOverrides({
        @AttributeOverride(name = "indicatif",
                column = @Column(name = "FAX_COUNTRY_CODE")),
        @AttributeOverride(name = "numero",
                column = @Column(name = "FAX_NUMBER"))
    })
    @Embedded
    private CorePhone fax;

    @Column(name = "OTHER_CONTACT", length = 255)
    private String otherContact;

    public CoreStakeHolder() {
        mobile = new CorePhone();
        phone = new CorePhone();
        fax = new CorePhone();
        address = new CoreAddress();
    }

    @XmlTransient
    public String getStakeholderId() {
        return stakeholderId;
    }

    public void setStakeholderId(String stakeholderId) {
        this.stakeholderId = stakeholderId;
    }

    @XmlElement(name = "NUMERO_CONTRIBUABLE")
    public String getTaxPayerNumber() {
        return taxPayerNumber;
    }

    public void setTaxPayerNumber(String taxPayerNumber) {
        this.taxPayerNumber = taxPayerNumber;
    }

    @XmlElement(name = "RAISON_SOCIALE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "AORTIC")
    public String getAortic() {
        return aortic;
    }

    public void setAortic(String aortic) {
        this.aortic = aortic;
    }

    @XmlElement(name = "ADRESSE")
    public CoreAddress getAddress() {
        if (address == null) {
            address = new CoreAddress();
        }
        return address;
    }

    public void setAddress(CoreAddress address) {
        this.address = address;
    }

    @XmlElement(name = "TELEPHONE_FIXE")
    public CorePhone getPhone() {
        if (phone == null) {
            phone = new CorePhone();
        }
        return phone;
    }

    public void setPhone(CorePhone phone) {
        this.phone = phone;
    }

    @XmlElement(name = "TELEPHONE_MOBILE")
    public CorePhone getMobile() {
        if (mobile == null) {
            mobile = new CorePhone();
        }
        return mobile;
    }

    public void setMobile(CorePhone mobile) {
        this.mobile = mobile;
    }

    @XmlElement(name = "FAX")
    public CorePhone getFax() {
        if (fax == null) {
            fax = new CorePhone();
        }
        return fax;
    }

    public void setFax(CorePhone fax) {
        this.fax = fax;
    }

    @XmlElement(name = "AUTRE_CONTACT")
    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stakeholderId != null ? stakeholderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CoreStakeHolder) {
        } else {
            return false;
        }
        CoreStakeHolder other = (CoreStakeHolder) object;
        return !((this.stakeholderId == null && other.stakeholderId != null) || (this.stakeholderId != null && !this.stakeholderId.equals(other.stakeholderId)));
    }

    @Override
    public String toString() {
        return "org.guce.core.entities.CoreEntity[ id=" + stakeholderId + " ]";
    }

}
