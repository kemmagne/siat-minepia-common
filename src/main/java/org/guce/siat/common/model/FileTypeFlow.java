package org.guce.siat.common.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


/**
 * The Class FileTypeFlow.
 */
@Entity
@Table(name = "FILE_TYPE_FLOW")
@AssociationOverrides(
{ @AssociationOverride(name = "pk.flow", joinColumns = @JoinColumn(name = "FLOW_ID")),
		@AssociationOverride(name = "pk.fileType", joinColumns = @JoinColumn(name = "FILE_TYPE_ID")) })
public class FileTypeFlow implements java.io.Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7842837160586610901L;

	/** The pk. */
	@EmbeddedId
	private FileTypeFlowId pk = new FileTypeFlowId();

	/** The label fr. */
	@Column(name = "LABEL_FR", nullable = false, length = 100)
	private String labelFr;

	/** The label en. */
	@Column(name = "LABEL_EN", nullable = false, length = 100)
	private String labelEn;




	/**
	 * Gets the pk.
	 *
	 * @return the pk
	 */
	public FileTypeFlowId getPk()
	{
		return pk;
	}


	/**
	 * Sets the pk.
	 *
	 * @param pk
	 *           the new pk
	 */
	public void setPk(final FileTypeFlowId pk)
	{
		this.pk = pk;
	}


	/**
	 * Gets the label fr.
	 *
	 * @return the label fr
	 */
	public String getLabelFr()
	{
		return labelFr;
	}


	/**
	 * Sets the label fr.
	 *
	 * @param labelFr
	 *           the new label fr
	 */
	public void setLabelFr(final String labelFr)
	{
		this.labelFr = labelFr;
	}


	/**
	 * Gets the label en.
	 *
	 * @return the label en
	 */
	public String getLabelEn()
	{
		return labelEn;
	}

	/**
	 * Sets the label en.
	 *
	 * @param labelEn
	 *           the new label en
	 */
	public void setLabelEn(final String labelEn)
	{
		this.labelEn = labelEn;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		final FileTypeFlow that = (FileTypeFlow) o;
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
		{
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
