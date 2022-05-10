package org.guce.siat.common.model.dto;



/**
 * The Class ServiceDTO.
 */
public class ServiceDTO extends AdministrationDTO
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The abreviation. */
	private String abreviation;

	/** The head service. */
	
	/** The sub department. */
	private SubDepartmentDTO subDepartment;

	/**
	 * Instantiates a new service.
	 */
	public ServiceDTO()
	{
	}

	/**
	 * Gets the sub department.
	 *
	 * @return the sub department
	 */
	public SubDepartmentDTO getSubDepartment()
	{
		return subDepartment;
	}

	/**
	 * Sets the sub department.
	 *
	 * @param subDepartment
	 *           the new sub department
	 */
	public void setSubDepartment(final SubDepartmentDTO subDepartment)
	{
		this.subDepartment = subDepartment;
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
		if (!(object instanceof ServiceDTO))
		{
			return false;
		}
		final ServiceDTO other = (ServiceDTO) object;
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
	 * @see org.guce.siat.common.model.AdministrationDTO#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("Service [abreviation=");
		builder.append(abreviation);
		builder.append(", subDepartment=");
		builder.append(subDepartment.getAbreviation());
		builder.append("]");
		return builder.toString();
	}


}
