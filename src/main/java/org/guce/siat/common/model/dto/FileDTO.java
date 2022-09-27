package org.guce.siat.common.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FileDTO.
 */
public class FileDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -6412240945201954603L;

    /**
     * The id.
     */
    private Long id;

    /**
     * The reference SIAT.
     */
    private String referenceSiat;

    /**
     * The reference GUCE.
     */
    private String referenceGuce;

    /**
     * The numero demande.
     */
    private String numeroDemande;

    /**
     * The numero dossier.
     */
    private String numeroDossier;

    /**
     * The file type guce.
     */
    private String fileTypeGuce;

    /**
     * The emetteur.
     */
    private String emetteur;

    /**
     * The destinataire.
     */
    private String destinataire;

    //	Procédure ANNULATION
    /**
     * The reference guce annulation.
     */
    private String referenceGuceAnnulation;

    /**
     * The numero demande.
     */
    private String numeroDemandeAnnulation;

    /**
     * The file type guce.
     */
    private String fileTypeGuceAnnulation;

    /**
     * The reference guce paiement.
     */
    // procédure paiement
    private String referenceGucePaiement;

    /**
     * The numero demande paiement.
     */
    private String numeroDemandePaiement;

    /**
     * The file type guce paiement.
     */
    private String fileTypeGucePaiement;
    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The signature date.
     */
    private Date signatureDate;

    /**
     * The validity date.
     */
    private Date validityDate;

    /**
     * The bureau.
     */
    private BureauDTO bureau;

    /**
     * The assigned user.
     */
    private UserDTO assignedUser;

    /**
     * The operator.
     */
    private CompanyDTO client;

    /**
     * The file type.
     */
    private FileTypeDTO fileType;

    /**
     * The country of origin.
     */
    private CountryDTO countryOfOrigin;

    /**
     * The country of destination.
     */
    private CountryDTO countryOfDestination;

    /**
     * The country of provenance.
     */
    private CountryDTO countryOfProvenance;

    /**
     * the signatory of file
     */
    private UserDTO signatory;

    /**
     * The last decision date.
     */
    private Date lastDecisionDate;

    private FileDTO parent;

    private String numeroDossierBase;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the reference siat.
     *
     * @return the referenceSiat
     */
    public String getReferenceSiat() {
        return referenceSiat;
    }

    /**
     * Sets the reference siat.
     *
     * @param referenceSiat the referenceSiat to set
     */
    public void setReferenceSiat(final String referenceSiat) {
        this.referenceSiat = referenceSiat;
    }

    /**
     * Gets the reference guce.
     *
     * @return the referenceGuce
     */
    public String getReferenceGuce() {
        return referenceGuce;
    }

    /**
     * Sets the reference guce.
     *
     * @param referenceGuce the referenceGuce to set
     */
    public void setReferenceGuce(final String referenceGuce) {
        this.referenceGuce = referenceGuce;
    }

    /**
     * Gets the numero demande.
     *
     * @return the numeroDemande
     */
    public String getNumeroDemande() {
        return numeroDemande;
    }

    /**
     * Sets the numero demande.
     *
     * @param numeroDemande the numeroDemande to set
     */
    public void setNumeroDemande(final String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    /**
     * Gets the numero dossier.
     *
     * @return the numeroDossier
     */
    public String getNumeroDossier() {
        return numeroDossier;
    }

    /**
     * Sets the numero dossier.
     *
     * @param numeroDossier the numeroDossier to set
     */
    public void setNumeroDossier(final String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the bureau.
     *
     * @return the bureau
     */
    public BureauDTO getBureau() {
        return bureau;
    }

    /**
     * Sets the bureau.
     *
     * @param bureau the new bureau
     */
    public void setBureau(final BureauDTO bureau) {
        this.bureau = bureau;
    }

    /**
     * Gets the file type.
     *
     * @return the fileType
     */
    public FileTypeDTO getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the fileType to set
     */
    public void setFileType(final FileTypeDTO fileType) {
        this.fileType = fileType;
    }

    /**
     * Gets the assigned user.
     *
     * @return the assignedUser
     */
    public UserDTO getAssignedUser() {
        return assignedUser;
    }

    /**
     * Sets the assigned user.
     *
     * @param assignedUser the assignedUser to set
     */
    public void setAssignedUser(final UserDTO assignedUser) {
        this.assignedUser = assignedUser;
    }

    /**
     * Gets the client.
     *
     * @return the client
     */
    public CompanyDTO getClient() {
        return client;
    }

    /**
     * Sets the client.
     *
     * @param client the client to set
     */
    public void setClient(final CompanyDTO client) {
        this.client = client;
    }

    /**
     * Gets the country of origin.
     *
     * @return the countryOfOrigin
     */
    public CountryDTO getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Sets the country of origin.
     *
     * @param countryOfOrigin the countryOfOrigin to set
     */
    public void setCountryOfOrigin(final CountryDTO countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * Gets the country of destination.
     *
     * @return the countryOfDestination
     */
    public CountryDTO getCountryOfDestination() {
        return countryOfDestination;
    }

    /**
     * Sets the country of destination.
     *
     * @param countryOfDestination the countryOfDestination to set
     */
    public void setCountryOfDestination(final CountryDTO countryOfDestination) {
        this.countryOfDestination = countryOfDestination;
    }

    /**
     * Gets the country of provenance.
     *
     * @return the countryOfProvenance
     */
    public CountryDTO getCountryOfProvenance() {
        return countryOfProvenance;
    }

    /**
     * Sets the country of provenance.
     *
     * @param countryOfProvenance the countryOfProvenance to set
     */
    public void setCountryOfProvenance(final CountryDTO countryOfProvenance) {
        this.countryOfProvenance = countryOfProvenance;
    }

    /**
     * Gets the file type guce.
     *
     * @return the fileTypeGuce
     */
    public String getFileTypeGuce() {
        return fileTypeGuce;
    }

    /**
     * Sets the file type guce.
     *
     * @param fileTypeGuce the fileTypeGuce to set
     */
    public void setFileTypeGuce(final String fileTypeGuce) {
        this.fileTypeGuce = fileTypeGuce;
    }

    /**
     * Gets the emetteur.
     *
     * @return the emetteur
     */
    public String getEmetteur() {
        return emetteur;
    }

    /**
     * Sets the emetteur.
     *
     * @param emetteur the emetteur to set
     */
    public void setEmetteur(final String emetteur) {
        this.emetteur = emetteur;
    }

    /**
     * Gets the destinataire.
     *
     * @return the destinataire
     */
    public String getDestinataire() {
        return destinataire;
    }

    /**
     * Sets the destinataire.
     *
     * @param destinataire the destinataire to set
     */
    public void setDestinataire(final String destinataire) {
        this.destinataire = destinataire;
    }

    /**
     * Gets the numero demande annulation.
     *
     * @return the numeroDemandeAnnulation
     */
    public String getNumeroDemandeAnnulation() {
        return numeroDemandeAnnulation;
    }

    /**
     * Gets the reference guce annulation.
     *
     * @return the referenceGuceAnnulation
     */
    public String getReferenceGuceAnnulation() {
        return referenceGuceAnnulation;
    }

    /**
     * Sets the reference guce annulation.
     *
     * @param referenceGuceAnnulation the referenceGuceAnnulation to set
     */
    public void setReferenceGuceAnnulation(final String referenceGuceAnnulation) {
        this.referenceGuceAnnulation = referenceGuceAnnulation;
    }

    /**
     * Gets the file type guce annulation.
     *
     * @return the fileTypeGuceAnnulation
     */
    public String getFileTypeGuceAnnulation() {
        return fileTypeGuceAnnulation;
    }

    /**
     * Sets the file type guce annulation.
     *
     * @param fileTypeGuceAnnulation the fileTypeGuceAnnulation to set
     */
    public void setFileTypeGuceAnnulation(final String fileTypeGuceAnnulation) {
        this.fileTypeGuceAnnulation = fileTypeGuceAnnulation;
    }

    /**
     * Sets the numero demande annulation.
     *
     * @param numeroDemandeAnnulation the numeroDemandeAnnulation to set
     */
    public void setNumeroDemandeAnnulation(final String numeroDemandeAnnulation) {
        this.numeroDemandeAnnulation = numeroDemandeAnnulation;
    }

    /**
     * Gets the reference guce paiement.
     *
     * @return the reference guce paiement
     */
    public String getReferenceGucePaiement() {
        return referenceGucePaiement;
    }

    /**
     * Sets the reference guce paiement.
     *
     * @param referenceGucePaiement the new reference guce paiement
     */
    public void setReferenceGucePaiement(final String referenceGucePaiement) {
        this.referenceGucePaiement = referenceGucePaiement;
    }

    /**
     * Gets the numero demande paiement.
     *
     * @return the numero demande paiement
     */
    public String getNumeroDemandePaiement() {
        return numeroDemandePaiement;
    }

    /**
     * Sets the numero demande paiement.
     *
     * @param numeroDemandePaiement the new numero demande paiement
     */
    public void setNumeroDemandePaiement(final String numeroDemandePaiement) {
        this.numeroDemandePaiement = numeroDemandePaiement;
    }

    /**
     * Gets the file type guce paiement.
     *
     * @return the file type guce paiement
     */
    public String getFileTypeGucePaiement() {
        return fileTypeGucePaiement;
    }

    /**
     * Sets the file type guce paiement.
     *
     * @param fileTypeGucePaiement the new file type guce paiement
     */
    public void setFileTypeGucePaiement(final String fileTypeGucePaiement) {
        this.fileTypeGucePaiement = fileTypeGucePaiement;
    }

    /**
     * Gets the signature date.
     *
     * @return the signatureDate
     */
    public Date getSignatureDate() {
        return signatureDate;
    }

    /**
     * Sets the signature date.
     *
     * @param signatureDate the signatureDate to set
     */
    public void setSignatureDate(final Date signatureDate) {
        this.signatureDate = signatureDate;
    }

    /**
     * Gets the validity date.
     *
     * @return the validityDate
     */
    public Date getValidityDate() {
        return validityDate;
    }

    /**
     * Sets the validity date.
     *
     * @param validityDate the validityDate to set
     */
    public void setValidityDate(final Date validityDate) {
        this.validityDate = validityDate;
    }

    public UserDTO getSignatory() {
        return signatory;
    }

    public void setSignatory(UserDTO signatory) {
        this.signatory = signatory;
    }

    public Date getLastDecisionDate() {
        return lastDecisionDate;
    }

    public void setLastDecisionDate(Date lastDecisionDate) {
        this.lastDecisionDate = lastDecisionDate;
    }

    public FileDTO getParent() {
        return parent;
    }

    public void setParent(FileDTO parent) {
        this.parent = parent;
    }

    public String getNumeroDossierBase() {
        return numeroDossierBase;
    }

    public void setNumeroDossierBase(String numeroDossierBase) {
        this.numeroDossierBase = numeroDossierBase;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof FileDTO)) {
            return false;
        }
        final FileDTO other = (FileDTO) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("File[	referenceSiat= ");
        builder.append(referenceSiat);
        builder.append(", referenceGuce= ");
        builder.append(referenceGuce);
        builder.append(", numeroDemande=");
        builder.append(numeroDemande);
        builder.append(", numeroDossier=");
        builder.append(numeroDossier);
        builder.append(", createdDate=");
        builder.append(createdDate);
        builder.append(", fileType=");
        builder.append(fileType.getLabelFr());
        builder.append(", bureau=");
        builder.append(bureau.getCode());
        builder.append("	]");
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

    public static class CountryDTO {

        private String countryIdAlpha2;

        private String countryIdNum;

        private String countryIdAlpha3;

        public String getCountryIdAlpha2() {
            return countryIdAlpha2;
        }

        public void setCountryIdAlpha2(String countryIdAlpha2) {
            this.countryIdAlpha2 = countryIdAlpha2;
        }

        public String getCountryIdNum() {
            return countryIdNum;
        }

        public void setCountryIdNum(String countryIdNum) {
            this.countryIdNum = countryIdNum;
        }

        public String getCountryIdAlpha3() {
            return countryIdAlpha3;
        }

        public void setCountryIdAlpha3(String countryIdAlpha3) {
            this.countryIdAlpha3 = countryIdAlpha3;
        }

    }

    public static class CompanyDTO {

        private Long id;

        private String recipientFirstName;

        private String companyName;

        private String numContribuable;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRecipientFirstName() {
            return recipientFirstName;
        }

        public void setRecipientFirstName(String recipientFirstName) {
            this.recipientFirstName = recipientFirstName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getNumContribuable() {
            return numContribuable;
        }

        public void setNumContribuable(String numContribuable) {
            this.numContribuable = numContribuable;
        }

    }
}
