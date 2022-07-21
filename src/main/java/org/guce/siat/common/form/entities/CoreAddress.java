package org.guce.siat.common.form.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.guce.siat.common.model.Country;

/**
 *
 * @author Koufana Crepin
 */
@Embeddable
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "", propOrder = {
    "address1",
    "address2",
    "address3",
    "address4",
    "address5",
    "postofficebox",
    "country",
    "city",
    "email",
    "website"
})
public class CoreAddress implements Serializable {

    @Column(name = "ADDRESS1", length = 255)
    private String address1;
    @Column(name = "ADDRESS2", length = 255)
    private String address2;
    @Column(name = "ADDRESS3", length = 255)
    private String address3;
    @Column(name = "ADDRESS4", length = 255)
    private String address4;
    @Column(name = "ADDRESS5", length = 255)
    private String address5;
    @Column(name = "POBOX", length = 35)
    private String postofficebox;
    @Column(name = "WEBSITE", length = 50)
    private String website;
    @Column(name = "EMAIL", length = 50)
    private String email;

    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID_ALPHA2")
    @ManyToOne
    private Country country;

    @Column(name = "CITY", length = 80)
    private String city;

    public CoreAddress() {
        country = new Country();
        country.setCountryIdAlpha2("CM");
    }

    @XmlElement(name = "ADRESSE1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @XmlElement(name = "ADRESSE2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @XmlElement(name = "ADRESSE3")
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @XmlElement(name = "ADRESSE4")
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @XmlElement(name = "ADRESSE5")
    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    @XmlElement(name = "BP")
    public String getPostofficebox() {
        return postofficebox;
    }

    public void setPostofficebox(String postofficebox) {
        this.postofficebox = postofficebox;
    }

    @XmlElement(name = "SITE_WEB")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @XmlElement(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "PAYS_ADRESSE")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @XmlElement(name = "VILLE")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
