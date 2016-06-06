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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.utils.enums.CompanyType;




/**
 * The Class Company.
 */

@Entity
@Table(name = "COMPANY")
@XmlRootElement
public class Company extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
	@SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", allocationSize = 1)
	private Long id;

	/** The recipient first name. */
	@Column(name = "RECIPIENT_FIRST_NAME")
	private String recipientFirstName;

	/** The company name. */
	@Column(name = "COMPANY_NAME")
	private String companyName;

	/** The num contribuable. */
	@Column(name = "NUM_CONTRIBUABLE")
	private String numContribuable;

	/** The profession. */
	@Column(name = "PROFESSION")
	private String profession;

	/** The first address. */
	@Column(name = "FIRST_ADDRESS")
	private String firstAddress;

	/** The second address. */
	@Column(name = "SECOND_ADDRESS")
	private String secondAddress;

	/** The postal code. */
	@Column(name = "POSTALCODE")
	private String postalCode;

	/** The city. */
	@Column(name = "CITY")
	private String city;

	/** The fax. */
	@Column(name = "FAX")
	private String fax;

	/** The phone. */
	@Column(name = "PHONE")
	private String phone;

	/** The mobile. */
	@Column(name = "MOBILE")
	private String mobile;

	/** The email. */
	@Column(name = "EMAIL")
	private String email;

	/** The web site. */
	@Column(name = "WEB_SITE")
	private String webSite;

	/** The company type. */
	@Enumerated(EnumType.STRING)
	@Column(name = "COMPANY_TYPE")
	private CompanyType companyType;

	/** The country. */
	@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID_ALPHA2")
	@ManyToOne
	private Country country;

	/** The operator file list. */
	@OneToMany(mappedBy = "client")
	private List<File> operatorFileList;

	/** The full address. */
	@Transient
	private String fullAddress;

	/* BEGIN Agrément commerce: commerce approval */

	/** The commerce approval registration number file. */
	@Column(name = "NUM_INS_FI_AGR_COM")
	private String commerceApprovalRegistrationNumberFile;

	/** The commerce approval obtained date. */
	@Column(name = "DATE_OBT_AGR_COM")
	private String commerceApprovalObtainedDate;

	/** The commerce approval validity date. */
	@Column(name = "DATE_VAL_AGR_COM")
	private String commerceApprovalValidityDate;

	/** The commerce approval number trader map. */
	@Column(name = "NUM_CARTE_COM_AGR_COM")
	private String commerceApprovalNumberTraderMap;

	/* END Agrément commerce: commerce approval */

	/* BEGIN Agrément métier: business approval */

	/** The business approval type. */
	@Column(name = "TYPE_AGREMENT_METIER")
	private String businessApprovalType;

	/** The approval number business approval. */
	@Column(name = "NUMERO_AGREMENT_METIER")
	private String businessApprovalRegistrationNumber;

	/** The approval date business approval. */
	@Column(name = "DATE_AGREMENT_METIER")
	private String businessApprovalDate;

	/** The business approval validity date. */
	@Column(name = "DATE_VALIDITE_METIER")
	private String businessApprovalValidityDate;

	/* END Agrément métier: business approval */

	/* BEGIN Permis: permit */

	/** The permit number. */
	@Column(name = "NUMERO_PERMIS")
	private String permitNumber;

	@Column(name = "PERMIT_OBTAINING_DATE")
	private String permitObtainingDate;

	@Column(name = "PERMIT_APPROVAL_DATE")
	private String permitApprovalDate;

	@Column(name = "PERMIT_TYPE")
	private String permitType;

	/* END Permis: permit */

	/** The trade register number. */
	@Column(name = "NUMERO_REGISTRE_COMMERCE")
	private String tradeRegisterNumber;

	/** The c ni. */
	@Column(name = "CNI")
	private String cNI;

	/**
	 * Instantiates a new company.
	 */
	public Company()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the recipient first name.
	 *
	 * @return the recipient first name
	 */
	public String getRecipientFirstName()
	{
		return recipientFirstName;
	}

	/**
	 * Sets the recipient first name.
	 *
	 * @param recipientFirstName
	 *           the new recipient first name
	 */
	public void setRecipientFirstName(final String recipientFirstName)
	{
		this.recipientFirstName = recipientFirstName;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName
	 *           the new company name
	 */
	public void setCompanyName(final String companyName)
	{
		this.companyName = companyName;
	}

	/**
	 * Gets the num contribuable.
	 *
	 * @return the num contribuable
	 */
	public String getNumContribuable()
	{
		return numContribuable;
	}

	/**
	 * Sets the num contribuable.
	 *
	 * @param numContribuable
	 *           the new num contribuable
	 */
	public void setNumContribuable(final String numContribuable)
	{
		this.numContribuable = numContribuable;
	}

	/**
	 * Gets the profession.
	 *
	 * @return the profession
	 */
	public String getProfession()
	{
		return profession;
	}

	/**
	 * Sets the profession.
	 *
	 * @param profession
	 *           the new profession
	 */
	public void setProfession(final String profession)
	{
		this.profession = profession;
	}

	/**
	 * Gets the first address.
	 *
	 * @return the firstAddress
	 */
	public String getFirstAddress()
	{
		return firstAddress;
	}

	/**
	 * Sets the first address.
	 *
	 * @param firstAddress
	 *           the firstAddress to set
	 */
	public void setFirstAddress(final String firstAddress)
	{
		this.firstAddress = firstAddress;
	}

	/**
	 * Gets the second address.
	 *
	 * @return the secondAddress
	 */
	public String getSecondAddress()
	{
		return secondAddress;
	}

	/**
	 * Sets the second address.
	 *
	 * @param secondAddress
	 *           the secondAddress to set
	 */
	public void setSecondAddress(final String secondAddress)
	{
		this.secondAddress = secondAddress;
	}

	/**
	 * Gets the web site.
	 *
	 * @return the webSite
	 */
	public String getWebSite()
	{
		return webSite;
	}

	/**
	 * Sets the web site.
	 *
	 * @param webSite
	 *           the webSite to set
	 */
	public void setWebSite(final String webSite)
	{
		this.webSite = webSite;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode()
	{
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode
	 *           the new postal code
	 */
	public void setPostalCode(final String postalCode)
	{
		this.postalCode = postalCode;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *           the new city
	 */
	public void setCity(final String city)
	{
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *           the new country
	 */
	public void setCountry(final Country country)
	{
		this.country = country;
	}

	/**
	 * Gets the fax.
	 *
	 * @return the fax
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * Sets the fax.
	 *
	 * @param fax
	 *           the new fax
	 */
	public void setFax(final String fax)
	{
		this.fax = fax;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *           the new phone
	 */
	public void setPhone(final String phone)
	{
		this.phone = phone;
	}

	/**
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public String getMobile()
	{
		return mobile;
	}

	/**
	 * Sets the mobile.
	 *
	 * @param mobile
	 *           the new mobile
	 */
	public void setMobile(final String mobile)
	{
		this.mobile = mobile;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *           the new email
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Gets the operator file list.
	 *
	 * @return the operator file list
	 */
	public List<File> getOperatorFileList()
	{
		return operatorFileList;
	}

	/**
	 * Sets the operator file list.
	 *
	 * @param operatorFileList
	 *           the new operator file list
	 */
	public void setOperatorFileList(final List<File> operatorFileList)
	{
		this.operatorFileList = operatorFileList;
	}

	/**
	 * Gets the company type.
	 *
	 * @return the companyType
	 */
	public CompanyType getCompanyType()
	{
		return companyType;
	}

	/**
	 * Sets the company type.
	 *
	 * @param companyType
	 *           the companyType to set
	 */
	public void setCompanyType(final CompanyType companyType)
	{
		this.companyType = companyType;
	}

	/**
	 * Gets the full address.
	 *
	 * @return the full address
	 */
	public String getFullAddress()
	{
		final StringBuilder returnedString = new StringBuilder();
		if (firstAddress != null)
		{
			returnedString.append(' ' + firstAddress);
		}
		if (secondAddress != null)
		{
			returnedString.append(' ' + secondAddress);
		}
		if (postalCode != null)
		{
			returnedString.append(' ' + postalCode);
		}
		if (city != null)
		{
			returnedString.append(' ' + city);
		}
		if (country != null)
		{
			returnedString.append(country.getCountryName());
		}
		return returnedString.toString();
	}

	/**
	 * Sets the full adress.
	 *
	 * @param fullAdress
	 *           the new full adress
	 */
	public void setFullAdress(final String fullAdress)
	{
		this.fullAddress = fullAdress;
	}

	/**
	 * Gets the commerce approval registration number file.
	 *
	 * @return the commerceApprovalRegistrationNumberFile
	 */
	public String getCommerceApprovalRegistrationNumberFile()
	{
		return commerceApprovalRegistrationNumberFile;
	}

	/**
	 * Sets the commerce approval registration number file.
	 *
	 * @param commerceApprovalRegistrationNumberFile
	 *           the commerceApprovalRegistrationNumberFile to set
	 */
	public void setCommerceApprovalRegistrationNumberFile(final String commerceApprovalRegistrationNumberFile)
	{
		this.commerceApprovalRegistrationNumberFile = commerceApprovalRegistrationNumberFile;
	}

	/**
	 * Gets the commerce approval obtained date.
	 *
	 * @return the commerceApprovalObtainedDate
	 */
	public String getCommerceApprovalObtainedDate()
	{
		return commerceApprovalObtainedDate;
	}

	/**
	 * Sets the commerce approval obtained date.
	 *
	 * @param commerceApprovalObtainedDate
	 *           the commerceApprovalObtainedDate to set
	 */
	public void setCommerceApprovalObtainedDate(final String commerceApprovalObtainedDate)
	{
		this.commerceApprovalObtainedDate = commerceApprovalObtainedDate;
	}

	/**
	 * Gets the commerce approval validity date.
	 *
	 * @return the commerceApprovalValidityDate
	 */
	public String getCommerceApprovalValidityDate()
	{
		return commerceApprovalValidityDate;
	}

	/**
	 * Sets the commerce approval validity date.
	 *
	 * @param commerceApprovalValidityDate
	 *           the commerceApprovalValidityDate to set
	 */
	public void setCommerceApprovalValidityDate(final String commerceApprovalValidityDate)
	{
		this.commerceApprovalValidityDate = commerceApprovalValidityDate;
	}

	/**
	 * Gets the commerce approval number trader map.
	 *
	 * @return the commerceApprovalNumberTraderMap
	 */
	public String getCommerceApprovalNumberTraderMap()
	{
		return commerceApprovalNumberTraderMap;
	}

	/**
	 * Sets the commerce approval number trader map.
	 *
	 * @param commerceApprovalNumberTraderMap
	 *           the commerceApprovalNumberTraderMap to set
	 */
	public void setCommerceApprovalNumberTraderMap(final String commerceApprovalNumberTraderMap)
	{
		this.commerceApprovalNumberTraderMap = commerceApprovalNumberTraderMap;
	}

	/**
	 * Gets the business approval type.
	 *
	 * @return the businessApprovalType
	 */
	public String getBusinessApprovalType()
	{
		return businessApprovalType;
	}

	/**
	 * Sets the business approval type.
	 *
	 * @param businessApprovalType
	 *           the businessApprovalType to set
	 */
	public void setBusinessApprovalType(final String businessApprovalType)
	{
		this.businessApprovalType = businessApprovalType;
	}

	/**
	 * Gets the business approval registration number.
	 *
	 * @return the businessApprovalRegistrationNumber
	 */
	public String getBusinessApprovalRegistrationNumber()
	{
		return businessApprovalRegistrationNumber;
	}

	/**
	 * Sets the business approval registration number.
	 *
	 * @param businessApprovalRegistrationNumber
	 *           the businessApprovalRegistrationNumber to set
	 */
	public void setBusinessApprovalRegistrationNumber(final String businessApprovalRegistrationNumber)
	{
		this.businessApprovalRegistrationNumber = businessApprovalRegistrationNumber;
	}

	/**
	 * Gets the business approval date.
	 *
	 * @return the businessApprovalDate
	 */
	public String getBusinessApprovalDate()
	{
		return businessApprovalDate;
	}

	/**
	 * Sets the business approval date.
	 *
	 * @param businessApprovalDate
	 *           the businessApprovalDate to set
	 */
	public void setBusinessApprovalDate(final String businessApprovalDate)
	{
		this.businessApprovalDate = businessApprovalDate;
	}

	/**
	 * Gets the business approval validity date.
	 *
	 * @return the businessApprovalValidityDate
	 */
	public String getBusinessApprovalValidityDate()
	{
		return businessApprovalValidityDate;
	}

	/**
	 * Sets the business approval validity date.
	 *
	 * @param businessApprovalValidityDate
	 *           the businessApprovalValidityDate to set
	 */
	public void setBusinessApprovalValidityDate(final String businessApprovalValidityDate)
	{
		this.businessApprovalValidityDate = businessApprovalValidityDate;
	}

	/**
	 * Gets the permit number.
	 *
	 * @return the permitNumber
	 */
	public String getPermitNumber()
	{
		return permitNumber;
	}

	/**
	 * Sets the permit number.
	 *
	 * @param permitNumber
	 *           the permitNumber to set
	 */
	public void setPermitNumber(final String permitNumber)
	{
		this.permitNumber = permitNumber;
	}

	/**
	 * @return the permitObtainingDate
	 */
	public String getPermitObtainingDate()
	{
		return permitObtainingDate;
	}

	/**
	 * @param permitObtainingDate
	 *           the permitObtainingDate to set
	 */
	public void setPermitObtainingDate(final String permitObtainingDate)
	{
		this.permitObtainingDate = permitObtainingDate;
	}

	/**
	 * @return the permitApprovalDate
	 */
	public String getPermitApprovalDate()
	{
		return permitApprovalDate;
	}

	/**
	 * @param permitApprovalDate
	 *           the permitApprovalDate to set
	 */
	public void setPermitApprovalDate(final String permitApprovalDate)
	{
		this.permitApprovalDate = permitApprovalDate;
	}

	/**
	 * Gets the permit type.
	 *
	 * @return the permitType
	 */
	public String getPermitType()
	{
		return permitType;
	}

	/**
	 * Sets the permit type.
	 *
	 * @param permitType
	 *           the permitType to set
	 */
	public void setPermitType(final String permitType)
	{
		this.permitType = permitType;
	}

	/**
	 * Gets the trade register number.
	 *
	 * @return the tradeRegisterNumber
	 */
	public String getTradeRegisterNumber()
	{
		return tradeRegisterNumber;
	}

	/**
	 * Sets the trade register number.
	 *
	 * @param tradeRegisterNumber
	 *           the tradeRegisterNumber to set
	 */
	public void setTradeRegisterNumber(final String tradeRegisterNumber)
	{
		this.tradeRegisterNumber = tradeRegisterNumber;
	}

	/**
	 * Gets the c ni.
	 *
	 * @return the cNI
	 */
	public String getcNI()
	{
		return cNI;
	}

	/**
	 * Sets the c ni.
	 *
	 * @param cNI
	 *           the cNI to set
	 */
	public void setcNI(final String cNI)
	{
		this.cNI = cNI;
	}

	/**
	 * @param fullAddress
	 *           the fullAddress to set
	 */
	public void setFullAddress(final String fullAddress)
	{
		this.fullAddress = fullAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
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
	public boolean equals(final Object object)
	{
		if (!(object instanceof Company))
		{
			return false;
		}
		final Company other = (Company) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
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
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("Company [id=");
		builder.append(id);
		builder.append(", recipientFirstName=");
		builder.append(recipientFirstName);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", numContribuable=");
		builder.append(numContribuable);
		builder.append(", address=");
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", city=");
		builder.append(city);
		builder.append(", fax=");
		builder.append(fax);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
