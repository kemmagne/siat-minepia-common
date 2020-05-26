package org.guce.siat.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.utils.enums.FinalDecisionType;

/**
 * The Class File.
 */
@Entity
@Table(name = "FILES")
@XmlRootElement
public class File extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ")
    @SequenceGenerator(name = "FILE_SEQ", sequenceName = "FILE_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The reference SIAT.
     */
    @Column(name = "REFERENCE_SIAT", unique = true, length = 35)
    private String referenceSiat;

    /**
     * The reference GUCE.
     */
    @Column(name = "REFERENCE_GUCE", nullable = false, unique = true, length = 35)
    private String referenceGuce;

    /**
     * The numero demande.
     */
    @Column(name = "NUMERO_DEMANDE", length = 35)
    private String numeroDemande;

    /**
     * The numero dossier.
     */
    @Column(name = "NUMERO_DOSSIER", length = 35, nullable = false)
    private String numeroDossier;

    /**
     * The file type guce.
     */
    @Column(name = "FILE_TYPE_GUCE", nullable = false, length = 35)
    private String fileTypeGuce;

    /**
     * The emetteur.
     */
    @Column(name = "EMETTEUR", length = 35, nullable = false)
    private String emetteur;

    /**
     * The destinataire.
     */
    @Column(name = "DESTINATAIRE", length = 35, nullable = false)
    private String destinataire;

    //	Procédure ANNULATION
    /**
     * The reference guce annulation.
     */
    @Column(name = "REFERENCE_GUCE_ANNULATION", unique = true, length = 35)
    private String referenceGuceAnnulation;

    /**
     * The numero demande.
     */
    @Column(name = "NUMERO_DEMANDE_ANNULATION", length = 35)
    private String numeroDemandeAnnulation;

    /**
     * The file type guce.
     */
    @Column(name = "FILE_TYPE_GUCE_ANNULATION", length = 35)
    private String fileTypeGuceAnnulation;

    /**
     * The reference guce paiement.
     */
    // procédure paiement
    @Column(name = "REFERENCE_GUCE_PAIEMENT", unique = true, length = 35)
    private String referenceGucePaiement;

    /**
     * The numero demande paiement.
     */
    @Column(name = "NUMERO_DEMANDE_PAIEMENT", length = 35)
    private String numeroDemandePaiement;

    /**
     * The file type guce paiement.
     */
    @Column(name = "FILE_TYPE_GUCE_PAIEMENT", length = 35)
    private String fileTypeGucePaiement;
    /**
     * The created date.
     */
    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * The signature date.
     */
    @Column(name = "SIGNATURE_DATE")
    @Temporal(TemporalType.DATE)
    private Date signatureDate;

    /**
     * The validity date.
     */
    @Column(name = "VALIDITY_DATE ")
    @Temporal(TemporalType.DATE)
    private Date validityDate;

    /**
     * The bureau.
     */
    @ManyToOne
    @JoinColumn(name = "BUREAU_ID", referencedColumnName = "ID")
    private Bureau bureau;

    /**
     * The assigned user.
     */
    @ManyToOne
    @JoinColumn(name = "ASSIGNED_USER_ID", referencedColumnName = "ID")
    private User assignedUser;

    /**
     * The operator.
     */
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    private Company client;

    /**
     * The file type.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
    private FileType fileType;

    /**
     * The attachments list.
     */
    @OneToMany(mappedBy = "file")
    private List<Attachment> attachmentsList;

    /**
     * The file items list.
     */
    @OneToMany(mappedBy = "file", cascade = CascadeType.PERSIST)
    private List<FileItem> fileItemsList;

    /**
     * The file administration list.
     */
    @OneToMany(mappedBy = "file", cascade = CascadeType.PERSIST)
    private List<FileAdministration> fileAdministrationsList;

    /**
     * The file field value list.
     */
    @OneToMany(mappedBy = "primaryKey.file", cascade = CascadeType.PERSIST)
    private List<FileFieldValue> fileFieldValueList;

    /**
     * The repetablefile field value list.
     */
    @Transient
    private List<FileFieldValue> repeatablefileFieldValueList;

    /**
     * The non repeatablefile field value list.
     */
    @Transient
    private List<FileFieldValue> nonRepeatablefileFieldValueList;

    /**
     * The recommandations list.
     */
    @OneToMany(mappedBy = "file")
    private List<Recommandation> recommandationsList;

    /**
     * The country of origin.
     */
    @ManyToOne
    @JoinColumn(name = "COUNTRY_OF_ORIGIN")
    private Country countryOfOrigin;

    /**
     * The country of destination.
     */
    @ManyToOne
    @JoinColumn(name = "COUNTRY_OF_DESTINATION")
    private Country countryOfDestination;

    /**
     * The country of provenance.
     */
    @ManyToOne
    @JoinColumn(name = "COUNTRY_OF_PROVENANCE")
    private Country countryOfProvenance;

    /**
     * The decision type.
     */
    @Transient
    private FinalDecisionType decisionType;

    /**
     * the signatory of file
     */
    @ManyToOne
    @JoinColumn(name = "SIGNATORY_USER_ID")
    private User signatory;
    /**
     * The last decision date.
     */
    @Column(name = "LAST_DECISION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDecisionDate;

    @ManyToOne
    @JoinColumn(name = "PARENT_FILE_ID", referencedColumnName = "ID")
    private File parent;

    @OneToMany(mappedBy = "parent")
    private List<File> childrenList;

    @Column(name = "NUMERO_DOSSIER_BASE")
    private String numeroDossierBase;

    @OneToMany(mappedBy = "file", orphanRemoval = true)
    private List<Container> containers;

    /**
     * Gets the id.
     *
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    @Override
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
    public Bureau getBureau() {
        return bureau;
    }

    /**
     * Sets the bureau.
     *
     * @param bureau the new bureau
     */
    public void setBureau(final Bureau bureau) {
        this.bureau = bureau;
    }

    /**
     * Gets the file type.
     *
     * @return the fileType
     */
    public FileType getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the fileType to set
     */
    public void setFileType(final FileType fileType) {
        this.fileType = fileType;
    }

    /**
     * Gets the attachments list.
     *
     * @return the attachmentsList
     */
    public List<Attachment> getAttachmentsList() {
        return attachmentsList;
    }

    /**
     * Sets the attachments list.
     *
     * @param attachmentsList the attachmentsList to set
     */
    public void setAttachmentsList(final List<Attachment> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }

    /**
     * Gets the file items list.
     *
     * @return the fileItemsList
     */
    public List<FileItem> getFileItemsList() {
        return fileItemsList;
    }

    /**
     * Sets the file items list.
     *
     * @param fileItemsList the fileItemsList to set
     */
    public void setFileItemsList(final List<FileItem> fileItemsList) {
        this.fileItemsList = fileItemsList;
    }

    /**
     * Gets the file administrations list.
     *
     * @return the fileAdministrationsList
     */
    public List<FileAdministration> getFileAdministrationsList() {
        return fileAdministrationsList;
    }

    /**
     * Sets the file administrations list.
     *
     * @param fileAdministrationsList the fileAdministrationsList to set
     */
    public void setFileAdministrationsList(List<FileAdministration> fileAdministrationsList) {
        this.fileAdministrationsList = fileAdministrationsList;
    }

    /**
     * Gets the assigned user.
     *
     * @return the assignedUser
     */
    public User getAssignedUser() {
        return assignedUser;
    }

    /**
     * Sets the assigned user.
     *
     * @param assignedUser the assignedUser to set
     */
    public void setAssignedUser(final User assignedUser) {
        this.assignedUser = assignedUser;
    }

    /**
     * Gets the client.
     *
     * @return the client
     */
    public Company getClient() {
        return client;
    }

    /**
     * Sets the client.
     *
     * @param client the client to set
     */
    public void setClient(final Company client) {
        this.client = client;
    }

    /**
     * Gets the file field value list.
     *
     * @return the fileFieldValueList
     */
    public List<FileFieldValue> getFileFieldValueList() {
        return fileFieldValueList;
    }

    /**
     * Sets the file field value list.
     *
     * @param fileFieldValueList the fileFieldValueList to set
     */
    public void setFileFieldValueList(final List<FileFieldValue> fileFieldValueList) {
        this.fileFieldValueList = fileFieldValueList;
    }

    /**
     * Gets the recommandations list.
     *
     * @return the recommandationsList
     */
    public List<Recommandation> getRecommandationsList() {
        return recommandationsList;
    }

    /**
     * Sets the recommandations list.
     *
     * @param recommandationsList the recommandationsList to set
     */
    public void setRecommandationsList(final List<Recommandation> recommandationsList) {
        this.recommandationsList = recommandationsList;
    }

    /**
     * Gets the repeatablefile field value list.
     *
     * @return the repeatablefile field value list
     */
    @JsonIgnore
    public List<FileFieldValue> getRepeatablefileFieldValueList() {

        if (CollectionUtils.isEmpty(repeatablefileFieldValueList)) {
            repeatablefileFieldValueList = new ArrayList<>();
            for (final FileFieldValue fileFieldValue : getFileFieldValueList()) {
                if (fileFieldValue.getFileField().getRepeatable()) {
                    repeatablefileFieldValueList.add(fileFieldValue);
                }
            }
        }
        return repeatablefileFieldValueList;

    }

    /**
     * Sets the repeatablefile field value list.
     *
     * @param repeatablefileFieldValueList the new repeatablefile field value
     * list
     */
    public void setRepeatablefileFieldValueList(final List<FileFieldValue> repeatablefileFieldValueList) {
        this.repeatablefileFieldValueList = repeatablefileFieldValueList;
    }

    @JsonIgnore
    /**
     * Gets the non repeatablefile field value list.
     *
     * @return the non repeatablefile field value list
     */
    public List<FileFieldValue> getNonRepeatablefileFieldValueList() {

        if (CollectionUtils.isEmpty(nonRepeatablefileFieldValueList)) {
            nonRepeatablefileFieldValueList = new ArrayList<FileFieldValue>();
            for (final FileFieldValue fileFieldValue : getFileFieldValueList()) {
                if (!fileFieldValue.getFileField().getRepeatable()) {
                    nonRepeatablefileFieldValueList.add(fileFieldValue);
                }
            }
        }
        return nonRepeatablefileFieldValueList;

    }

    /**
     * Sets the non repeatablefile field value list.
     *
     * @param nonRepeatablefileFieldValueList the new non repeatablefile field
     * value list
     */
    public void setNonRepeatablefileFieldValueList(final List<FileFieldValue> nonRepeatablefileFieldValueList) {
        this.nonRepeatablefileFieldValueList = nonRepeatablefileFieldValueList;
    }

    /**
     * Gets the country of origin.
     *
     * @return the countryOfOrigin
     */
    public Country getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Sets the country of origin.
     *
     * @param countryOfOrigin the countryOfOrigin to set
     */
    public void setCountryOfOrigin(final Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * Gets the country of destination.
     *
     * @return the countryOfDestination
     */
    public Country getCountryOfDestination() {
        return countryOfDestination;
    }

    /**
     * Sets the country of destination.
     *
     * @param countryOfDestination the countryOfDestination to set
     */
    public void setCountryOfDestination(final Country countryOfDestination) {
        this.countryOfDestination = countryOfDestination;
    }

    /**
     * Gets the country of provenance.
     *
     * @return the countryOfProvenance
     */
    public Country getCountryOfProvenance() {
        return countryOfProvenance;
    }

    /**
     * Sets the country of provenance.
     *
     * @param countryOfProvenance the countryOfProvenance to set
     */
    public void setCountryOfProvenance(final Country countryOfProvenance) {
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
     * Gets the decision type.
     *
     * @return the decision type
     */
    public FinalDecisionType getDecisionType() {
        return decisionType;
    }

    /**
     * Sets the decision type.
     *
     * @param decisionType the new decision type
     */
    public void setDecisionType(final FinalDecisionType decisionType) {
        this.decisionType = decisionType;
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

    public User getSignatory() {
        return signatory;
    }

    public void setSignatory(User signatory) {
        this.signatory = signatory;
    }

    public Date getLastDecisionDate() {
        return lastDecisionDate;
    }

    public void setLastDecisionDate(Date lastDecisionDate) {
        this.lastDecisionDate = lastDecisionDate;
    }

    public File getParent() {
        return parent;
    }

    public void setParent(File parent) {
        this.parent = parent;
    }

    public List<File> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<File> childrenList) {
        this.childrenList = childrenList;
    }

    public String getNumeroDossierBase() {
        return numeroDossierBase;
    }

    public void setNumeroDossierBase(String numeroDossierBase) {
        this.numeroDossierBase = numeroDossierBase;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
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
        if (!(object instanceof File)) {
            return false;
        }
        final File other = (File) object;
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
        builder.append("	]");
        return builder.toString();
    }

    @PrePersist
    private void prePersist() {
        if (lastDecisionDate == null) {
            lastDecisionDate = Calendar.getInstance().getTime();
        }
    }

}
