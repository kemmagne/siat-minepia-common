package org.guce.siat.common.utils.enums;

/**
 * The Enum StepCode.
 */
public enum StepCode {

    // CCT Step Code
    ST_CT_EPHYTO("Consultation des phyto recu depuis la plateForm"),
    /**
     * The S t_ c t_01.
     */
    ST_CT_01("Demande d'inspection"),
    /**
     * The S t_ c t_02.
     */
    ST_CT_02("Etude de recevabilité suite demande d'inspection"),
    /**
     * The S t_ c t_03.
     */
    ST_CT_03("Cotation"),
    /**
     * The S t_ c t_04.
     */
    ST_CT_04("Étude Approfondie"),
    /**
     * The S t_ c t_05.
     */
    ST_CT_05("Fin Rejet"),
    /**
     * The S t_ c t_06.
     */
    ST_CT_06("Fin CCT"),
    /**
     * The S t_ c t_07.
     */
    ST_CT_07("Demande RDV Visite"),
    /**
     * The S t_ c t_08.
     */
    ST_CT_08("Demande de Confirmation RDV Visite à quai"),
    /**
     * The S t_ c t_09.
     */
    ST_CT_09("Demande de Régularisation"),
    /**
     * The S t_ c t_10.
     */
    ST_CT_10("Etude de recevabilité suite Demande RDV Visite"),
    /**
     * The S t_ c t_11.
     */
    ST_CT_11("Affectation RDV Chez Déclarant"),
    /**
     * The S t_ c t_12.
     */
    ST_CT_12("Inspection et Prélèvement Chez Déclarant"),
    /**
     * The S t_ c t_13.
     */
    ST_CT_13("Décision Suite Contôle"),
    /**
     * The S t_ c t_14.
     */
    ST_CT_14("Analyse"),
    /**
     * The S t_ c t_15.
     */
    ST_CT_15("Traitement"),
    /**
     * The S t_ c t_16.
     */
    ST_CT_16("Etude de recevabilité suite Confirmation Visite à Quai"),
    /**
     * The S t_ c t_17.
     */
    ST_CT_17("Affectation RDV Visite à Quai"),
    /**
     * The S t_ c t_18.
     */
    ST_CT_18("Confirmation RDV Visite à Quai"),
    /**
     * The S t_ c t_19.
     */
    ST_CT_19("Inspection et Prélèvement à Quai"),
    /**
     * The S t_ c t_20.
     */
    ST_CT_20("Etude de recevabilité suite Régularisation"),
    /**
     * The S t_ c t_21.
     */
    ST_CT_21("Demande de Réexamen"),
    /**
     * The S t_ c t_22.
     */
    ST_CT_22("Etude de recevabilité suite Demande de Réexamen"),
    /**
     * The S t_ c t_23.
     */
    ST_CT_23("Confirmation de Déstruction"),
    /**
     * The S t_ c t_24.
     */
    ST_CT_24("Etude de recevabilité suite Confirmation de Déstruction"),
    /**
     * The S t_ c t_25.
     */
    ST_CT_25("Traitement RDD"),
    /**
     * The S t_ c t_26.
     */
    ST_CT_26("Fin RDD"),
    /**
     * The S t_ c t_27.
     */
    ST_CT_27("Demande d'annulation"),
    /**
     * The S t_ c t_28.
     */
    ST_CT_28("Etude demande d'annulation"),
    /**
     * The S t_ c t_29.
     */
    ST_CT_29("Fin Annulation"),
    /**
     * The S t_ c t_31.
     */
    ST_CT_31("Signature Suite CCT (EA)"),
    /**
     * The S t_ c t_32.
     */
    ST_CT_32("Signature Suite RDD(EA)"),
    /**
     * The S t_ c t_33.
     */
    ST_CT_33("Signature Suite RDD Final (EA)"),
    /**
     * The S t_ c t_34.
     */
    ST_CT_34("Signature Suite MEC (Régularisation)"),
    /**
     * The S t_ c t_35.
     */
    ST_CT_35("Signature Suite Visite à Quai"),
    /**
     * The S t_ c t_36.
     */
    ST_CT_36("Signature Suite APE"),
    /**
     * The S t_ c t_37.
     */
    ST_CT_37("Signature Suite Annulation"),
    /**
     * The S t_ c t_38.
     */
    ST_CT_38("Signature Suite CCT (DSC)"),
    /**
     * The S t_ c t_39.
     */
    ST_CT_39("Signature Suite RDD(DSC)"),
    /**
     * The S t_ c t_40.
     */
    ST_CT_40("Signature Suite RDD Final (DSC)"),
    /**
     * The S t_ c t_41.
     */
    ST_CT_41("Signature Suite Validation RDD"),
    /**
     * The S t_ c t_42.
     */
    ST_CT_42("Encaissement"),
    //

    /**
     *
     */
    /**
     * The S t_ c o_65.
     */
    ST_CT_43("Demande de Traitement"),
    ST_CT_44("Recevabilité - Demande de Traitement"),
    ST_CT_45("Demande de supervision"),
    ST_CT_46("Recevabilité - Demande de supervision"),
    ST_CT_47("Cotation"),
    ST_CT_48("Confirmation RDV pour traitement"),
    //
    ST_CT_49("Confirmation RDV pour inspection"),
    //
    ST_CT_51("Demande de modification"),
    ST_CT_52("Recevabilité - Demande de modification"),
    ST_CT_53("Cotation - Demande de modification"),
    ST_CT_54("Rejet demande de modification"),
    ST_CT_55("Traitement demande de modification"),
    ST_CT_56("Signature demande de modification"),
    // The S t_ c t for payment

    /**
     * The S t_ c t_57.
     */
    ST_CT_57("Validation Facture"),
    //
    ST_CT_58("Dépot Dossier Empotage Suite Notification RDV"),
    ST_CT_59("Recevabilité suite notification de RDV pour empotage"),
    ST_CT_60("Validation Facture"),
    ST_CT_61("Encaissement"),
    ST_CT_62("Cotation"),
    ST_CT_63("Validation Procès Verbal Empotage"),
    ST_CT_64("Signature Procès Verbal Empotage"),
    //
    ST_CT_65("Validation pour Signature"),
    ST_CT_66("Signature"),
    ST_CT_67("Signature"),
    //Steps pour le CCS MINSANTE
    ST_CT_CCS_01("Etude approfondie suite demande de modification"),
    ST_CT_CCS_02("Signature suite demande de modification"),
    //
    // AP Step Code
    /**
     * The S t_ a p_42.
     */
    ST_AP_42("Dépôt Dossier AP"),
    /**
     * The S t_ a p_43.
     */
    ST_AP_43("Rejet AP"),
    /**
     * The S t_ a p_44.
     */
    ST_AP_44("Autorisation AP"),
    /**
     * The S t_ a p_45.
     */
    ST_AP_45("Retrait AP"),
    /**
     * The S t_ a p_46.
     */
    ST_AP_46("Recevabilité AP"),
    /**
     * The S t_ a p_47.
     */
    ST_AP_47("Cota_1"),
    /**
     * The S t_ a p_48.
     */
    ST_AP_48("Cota_2/Etude"),
    /**
     * The S t_ a p_49.
     */
    ST_AP_49("Cota_3/Etude"),
    /**
     * The S t_ a p_50.
     */
    ST_AP_50("Cota_4/Etude"),
    /**
     * The S t_ a p_51.
     */
    ST_AP_51("Cota_5/Etude"),
    /**
     * The S t_ a p_52.
     */
    ST_AP_52("Cota_6/Etude"),
    /**
     * The S t_ a p_53.
     */
    ST_AP_53("Etude AP"),
    /**
     * The S t_ a p_54.
     */
    ST_AP_54("Signature AP"),
    /**
     * The S t_ a p_55.
     */
    ST_AP_55("Visa_2"),
    /**
     * The S t_ a p_56.
     */
    ST_AP_56("Visa_3"),
    /**
     * The S t_ a p_57.
     */
    ST_AP_57("Visa_4"),
    /**
     * The S t_ a p_58.
     */
    ST_AP_58("Visa_5"),
    /**
     * The S t_ a p_59.
     */
    ST_AP_59("Visa_6"),
    /**
     * The S t_ a p_60.
     */
    ST_AP_60("Demande d'annulation"),
    /**
     * The S t_ a p_61.
     */
    ST_AP_61("Etude demande d'annulation"),
    /**
     * The S t_ a p_62.
     */
    ST_AP_62("Fin Annulation"),
    /**
     * The S t_ a p_63.
     */
    ST_AP_63("Signature Suite Annulation"),
    /**
     * The S t_ a p_64.
     */
    ST_AP_64("Paiement"),
    ST_AP_65("Paiement"),
    ST_AP_VT1_01("Signature Modification du visa techique"),
    ST_AP_AIM_01("Signature Modification de l'autorisation d'importation"),
    ST_AP_AIM_02("Signature Prorogation de l'autorisation d'importation"),
    
    //Etapes de la procédure permis d'importation
    /* Etape de recevabilité */
    ST_AP_PIM_01("Recevabilité"),
    /* Etape d'étude approfondie */
    ST_AP_PIM_02("Etude approfondie"),
    /* Etape visa 1 */
    ST_AP_PIM_03("Visa 1"),
    /* Etape visa 1 */
    ST_AP_PIM_04("Visa 2"),
    /* Etape visa 1 */
    ST_AP_PIM_05("Signature"),
    /* Etape signature annulation  */
    ST_AP_PIM_06("Signature Annulation"),
    /* Etape signature prorogation*/
    ST_AP_PIM_07("Signature Prorogation"),
    /* Etape signature modification*/
    ST_AP_PIM_08("Signature Modification"),

    // CO Step Code
    /**
     * The S t_ c o_42.
     */
    ST_CO_42("Dépôt Dossier CO"),
    /**
     * The S t_ c o_43.
     */
    ST_CO_43("Rejet CO"),
    /**
     * The S t_ c o_44.
     */
    ST_CO_44("Autorisation CO"),
    /**
     * The S t_ c o_45.
     */
    ST_CO_45("Retrait CO"),
    /**
     * The S t_ c o_46.
     */
    ST_CO_46("Recevabilité CO"),
    /**
     * The S t_ c o_47.
     */
    ST_CO_47("Cota_1"),
    /**
     * The S t_ c o_48.
     */
    ST_CO_48("Cota_2/Etude"),
    /**
     * The S t_ c o_49.
     */
    ST_CO_49("Cota_3/Etude"),
    /**
     * The S t_ c o_50.
     */
    ST_CO_50("Cota_4/Etude"),
    /**
     * The S t_ c o_51.
     */
    ST_CO_51("Cota_5/Etude"),
    /**
     * The S t_ c o_52.
     */
    ST_CO_52("Cota_6/Etude"),
    /**
     * The S t_ c o_53.
     */
    ST_CO_53("Etude CO"),
    /**
     * The S t_ c o_54.
     */
    ST_CO_54("Signature CO"),
    /**
     * The S t_ c o_55.
     */
    ST_CO_55("Visa_2"),
    /**
     * The S t_ c o_56.
     */
    ST_CO_56("Visa_3"),
    /**
     * The S t_ c o_57.
     */
    ST_CO_57("Visa_4"),
    /**
     * The S t_ c o_58.
     */
    ST_CO_58("Visa_5"),
    /**
     * The S t_ c o_59.
     */
    ST_CO_59("Visa_6"),
    /**
     * The S t_ c o_60.
     */
    ST_CO_60("Demande d'annulation"),
    /**
     * The S t_ c o_61.
     */
    ST_CO_61("Etude demande d'annulation"),
    /**
     * The S t_ c o_62.
     */
    ST_CO_62("Fin Annulation"),
    /**
     * The S t_ c o_63.
     */
    ST_CO_63("Signature Suite Annulation"),
    /**
     * The S t_ c o_64.
     */
    ST_CO_64("Paiment"),
    /**
     * The S t_ c o_65.
     */
    ST_CO_65("Paiment"),
    // AM Step Code
    /**
     * The S t_ a m_42.
     */
    ST_AM_42("Dépôt Dossier AM"),
    /**
     * The S t_ a m_43.
     */
    ST_AM_43("Rejet AM"),
    /**
     * The S t_ a m_44.
     */
    ST_AM_44("Autorisation AM"),
    /**
     * The S t_ a m_45.
     */
    ST_AM_45("Retrait AM"),
    /**
     * The S t_ a m_46.
     */
    ST_AM_46("Recevabilité AM"),
    /**
     * The S t_ a m_47.
     */
    ST_AM_47("Cota_1"),
    /**
     * The S t_ a m_48.
     */
    ST_AM_48("Cota_2/Etude"),
    /**
     * The S t_ a m_49.
     */
    ST_AM_49("Cota_3/Etude"),
    /**
     * The S t_ a m_50.
     */
    ST_AM_50("Cota_4/Etude"),
    /**
     * The S t_ a m_51.
     */
    ST_AM_51("Cota_5/Etude"),
    /**
     * The S t_ a m_52.
     */
    ST_AM_52("Cota_6/Etude"),
    /**
     * The S t_ a m_53.
     */
    ST_AM_53("Etude AM"),
    /**
     * The S t_ a m_54.
     */
    ST_AM_54("Signature AM"),
    /**
     * The S t_ a m_55.
     */
    ST_AM_55("Visa_2"),
    /**
     * The S t_ a m_56.
     */
    ST_AM_56("Visa_3"),
    /**
     * The S t_ a m_57.
     */
    ST_AM_57("Visa_4"),
    /**
     * The S t_ a m_58.
     */
    ST_AM_58("Visa_5"),
    /**
     * The S t_ a m_59.
     */
    ST_AM_59("Visa_6"),
    /**
     * The S t_ a m_60.
     */
    ST_AM_60("Demande d'annulation"),
    /**
     * The S t_ a m_61.
     */
    ST_AM_61("Etude demande d'annulation"),
    /**
     * The S t_ a m_62.
     */
    ST_AM_62("Fin Annulation"),
    /**
     * The S t_ a m_63.
     */
    ST_AM_63("Signature Suite Annulation"),
    /**
     * The S t_ a m_64.
     */
    ST_AM_64("Traitement Contrôle"),
    /**
     * The S t_ a m_65.
     */
    ST_AM_65("Demande Dépôt Manifeste"),
    /**
     * The S t_ a m_66.
     */
    ST_AM_66("Recevabilité Manifest"),
    /**
     * The S t_ f t_42.
     */
    ST_FT_42("Dépôt Dossier FT"),
    /**
     * The S t_ f t_43.
     */
    ST_FT_43("'Rejet FT"),
    /**
     * The S t_ f t_44.
     */
    ST_FT_44("Autorisation FT"),
    /**
     * The S t_ f t_53.
     */
    ST_FT_53("Etude FT"),
    // SF Step Codes
    /**
     * The S t_ s f_42.
     */
    ST_SF_42("Dépôt Dossier SF"),
    /**
     * The S t_ s f_43.
     */
    ST_SF_43("Rejet SF"),
    /**
     * The S t_ s f_44.
     */
    ST_SF_44("Autorisation SF"),
    /**
     * The S t_ s f_45.
     */
    ST_SF_45("Retrait SF"),
    /**
     * The S t_ s f_46.
     */
    ST_SF_46("Recevabilité SF"),
    /**
     * The S t_ s f_47.
     */
    ST_SF_47("Cota_1"),
    /**
     * The S t_ s f_48.
     */
    ST_SF_48("Cota_2/Etude"),
    /**
     * The S t_ s f_49.
     */
    ST_SF_49("Cota_3/Etude"),
    /**
     * The S t_ s f_50.
     */
    ST_SF_50("Cota_4/Etude"),
    /**
     * The S t_ s f_51.
     */
    ST_SF_51("Cota_5/Etude"),
    /**
     * The S t_ s f_52.
     */
    ST_SF_52("Cota_6/Etude"),
    /**
     * The S t_ s f_53.
     */
    ST_SF_53("Etude SF"),
    /**
     * The S t_ s f_54.
     */
    ST_SF_54("Signature SF"),
    /**
     * The S t_ s f_55.
     */
    ST_SF_55("Visa_2"),
    /**
     * The S t_ s f_56.
     */
    ST_SF_56("Visa_3"),
    /**
     * The S t_ s f_57.
     */
    ST_SF_57("Visa_4"),
    /**
     * The S t_ s f_58.
     */
    ST_SF_58("Visa_5"),
    /**
     * The S t_ s f_59.
     */
    ST_SF_59("Visa_6"),
    /**
     * The S t_ s f_60.
     */
    ST_SF_60("Demande d'annulation"),
    /**
     * The S t_ s f_61.
     */
    ST_SF_61("Etude demande d'annulation"),
    /**
     * The S t_ s f_62.
     */
    ST_SF_62("Fin Annulation"),
    /**
     * The S t_ s f_63.
     */
    ST_SF_63("Signature Suite Annulation"),
    /**
     * The S t_ s f_64.
     */
    ST_SF_64("Etude Recevabilité suite demande prorogation"),
    /**
     * The S t_ s f_65.
     */
    ST_SF_65("Etude demande prorogation"),
    /**
     * The S t_ s f_66.
     */
    ST_SF_66("Signature suite étude demande de prorogation"),
    /**
     * The S t_ s f_67.
     */
    ST_SF_67("Etude Recevabilité suite demande de modification"),
    /**
     * The S t_ s f_68.
     */
    ST_SF_68("Etude demande de modification"),
    /**
     * The S t_ s f_69.
     */
    ST_SF_69("Signature suite étude demande de modification"),
    /**
     * The S t_ c c_42.
     */
    ST_CC_42("Dépôt Dossier CC"),
    /**
     * The S t_ c o_43.
     */
    ST_CC_43("Rejet CC"),
    /**
     * The S t_ c o_44.
     */
    ST_CC_44("Autorisation CC"),
    /**
     * The S t_ c o_45.
     */
    ST_CC_45("Retrait CC"),
    /**
     * The S t_ c o_46.
     */
    ST_CC_46("Recevabilité CC"),
    /**
     * The S t_ c o_47.
     */
    ST_CC_47("Cota_1"),
    /**
     * The S t_ c o_48.
     */
    ST_CC_48("Cota_2/Etude"),
    /**
     * The S t_ c o_49.
     */
    ST_CC_49("Cota_3/Etude"),
    /**
     * The S t_ c o_50.
     */
    ST_CC_50("Cota_4/Etude"),
    /**
     * The S t_ c o_51.
     */
    ST_CC_51("Cota_5/Etude"),
    /**
     * The S t_ c o_52.
     */
    ST_CC_52("Cota_6/Etude"),
    /**
     * The S t_ c o_53.
     */
    ST_CC_53("Etude CC"),
    /**
     * The S t_ c o_54.
     */
    ST_CC_54("Signature CC"),
    /**
     * The S t_ c o_55.
     */
    ST_CC_55("Visa_2"),
    /**
     * The S t_ c o_56.
     */
    ST_CC_56("Visa_3"),
    /**
     * The S t_ c o_57.
     */
    ST_CC_57("Visa_4"),
    /**
     * The S t_ c o_58.
     */
    ST_CC_58("Visa_5"),
    /**
     * The S t_ c o_59.
     */
    ST_CC_59("Visa_6"),
    /**
     * The S t_ c o_60.
     */
    ST_CC_60("Demande d'annulation"),
    /**
     * The S t_ c o_61.
     */
    ST_CC_61("Etude demande d'annulation"),
    /**
     * The S t_ c o_62.
     */
    ST_CC_62("Fin Annulation"),
    /**
     * The S t_ c o_63.
     */
    ST_CC_63("Signature Suite Annulation"),
    /**
     * The S t_ c o_64.
     */
    ST_CC_64("Paiment"),
    /**
     * The S t_ c o_65.
     */
    ST_CC_65("Analyse"),
    /**
     * The S t_ c c_66.
     */
    ST_CC_66("Rejet Intermediaire"),
    /**
     * The S t_ c c_67.
     */
    ST_CC_67("Recevabilité Contre Analyse"),
    /**
     * The S t_ c c_68.
     */
    ST_CC_68("Etude Contre Analyse"),
    /**
     * The S t_ c c_69.
     */
    ST_CC_69("Contre Analyse"),
    /**
     * The S t_ c c_70.
     */
    ST_CC_70("Rejet Final"),
    /**
     * The S t_ c c_71.
     */
    ST_CC_71("Signature Suite contre analyse"),
    /**
     * The S t_ c c_72.
     */
    ST_CC_72("Acceptation suite analyse"),
    /**
     * The S t_ c c_73.
     */
    ST_CC_73("Dépôt demande de modification"),
    /**
     * The S t_ c c_74.
     */
    ST_CC_74("Recevabilité demande de modification"),
    /**
     * The S t_ c c_75.
     */
    ST_CC_75("Cotation Demande de modification"),
    /**
     * The S t_ c c_76.
     */
    ST_CC_76("Etude Demande de modification"),
    /**
     * The S t_ c c_77.
     */
    ST_CC_77("Signature Demande de modification"),
    ST_CC_78("Dépôt demande d'annulation"),
    ST_CC_79("Recevabilité demande d'annulation"),
    ST_CC_80("Cotation Demande d'annulation"),
    ST_CC_81("Etude Demande d'annulation"),
    ST_CC_82("Signature Demande d'annulation"),
    ST_CC_83("Traitement douane"),
    ST_CC_84("Fin certificat d'origine"),
    /**
     * The S t_ c c_cvs 01.
     */
    ST_CT_CVS_01("Vérification approfondie"),
    /**
     * The S t_ c c_cvs 02.
     */
    ST_CT_50("Retour du dossier pour réorientation"),
    ST_CT_CVS_02("Vérification suite validation");
    
    /**
     * Steps for Airport CTE and PVI
     */
//    ST_CT_E_AIP_01("Admissibility - Airport phytosanitary certificate request"),
//    ST_CT_E_AIP_02("Signature - Airport phytosanitary certificate request");

    /**
     * The label.
     */
    private final String label;

    /**
     * Instantiates a new step code.
     *
     * @param label the label
     */
    private StepCode(final String label) {
        this.label = label.intern();
    }

    /**
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

}
