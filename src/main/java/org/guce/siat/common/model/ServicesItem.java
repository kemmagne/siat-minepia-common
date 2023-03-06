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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.enums.ServiceItemType;


/**
 * The Class ServicesItem.
 */
@Entity
@Table(name = "SERVICES_ITEM")
@XmlRootElement
public class ServicesItem extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICES_ITEM_SEQ")
	@SequenceGenerator(name = "SERVICES_ITEM_SEQ", sequenceName = "SERVICES_ITEM_SEQ", allocationSize = 1)
	private Long id;

	/** The label. */
	@Column(name = "LABEL", nullable = false)
	private String label;

	/** The type. */
	@Column(name = "TYPE", nullable = false)
	private Character type;

	/** The code. */
	@Column(name = "CODE")
	private String code;

	/** The deleted. */
	@Column(name = "DELETED")
	private Boolean deleted;

	/** The service. */
	@JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID", nullable = false)
	@ManyToOne
	private Service service;

	/** The nsh. */
	@ManyToOne
	@JoinColumn(name = "NSH_ID", referencedColumnName = "GOODS_ITEM_CODE", nullable = false)
	private Item nsh;

	/** The snsh. */
	@Transient
	private String snsh;

	/** The snsh label. */
	@Transient
	private String snshLabel;

	/**
	 * Instantiates a new services item.
	 */
	public ServicesItem()
	{
	}

	/**
	 * Gets the nsh.
	 *
	 * @return the nsh
	 */
	public Item getNsh()
	{
		return nsh;
	}

	/**
	 * Sets the nsh.
	 *
	 * @param nsh
	 *           the nsh to set
	 */
	public void setNsh(final Item nsh)
	{
		this.nsh = nsh;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *           the new id
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Character getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *           the new type
	 */
	public void setType(final Character type)
	{
		this.type = type;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *           the new label
	 */
	public void setLabel(final String label)
	{
		this.label = label;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Service getService()
	{
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service
	 *           the service to set
	 */
	public void setService(final Service service)
	{
		this.service = service;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *           the deleted to set
	 */
	public void setDeleted(final Boolean deleted)
	{
		this.deleted = deleted;
	}


	/**
	 * Gets the snsh.
	 *
	 * @return the snsh
	 */
	public String getSnsh()
	{
		if (ServiceItemType.SUBFAMILY.getCode().equals(type.toString()) && code != null)
		{
			return nsh.getGoodsItemCode() + code;
		}
		else
		{
			return nsh.getGoodsItemCode();
		}
	}

	/**
	 * Sets the snsh.
	 *
	 * @param snsh
	 *           the snsh to set
	 */
	public void setSnsh(final String snsh)
	{
		this.snsh = snsh;
	}

	/**
	 * Gets the snsh label.
	 *
	 * @return the snshLabel
	 */
	public String getSnshLabel()
	{
		if (ServiceItemType.SUBFAMILY.getCode().equals(type.toString()) && code != null)
		{
			return nsh.getGoodsItemDesc() + Constants.SLASH_CHARACTER + label;
		}
		else
		{
			return nsh.getGoodsItemDesc();
		}
	}

	/**
	 * Sets the snsh label.
	 *
	 * @param snshLabel
	 *           the snshLabel to set
	 */
	public void setSnshLabel(final String snshLabel)
	{
		this.snshLabel = snshLabel;
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
		if (!(object instanceof ServicesItem))
		{
			return false;
		}
		final ServicesItem other = (ServicesItem) object;
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
		builder.append("ServicesItem [id=");
		builder.append(id);
		builder.append(", label=");
		builder.append(label);
		builder.append(", type=");
		builder.append(type);
		builder.append(", code=");
		builder.append(code);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
