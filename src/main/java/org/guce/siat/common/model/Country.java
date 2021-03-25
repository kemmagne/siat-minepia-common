package org.guce.siat.common.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Country.
 */
@Entity
@Table(name = "REP_COUNTRY")
@XmlRootElement
public class Country implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The country id alpha2.
     */
    @Id
    @Basic(optional = false)
    @Column(name = "COUNTRY_ID_ALPHA2")
    private String countryIdAlpha2;

    /**
     * The country id num.
     */
    @Column(name = "COUNTRY_ID_NUM")
    private String countryIdNum;

    /**
     * The country id alpha3.
     */
    @Column(name = "COUNTRY_ID_ALPHA3")
    private String countryIdAlpha3;

    /**
     * The language code3.
     */
    @Column(name = "LANGUAGE_CODE3")
    private String languageCode3;

    /**
     * The country name.
     */
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    /**
     * The country name fr.
     */
    @Column(name = "COUNTRY_NAME_FR")
    private String countryNameFr;

    /**
     * The country name cpl.
     */
    @Column(name = "COUNTRY_NAME_CPL")
    private String countryNameCpl;

    /**
     * The country name cpl fr.
     */
    @Column(name = "COUNTRY_NAME_CPL_FR")
    private String countryNameCplFr;

    /**
     * Instantiates a new country.
     */
    public Country() {
    }

    public Country(String countryIdAlpha2, String countryName) {
        this.countryIdAlpha2 = countryIdAlpha2;
        this.countryName = countryName;
    }

    /**
     * Gets the country id alpha2.
     *
     * @return the countryIdAlpha2
     */
    public String getCountryIdAlpha2() {
        return countryIdAlpha2;
    }

    /**
     * Sets the country id alpha2.
     *
     * @param countryIdAlpha2 the countryIdAlpha2 to set
     */
    public void setCountryIdAlpha2(final String countryIdAlpha2) {
        this.countryIdAlpha2 = countryIdAlpha2;
    }

    /**
     * Gets the country id num.
     *
     * @return the countryIdNum
     */
    public String getCountryIdNum() {
        return countryIdNum;
    }

    /**
     * Sets the country id num.
     *
     * @param countryIdNum the countryIdNum to set
     */
    public void setCountryIdNum(final String countryIdNum) {
        this.countryIdNum = countryIdNum;
    }

    /**
     * Gets the country id alpha3.
     *
     * @return the countryIdAlpha3
     */
    public String getCountryIdAlpha3() {
        return countryIdAlpha3;
    }

    /**
     * Sets the country id alpha3.
     *
     * @param countryIdAlpha3 the countryIdAlpha3 to set
     */
    public void setCountryIdAlpha3(final String countryIdAlpha3) {
        this.countryIdAlpha3 = countryIdAlpha3;
    }

    /**
     * Gets the language code3.
     *
     * @return the languageCode3
     */
    public String getLanguageCode3() {
        return languageCode3;
    }

    /**
     * Sets the language code3.
     *
     * @param languageCode3 the languageCode3 to set
     */
    public void setLanguageCode3(final String languageCode3) {
        this.languageCode3 = languageCode3;
    }

    /**
     * Gets the country name.
     *
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name.
     *
     * @param countryName the countryName to set
     */
    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    /**
     * Gets the country name fr.
     *
     * @return the countryNameFr
     */
    public String getCountryNameFr() {
        return countryNameFr;
    }

    /**
     * Sets the country name fr.
     *
     * @param countryNameFr the countryNameFr to set
     */
    public void setCountryNameFr(final String countryNameFr) {
        this.countryNameFr = countryNameFr;
    }

    /**
     * Gets the country name cpl.
     *
     * @return the countryNameCpl
     */
    public String getCountryNameCpl() {
        return countryNameCpl;
    }

    /**
     * Sets the country name cpl.
     *
     * @param countryNameCpl the countryNameCpl to set
     */
    public void setCountryNameCpl(final String countryNameCpl) {
        this.countryNameCpl = countryNameCpl;
    }

    /**
     * Gets the country name cpl fr.
     *
     * @return the countryNameCplFr
     */
    public String getCountryNameCplFr() {
        return countryNameCplFr;
    }

    /**
     * Sets the country name cpl fr.
     *
     * @param countryNameCplFr the countryNameCplFr to set
     */
    public void setCountryNameCplFr(final String countryNameCplFr) {
        this.countryNameCplFr = countryNameCplFr;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryIdAlpha2 != null ? countryIdAlpha2.hashCode() : 0);
        return hash;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Country)) {
            return false;
        }
        final Country other = (Country) object;
        if ((this.getCountryIdAlpha2() == null && other.getCountryIdAlpha2() != null)
                || (this.getCountryIdAlpha2() != null && !this.getCountryIdAlpha2().equals(other.getCountryIdAlpha2()))) {
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
        builder.append("Country [countryIdAlpha2=");
        builder.append(countryIdAlpha2);
        builder.append(", countryIdNum=");
        builder.append(countryIdNum);
        builder.append(", countryIdAlpha3=");
        builder.append(countryIdAlpha3);
        builder.append(", languageCode3=");
        builder.append(languageCode3);
        builder.append(", countryName=");
        builder.append(countryName);
        builder.append(", countryNameFr=");
        builder.append(countryNameFr);
        builder.append(", countryNameCpl=");
        builder.append(countryNameCpl);
        builder.append(", countryNameCplFr=");
        builder.append(countryNameCplFr);
        builder.append("]");
        return builder.toString();
    }

}
