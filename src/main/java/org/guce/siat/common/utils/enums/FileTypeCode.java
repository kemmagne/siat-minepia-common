package org.guce.siat.common.utils.enums;

/**
 * The Enum FileTypeCode.
 */
public enum FileTypeCode {

    //	Autorisations Prealables File Types
    /**
     * The aie minader.
     *///	Autorisations Prealables File Types
    /**
     * The aie minader.
     */
    AIE_MINADER("Sample import authorization (AH)", "Autorisation d'importation des échantillons (AH)"),
    /**
     * The ae minader.
     */
    AE_MINADER("Authorization of trial(AH)", "Autorisation des essaies (AH)"),
    /**
     * The eh minader.
     */
    EH_MINADER("Approval study (AH)", "Etude d'homologation (EH)"),
    /**
     * The as minader.
     */
    AS_MINADER("Special Authorization MINADER", "Autorisation Spéciale MINADER"),
    /**
     * The cat minader.
     */
    CAT_MINADER("Treatments Equipment Certification", "Certification des Appareils de Traitement"),
    /**
     * The pivpsrp minader.
     */
    PIVPSRP_MINADER("Permis of importation of plants and Poducts of phytosanitary industry regulation MINADER",
            "Permis d'importation des végétaux et poduits soumis à la réglémentation phytosanitaire MINADER"),
    /**
     * The di minader.
     */
    DI_MINADER("Import declaration", "Déclaration d'importation"),
    /**
     * The at minsante.
     */
    AT_MINSANTE("MINSANTE Technical Assessment", "Avis Technique MINSANTE"),
    /**
     * The vtp minsante.
     */
    VTP_MINSANTE("Provisional Technical Visa MINSANTE", "Visa technique provisoire MINSANTE"),
    /**
     * The vtd minsante.
     */
    VTD_MINSANTE("Visa Definitive Technology MINSANTE", "Visa Technique Définitif MINSANTE"),
    /**
     * The ai minsante.
     */
    AI_MINSANTE("Import authorization MINSANTE", "Autorisation d'importation MINSANTE"),
    /**
     * The ccs minsante.
     */
    CCS_MINSANTE("MINSANTE health control certificate", "Certificat de contrôle sanitaire MINSANTE"),
    /**
     * The ai minmidt.
     */
    AI_MINMIDT("Import authorization MINMIDT", "Autorisation d'importation MINMIDT"),
    /**
     * The ae minmidt.
     */
    AE_MINMIDT("Export authorization MINMIDT", " Atorisation d'exportation MINMIDT"),
    /**
     * The cea minmidt.
     */
    CEA_MINMIDT("Certificate of Expertise / Authenticity", "Certificat d'Expertise/Authenticité"),
    /**
     * The at minepia.
     */
    AT_MINEPIA("MINEPIA Technical Assessment", "Avis Technique MINEPIA"),
    /**
     * The vt minepia.
     */
    VT_MINEPIA("Visa Technique MINEPIA", "Visa Technique MINEPIA"),
    /**
     * The vt minepded.
     */
    VT_MINEPDED("Visa Technique MINEPDED", "Visa Technique MINEPDED"),
    /**
     * The cp minepded.
     */
    CP_MINEPDED("CPrior consent MINEPDED", "Consentement Préalable MINEPDED"),
    /**
     * The as minfof.
     */
    AS_MINFOF("Export Authorization MINFOF", "Autorisation d'exportation"),
    /**
     * The co minfof foret.
     */
    CO_MINFOF_FORET("Forest Certificate of Origin MINFOF", "Certificat d’Origine de Forêt MINFOF"),
    /**
     * The co minfof faune.
     */
    CO_MINFOF_FAUNE("Wildlife  Certificate of Origin MINFOF", "Certificat d’Origine de Faune MINFOF"),
    /**
     * The lvtb minfof.
     */
    LVTB_MINFOF("Car Transportation letter for the Hood MINFOF", "Lettre de Voiture pour le Transport des Bois MINFOF"),
    /**
     * The bsbe minfof.
     */
    BSBE_MINFOF("Woods Specification Bulletin for Export MINFOF", "Bulletin de Spécification des Bois à l''Export MINFOF"),
    //	Certificat de Controle Technique File Types

    /**
     * The cct ct.
     */
    CCT_CT("Roadworthiness certificate", "Certificat de contrôle technique"),
    /**
     * the cct csv
     */
    CCT_CSV("Veterinary health certificate", "Certificat sanitaire vétérinaire"),
    /**
     * The cct ct e.
     */
    CCT_CT_E("Roadworthiness certificate for export", "Certificat de contrôle technique à l'export"),
    /**
     * The cc ct.
     */
    CC_CT("Certificate of Conformity", "Certificat de conformité"),
    /**
     * The cq ct.
     */
    CQ_CT("Quality certificate", "Certificat de qualité"),
    //	Certificat d'Origine File Types

    /**
     * The co gco.
     */
    CO_GCO("Managing Certificate of Origin", "Gestion de Certificat d’Origine"),
    /**
     * The co gfc.
     */
    CO_GFC("Management Consular File", "Gestion du Fichier Consulaire "),
    //	AFFAIRE MARITIME

    /**
     * The am doi.
     */
    AM_DOI("Import Operations", "Domaine des Opérations à l’Import"),
    /**
     * The am doe.
     */
    AM_DOE("Export Operations", "Domaine des Opérations à l’Export"),
    /**
     * The am manifest.
     */
    AM_MANIFEST("Manifeste", "Manifest"),
    //	FRET TERRESTRE

    /**
     * The ft df.
     */
    FT_DF("Declaration des fret", "Declaration des fret"),
    /**
     * The ft lvi.
     */
    FT_LVI("Lettre de voiture internationale", "Lettre de voiture internationale"),
    //	MONOTORING MINCOMMERCE

    /**
     * The as mincommerce.
     */
    AS_MINCOMMERCE("Special Authorization MINCOMMERCE", "Autorisation Spéciale MINCOMMERCE"),
    /**
     * The fimex.
     */
    FIMEX("Subscription to exporters/importers file process", "Processus d’inscription au fichier des importateurs/exportateurs"),
    /**
     * The di mincommerce.
     */
    DI_MINCOMMERCE("Declarations of Import MINCOMMERCE", "Déclarations d’Importation MINCOMMERCE"),
    /* Inscription au régistre des poids et mesures */
    IRMP_MINCOMMERCE("Iscription au Régistre des Poids et Mesures", "Iscription au Régistre des Poids et Mesures"),
    /**
     * The de mincommerce.
     */
    DE_MINCOMMERCE("Declarations of Export MINCOMMERCE", "Déclarations d’Exportation MINCOMMERCE"),
    /**
     * The idi.
     */
    IDI("Customs charge (Import)", "Imputation Douanière (Import)"),
    /**
     * The ide.
     */
    IDE("Customs charge (Export)", "Imputation Douanière (Export)"),
    /**
     * The fimex wf.
     */
    FIMEX_WF("Subscription to exporters/importers file process", "Processus d’inscription au fichier des importateurs/exportateurs"),
    //	SUIVIE FINANCIERS

    /**
     * The sf gve.
     */
    SF_GVE("Export License", "Licence d’Exportation"),
    /**
     * The sf af.
     */
    SF_AF("Tax benefits", "Avantages fiscaux"),
    /**
     * The sf qf.
     */
    SF_QF("Tax clearance", "Quitus fiscal"),
    /**
     * The sf ar.
     */
    SF_AR("Repatriation certificate", "Attestation de Rapatriement"),
    // CACAO CAFFE

    /**
     * The cc dv.
     */
    CC_DV("Declaration Sale", "Déclaration de Vente"),
    /**
     * The cc de.
     */
    CC_DE("Export Declaration", "Declaration d'Exportation"),
    /**
     * The cc bq.
     */
    CC_BQ("Quality Bulletin", "Bulletin de Qualité"),
    /**
     * The cc cocac.
     */
    CC_COCAC("Certificate of Origin COCOA", "Certificat d'Origine CACAO"),
    /**
     * The cc cocaf.
     */
    CC_COCAF("Certificate of Origin CAFE", "Certificat d'Origine CAFE"),
    /**
     * The alert.
     */
    ALERT("Alerte", "Alert"),
    /**
     * Attestation de traitement
     */
    CCT_CT_E_ATP("Certificate of treatment", "Attestation de traitement"),
    /**
     * Fiche de supervision de traitement phytosanitaire
     */
    CCT_CT_E_FSTP("Supervision file of pytosanitary treatment", "Fiche de supervision de traitement phytosanitaire"),
    /**
     * Procès verbal d'inspection phytosanitaire
     */
    CCT_CT_E_PVI("Phytosanitary inspection report", "Procès verbal d'inspection phytosanitaire"),
    /**
     * Procès verbal d'emptage
     */
    CCT_CT_E_PVE("Potting report", "Procès verbal d'empotage"),
    
    EPHYTO("EPHYTO", "EPHYTO"),
    
    PAYMENT("Payment", "Paiement");

    /**
     * The label en.
     */
    private final String labelEn;

    /**
     * The labl fr.
     */
    private final String lablFr;

    /**
     * Instantiates a new file type code.
     *
     * @param labelEn the label en
     * @param labelFr the label fr
     */
    private FileTypeCode(final String labelEn, final String labelFr) {
        this.labelEn = labelEn;
        this.lablFr = labelFr;

    }

    /**
     * Gets the labl fr.
     *
     * @return the labl fr
     */
    public String getLablFr() {
        return lablFr;
    }

    /**
     * Gets the label en.
     *
     * @return the label en
     */
    public String getLabelEn() {
        return labelEn;
    }

}
