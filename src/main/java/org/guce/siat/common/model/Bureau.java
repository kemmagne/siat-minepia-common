package org.guce.siat.common.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.utils.enums.BureauType;


/**
 * The Class Bureau.
 */
@javax.persistence.Entity
@Table(name = "BUREAU")
@XmlRootElement
public class Bureau extends Entity
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The head office. */
	@Transient
	private User headOffice;

	/** The bureau type. */
	@Enumerated(EnumType.STRING)
	@Column(name = "BUREAU_TYPE")
	private BureauType bureauType;

	/**
	 * Instantiates a new bureau.
	 */
	public Bureau()
	{
	}

	/**
	 * Gets the head office.
	 *
	 * @return the headOffice
	 */
	public User getHeadOffice()
	{
		return headOffice;
	}

	/**
	 * Sets the head office.
	 *
	 * @param headOffice
	 *           the headOffice to set
	 */
	public void setHeadOffice(final User headOffice)
	{
		this.headOffice = headOffice;
	}

	/**
	 * Gets the bureau type.
	 *
	 * @return the bureauType
	 */
	public BureauType getBureauType()
	{
		return bureauType;
	}

	/**
	 * Sets the bureau type.
	 *
	 * @param bureauType
	 *           the bureauType to set
	 */
	public void setBureauType(final BureauType bureauType)
	{
		this.bureauType = bureauType;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.Entity#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("bureau [id=");
		builder.append(super.getId());
		builder.append(", code=");
		builder.append(super.getCode());
		builder.append(", labelFr=");
		builder.append(super.getLabelFr());
		builder.append(", labelEn=");
		builder.append(super.getLabelEn());
		builder.append(", address=");
		builder.append(super.getAddress());
		builder.append(", telephone=");
		builder.append(super.getTelephone());
		builder.append(", fax=");
		builder.append(super.getFax());
		builder.append(", email=");
		builder.append(super.getEmail());
		builder.append(", deleted=");
		builder.append(super.getDeleted());
		builder.append("]");
		return builder.toString();
	}

}
