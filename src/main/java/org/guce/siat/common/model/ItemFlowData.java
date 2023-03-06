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
 * The Class ItemFlowData.
 */
@Entity
@Table(name = "ITEM_FLOW_DATA")
@XmlRootElement
public class ItemFlowData extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_FLOW_DATA_SEQ")
	@SequenceGenerator(name = "ITEM_FLOW_DATA_SEQ", sequenceName = "ITEM_FLOW_DATA_SEQ", allocationSize = 1)
	private Long id;

	/** The value. */
	@Column(name = "VALUE", length = 1000)
	private String value;

	/** The item flow. */
	@JoinColumn(name = "ITEM_FLOW_ID", referencedColumnName = "ID")
	@ManyToOne
	private ItemFlow itemFlow;

	/** The data type. */
	@JoinColumn(name = "DATA_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne
	private DataType dataType;

	/**
	 * Instantiates a new item flow data.
	 */
	public ItemFlowData()
	{
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
	 *           the id to set
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *           the value to set
	 */
	public void setValue(final String value)
	{
		this.value = value;
	}

	/**
	 * Gets the item flow.
	 *
	 * @return the itemFlow
	 */
	public ItemFlow getItemFlow()
	{
		return itemFlow;
	}

	/**
	 * Sets the item flow.
	 *
	 * @param itemFlow
	 *           the itemFlow to set
	 */
	public void setItemFlow(final ItemFlow itemFlow)
	{
		this.itemFlow = itemFlow;
	}

	/**
	 * Gets the data type.
	 *
	 * @return the dataType
	 */
	public DataType getDataType()
	{
		return dataType;
	}

	/**
	 * Sets the data type.
	 *
	 * @param dataType
	 *           the dataType to set
	 */
	public void setDataType(final DataType dataType)
	{
		this.dataType = dataType;
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
		if (!(object instanceof ItemFlowData))
		{
			return false;
		}
		final ItemFlowData other = (ItemFlowData) object;
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
		builder.append("ItemFlowData [id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
