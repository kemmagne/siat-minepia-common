package org.guce.siat.common.model.dto;

import java.util.List;

/**
 * The Class OrganismDTO.
 */
public class OrganismDTO extends AdministrationDTO {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The abreviation.
     */
    private String abreviation;

    /**
     * The admin organism.
     */
    private UserDTO adminOrganism;

    /**
     * The manager.
     */
    private UserDTO manager;

    /**
     * The ministry.
     */
    private MinistryDTO ministry;

    /**
     * Instantiates a new organism.
     */
    public OrganismDTO() {
    }

    /**
     * Instantiates a new organism.
     *
     * @param id the id
     */
    public OrganismDTO(final Long id) {
        this.id = id;
    }

    /**
     * Gets the abreviation.
     *
     * @return the abreviation
     */
    public String getAbreviation() {
        return abreviation;
    }

    /**
     * Sets the abreviation.
     *
     * @param abreviation the abreviation to set
     */
    public void setAbreviation(final String abreviation) {
        this.abreviation = abreviation;
    }

    /**
     * Gets the ministry.
     *
     * @return the ministry
     */
    public MinistryDTO getMinistry() {
        return ministry;
    }

    /**
     * Sets the ministry.
     *
     * @param ministry the ministry to set
     */
    public void setMinistry(final MinistryDTO ministry) {
        this.ministry = ministry;
    }

    /**
     * Gets the admin organism.
     *
     * @return the adminOrganism
     */
    public UserDTO getAdminOrganism() {
        return adminOrganism;
    }

    /**
     * Sets the admin organism.
     *
     * @param adminOrganism the adminOrganism to set
     */
    public void setAdminOrganism(final UserDTO adminOrganism) {
        this.adminOrganism = adminOrganism;
    }

    /**
     * Gets the manager.
     *
     * @return the manager
     */
    public UserDTO getManager() {
        return manager;
    }

    /**
     * Sets the manager.
     *
     * @param manager the manager to set
     */
    public void setManager(final UserDTO manager) {
        this.manager = manager;
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
        if (!(object instanceof OrganismDTO)) {
            return false;
        }
        final OrganismDTO other = (OrganismDTO) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
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
        builder.append("Organism [id=");
        builder.append(id);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelEn=");
        builder.append(labelEn);
        builder.append(", abreviation=");
        builder.append(abreviation);
        builder.append(", deleted=");
        builder.append(deleted);
        builder.append("]");
        return builder.toString();
    }

    public static class UserDTO {

        private String login;

        /**
         * The first name.
         */
        private String firstName;

        /**
         * The last name.
         */
        private String lastName;
        private Long id;

        public Long getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    }

}
