package org.guce.siat.common.model.dto;



/**
 * The Class SubDepartmentDTO.
 */
public class SubDepartmentDTO extends AdministrationDTO
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The abreviation. */
	private String abreviation;

	private OrganismDTO organism;


	/**
	 * Instantiates a new sub department.
	 */
	public SubDepartmentDTO()
	{
	}

	/**
	 * Gets the organism.
	 *
	 * @return the organism
	 */
	public OrganismDTO getOrganism()
	{
		return organism;
	}

	/**
	 * Sets the organism.
	 *
	 * @param organism
	 *           the new organism
	 */
	public void setOrganism(final OrganismDTO organism)
	{
		this.organism = organism;
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
		if (!(object instanceof SubDepartmentDTO))
		{
			return false;
		}
		final SubDepartmentDTO other = (SubDepartmentDTO) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the abreviation.
	 *
	 * @return the abreviation
	 */
	public String getAbreviation()
	{
		return abreviation;
	}

	/**
	 * Sets the abreviation.
	 *
	 * @param abreviation
	 *           the new abreviation
	 */
	public void setAbreviation(final String abreviation)
	{
		this.abreviation = abreviation;
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
		builder.append("SubDepartment [id=");
		builder.append(id);
		builder.append(", labelFr=");
		builder.append(labelFr);
		builder.append(", labelEn=");
		builder.append(labelEn);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
