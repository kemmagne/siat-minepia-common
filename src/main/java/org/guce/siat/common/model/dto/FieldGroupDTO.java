package org.guce.siat.common.model.dto;

import java.io.Serializable;


/**
 * The Class FieldGroup.
 */
public class FieldGroupDTO implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9024094519041273961L;

	/** The id. */
	private Long id;

	/** The label fr. */
	private String labelFr;

	/** The label en. */
	private String labelEn;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the label fr.
	 *
	 * @return the labelFr
	 */
	public String getLabelFr()
	{
		return labelFr;
	}

	/**
	 * Sets the label fr.
	 *
	 * @param labelFr
	 *           the labelFr to set
	 */
	public void setLabelFr(final String labelFr)
	{
		this.labelFr = labelFr;
	}

	/**
	 * Gets the label en.
	 *
	 * @return the labelEn
	 */
	public String getLabelEn()
	{
		return labelEn;
	}

	/**
	 * Sets the label en.
	 *
	 * @param labelEn
	 *           the labelEn to set
	 */
	public void setLabelEn(final String labelEn)
	{
		this.labelEn = labelEn;
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
		if (!(object instanceof FieldGroupDTO))
		{
			return false;
		}
		final FieldGroupDTO other = (FieldGroupDTO) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

    @Override
    public String toString() {
        return "FieldGroup{" + "id=" + id + ", labelFr=" + labelFr + ", labelEn=" + labelEn + '}';
    }

}
