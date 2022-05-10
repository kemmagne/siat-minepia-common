package org.guce.siat.common.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Entity.
 */
@javax.persistence.Entity
@Table(name = "ENTITY")
@XmlRootElement
public class Entity extends Administration {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The code.
     */
    @Column(name = "CODE")
    protected String code;

    /**
     * The address.
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * The telephone.
     */
    @Column(name = "TELEPHONE")
    private String telephone;

    /**
     * The fax.
     */
    @Column(name = "FAX")
    private String fax;

    /**
     * The bp.
     */
    @Column(name = "BP", length = 10)
    private String bp;

    /**
     * The email.
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * The service.
     */
    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")
    private Service service;
    
    /**
     * The city in french.
     */
    @Column(name = "CITY_FR")
    private String cityFr;
    
    /**
     * The city in english.
     */
    @Column(name = "CITY_EN")
    private String cityEn;
    
    /**
     * The department in french.
     */
    @Column(name = "DEPARTMENT_FR")
    private String departmentFr;
    
    /**
     * The department in english.
     */
    @Column(name = "DEPARTMENT_EN")
    private String departmentEn;
    
    /**
     * The region in french.
     */
    @Column(name = "REGION_FR")
    private String regionFr;
    
    /**
     * The region in english.
     */
    @Column(name = "REGION_EN")
    private String regionEn;

    /**
     * @param id the id to set
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(final String fax) {
        this.fax = fax;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(final Service service) {
        this.service = service;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getCityFr() {
        return cityFr;
    }

    public void setCityFr(String cityFr) {
        this.cityFr = cityFr;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getDepartmentFr() {
        return departmentFr;
    }

    public void setDepartmentFr(String departmentFr) {
        this.departmentFr = departmentFr;
    }

    public String getDepartmentEn() {
        return departmentEn;
    }

    public void setDepartmentEn(String departmentEn) {
        this.departmentEn = departmentEn;
    }

    public String getRegionFr() {
        return regionFr;
    }

    public void setRegionFr(String regionFr) {
        this.regionFr = regionFr;
    }

    public String getRegionEn() {
        return regionEn;
    }

    public void setRegionEn(String regionEn) {
        this.regionEn = regionEn;
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
        if (!(object instanceof Entity)) {
            return false;
        }
        final Entity other = (Entity) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Entity [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", address=");
        builder.append(address);
        builder.append(", telephone=");
        builder.append(telephone);
        builder.append(", fax=");
        builder.append(fax);
        builder.append(", email=");
        builder.append(email);
        builder.append("]");
        return builder.toString();
    }

}
