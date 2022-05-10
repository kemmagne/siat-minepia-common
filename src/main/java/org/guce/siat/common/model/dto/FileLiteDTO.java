package org.guce.siat.common.model.dto;

import java.io.Serializable;

/**
 * The Class FileDTO.
 */
public class FileLiteDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -6412240945201954603L;

    /**
     * The id.
     */
    private Long id;


    /**
     * The numero demande.
     */
    private String numeroDemande;

    /**
     * The numero dossier.
     */
    private String numeroDossier;

   
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


}
