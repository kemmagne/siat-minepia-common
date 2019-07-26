package org.guce.siat.common.utils.enums;

/**
 * The Enum FlowCode.
 */
public enum FlowCode {
    // CCT Flow Code

    /**
     * The F l_ c t_01.
     */
    FL_CT_01("Demande d'inspection"),
    /**
     * The F l_ c t_02.
     */
    FL_CT_02("CI Suite Recevabilité Demande d'inspection"),
    /**
     * The F l_ c t_03.
     */
    FL_CT_03("CI Réponse - Demande d'inspection-ER"),
    /**
     * The F l_ c t_04.
     */
    FL_CT_04("Rejet Suite Etude de Recevabilité"),
    /**
     * The F l_ c t_05.
     */
    FL_CT_05("Validation pour Cotation"),
    /**
     * The F l_ c t_06.
     */
    FL_CT_06("Cotation"),
    /**
     * The F l_ c t_07.
     */
    FL_CT_07("CCT pour Signature Suite EA"),
    /**
     * The F l_ c t_08.
     */
    FL_CT_08("Signature CCT (EA)"),
    /**
     * The F l_ c t_11.
     */
    FL_CT_11("Décision de Réexamen pour Signature Suite EA (RDD)"),
    /**
     * The F l_ c t_12.
     */
    FL_CT_12("Signature Décision de Réexamen (EA)"),
    /**
     * The F l_ c t_13.
     */
    FL_CT_13("Décision de Refoulement/Destruction pour Signature Suite EA (RDD Final)"),
    /**
     * The F l_ c t_14.
     */
    FL_CT_14("Signature Décision de Refoulement/Destruction (RDD Final)(EA)"),
    /**
     * The F l_ c t_16.
     */
    FL_CT_16("Signature Décision de Régularisation"),
    /**
     * The F l_ c t_17.
     */
    FL_CT_17("Décision de RDV à Quai pour Signature"),
    /**
     * The F l_ c t_18.
     */
    FL_CT_18("Signature Décision de RDV à Quai"),
    /**
     * The F l_ c t_19.
     */
    FL_CT_19("APE pour Signature"),
    /**
     * The F l_ c t_20.
     */
    FL_CT_20("Signature APE"),
    /**
     * The F l_ c t_21.
     */
    FL_CT_21("Demande RDV Visite chez Déclarant"),
    /**
     * The F l_ c t_22.
     */
    FL_CT_22("CI Suite Recevabilité Demande RDV Visite chez Déclarant"),
    /**
     * The F l_ c t_23.
     */
    FL_CT_23("Validation Suite Recevabilité - RDV Visite chez Déclarant"),
    /**
     * The F l_ c t_24.
     */
    FL_CT_24("CI Suite Etude Approfondie"),
    /**
     * The F l_ c t_25.
     */
    FL_CT_25("CI Suite Affectation RDV chez Déclarant"),
    /**
     * The F l_ c t_26.
     */
    FL_CT_26("Validation RDV chez Déclarant"),
    /**
     * The F l_ c t_27.
     */
    FL_CT_27("Ajournement Suite Inspection chez Déclarant"),
    /**
     * The F l_ c t_28.
     */
    FL_CT_28("Saisie Constat Suite RDV chez Déclarant"),
    /**
     * The F l_ c t_29.
     */
    FL_CT_29("Demande d'Analyse"),
    /**
     * The F l_ c t_30.
     */
    FL_CT_30("CI Suite Confirmation Décision Visite à Quai-ArdvAQuai"),
    /**
     * The F l_ c t_31.
     */
    FL_CT_31("Envoie Résultat d'Analyse"),
    /**
     * The F l_ c t_32.
     */
    FL_CT_32("CCT pour Signature Suite DSC"),
    /**
     * The F l_ c t_33.
     */
    FL_CT_33("Décision de Refoulement/Destruction pour Signature Suite DSC (RDD Final)"),
    /**
     * The F l_ c t_34.
     */
    FL_CT_34("Décision de Régularisation pour Signature"),
    /**
     * The F l_ c t_35.
     */
    FL_CT_35("Décision de Réexamen pour Signature Suite DSC (RDD)"),
    /**
     * The F l_ c t_36.
     */
    FL_CT_36("Demande de Confirmation RDV Visite à Quai"),
    /**
     * The F l_ c t_37.
     */
    FL_CT_37("CI Suite Recevabilité Demande de Confirmation RDV Visite à Quai"),
    /**
     * The F l_ c t_38.
     */
    FL_CT_38("CI Réponse - Demande RDV Visite-ER"),
    /**
     * The F l_ c t_39.
     */
    FL_CT_39("CI Réponse - Demande de Confirmation RDV Visite à Quai-ER"),
    /**
     * The F l_ c t_40.
     */
    FL_CT_40("Validation Suite Recevabilité - Demande de Confirmation VQ"),
    /**
     * The F l_ c t_41.
     */
    FL_CT_41("Proposition RDV Visite à Quai"),
    /**
     * The F l_ c t_42.
     */
    FL_CT_42("Validation RDV Visite à Quai"),
    /**
     * The F l_ c t_43.
     */
    FL_CT_43("Ajournement Suite Inspection Visite à Quai"),
    /**
     * The F l_ c t_44.
     */
    FL_CT_44("Saisie Constat Suite Visite à Quai"),
    /**
     * The F l_ c t_45.
     */
    FL_CT_45("Demande de Régularisation"),
    /**
     * The F l_ c t_46.
     */
    FL_CT_46("CI Suite Recevabilité Demande de Régularisation"),
    /**
     * The F l_ c t_47.
     */
    FL_CT_47("CI Réponse - Demande de Régularisation"),
    /**
     * The F l_ c t_48.
     */
    FL_CT_48("Validation Suite Recevabilité - Demande de Régularisation"),
    /**
     * The F l_ c t_49.
     */
    FL_CT_49("Demande de Réexamen"),
    /**
     * The F l_ c t_50.
     */
    FL_CT_50("CI Suite Recevabilité Demande de Réexamen"),
    /**
     * The F l_ c t_51.
     */
    FL_CT_51("CI Réponse - Réexamen"),
    /**
     * The F l_ c t_52.
     */
    FL_CT_52("Validation Demande de Réexamen pour EA"),
    /**
     * The F l_ c t_53.
     */
    FL_CT_53("Validation Demande de Réexamen pour DSC"),
    /**
     * The F l_ c t_54.
     */
    FL_CT_54("Confirmation de Déstruction"),
    /**
     * The F l_ c t_55.
     */
    FL_CT_55("CI Suite Recevabilité Demande de Refoulement/Destruction"),
    /**
     * The F l_ c t_56.
     */
    FL_CT_56("CI Réponse - Confirmation de Déstruction-ER"),
    /**
     * The F l_ c t_57.
     */
    FL_CT_57("Validation Suite Recevabilité - Confirmation de Déstruction"),
    /**
     * The F l_ c t_58.
     */
    FL_CT_58("CI Suite Traitement RDD"),
    /**
     * The F l_ c t_59.
     */
    FL_CT_59("Confirmation de Déstruction pour Signature"),
    /**
     * The F l_ c t_61.
     */
    FL_CT_61("Demande d'annulation"),
    /**
     * The F l_ c t_62.
     */
    FL_CT_62("Confirmation Annulation"),
    /**
     * The F l_ c t_63.
     */
    FL_CT_63("Rejet Annulation"),
    /**
     * The F l_ c t_64.
     */
    FL_CT_64("Demande de Traitement"),
    /**
     * The F l_ c t_65.
     */
    FL_CT_65("CI Suite Demande de Traitement"),
    /**
     * The F l_ c t_66.
     */
    FL_CT_66("Envoie Résultat de Traitement"),
    /**
     * The F l_ c t_67.
     */
    FL_CT_67("Confirmation Annulation pour Signature"),
    /**
     * The F l_ c t_68.
     */
    FL_CT_68("CI Réponse - Demande d'inspection-EA"),
    /**
     * The F l_ c t_69.
     */
    FL_CT_69("CI Réponse - Demande RDV Visite-ArdvChezD"),
    /**
     * The F l_ c t_70.
     */
    FL_CT_70("CI Réponse - Demande de Confirmation RDV Visite à Quai-ArdvAQuai"),
    /**
     * The F l_ c t_71.
     */
    FL_CT_71("CI Réponse - Confirmation de Déstruction (RDD)"),
    /**
     * The F l_ c t_72.
     */
    FL_CT_72("Signature Fin Déstruction"),
    /**
     * The F l_ c t_73.
     */
    FL_CT_73("Signature Demande de Réexamen (DSC)"),
    /**
     * The F l_ c t_74.
     */
    FL_CT_74("Signature Demande Refoulement/Destruction (RDD Final)(DSC)"),
    /**
     * The F l_ c t_76.
     */
    FL_CT_76("Refus signature Décision de CCT"),
    /**
     * The F l_ c t_77.
     */
    FL_CT_77("Refus signature Décision RDD (EA)"),
    /**
     * The F l_ c t_78.
     */
    FL_CT_78("Refus signature Décision RDD final (EA)"),
    /**
     * The F l_ c t_79.
     */
    FL_CT_79("Refus signature Décision de régularisation"),
    /**
     * The F l_ c t_80.
     */
    FL_CT_80("Refus signature Décision de RDV Visite à Quai"),
    /**
     * The F l_ c t_81.
     */
    FL_CT_81("Refus signature Décision APE"),
    /**
     * The F l_ c t_82.
     */
    FL_CT_82("Refus signature Validation de Déstruction"),
    /**
     * The F l_ c t_83.
     */
    FL_CT_83("Refus signature Décision RDD (DSC)"),
    /**
     * The F l_ c t_84.
     */
    FL_CT_84("Refus signature Décision RDD final (DSC)"),
    /**
     * The F l_ c t_87.
     */
    FL_CT_87("Refus signature Confirmation Annulation"),
    /**
     * The F l_ c t_88.
     */
    FL_CT_88("CI Suite Confirmation de Déstruction (RDD)"),
    /**
     * The F l_ c t_89.
     */
    FL_CT_89("Signature CCT (DSC)"),
    /**
     * The F l_ c t_90.
     */
    FL_CT_90("Refus signature CCT (DSC)"),
    /**
     * The F l_ c t_91.
     */
    FL_CT_91("Rejet Suite Étude Approfondie"),
    /**
     * The F l_ c t_91.
     */
    FL_CT_92("Facturation"),
    /**
     * The F l_ c t_91.
     */
    FL_CT_93("Encaissement"),
    //

    /**
     * new codes relative to treatement request
     */
    /**
     * The F l_ c t_91.
     */
    FL_CT_94("Demande de Traitement"),
    FL_CT_95("Validation Suite Recevabilité - Demande de Traitement"),
    FL_CT_96("CI - Demande de Traitement"),
    FL_CT_97("Rejet - Demande de Traitement"),
    FL_CT_98("Rejet - Demande de Traitement"),
    FL_CT_99("Demande de supervision"),
    FL_CT_100("Validation Suite Recevabilité - Demande de supervision"),
    FL_CT_101("CI - Demande de supervision"),
    FL_CT_102("Rejet - Demande de supervision"),
    FL_CT_103("Cotation"),
    FL_CT_104("Confirmation RDV pour traitement"),
    FL_CT_105("Confirmation RDV pour inspection"),
    //

    /**
     * AP Flow Code
     */
    /**
     * The F l_ a p_85.
     */
    FL_AP_85("Dépot dossier AP"),
    /**
     * The F l_ a p_86.
     */
    FL_AP_86("Complément d'information Suite Recevabilité AP"),
    /**
     * The F l_ a p_87.
     */
    FL_AP_87("Rejet Suite Recevabilité AP"),
    /**
     * The F l_ a p_88.
     */
    FL_AP_88("Validation pour cotation AP"),
    /**
     * The F l_ a p_89.
     */
    FL_AP_89("Cotation_1"),
    /**
     * The F l_ a p_90.
     */
    FL_AP_90("Cotation_2"),
    /**
     * The F l_ a p_91.
     */
    FL_AP_91("Cotation_3"),
    /**
     * The F l_ a p_92.
     */
    FL_AP_92("Cotation_4"),
    /**
     * The F l_ a p_93.
     */
    FL_AP_93("Cotation_5"),
    /**
     * The F l_ a p_94.
     */
    FL_AP_94("Cotation_6"),
    /**
     * The F l_ a p_95.
     */
    FL_AP_95("Rejet Suite Etude_2 AP"),
    /**
     * The F l_ a p_96.
     */
    FL_AP_96("Rejet Suite Etude_3 AP"),
    /**
     * The F l_ a p_97.
     */
    FL_AP_97("Rejet Suite Etude_4 AP"),
    /**
     * The F l_ a p_98.
     */
    FL_AP_98("Rejet Suite Etude_5 AP"),
    /**
     * The F l_ a p_99.
     */
    FL_AP_99("Rejet Suite Etude_6 AP"),
    /**
     * The F l_ a p_100.
     */
    FL_AP_100("Rejet Suite Etude AP"),
    /**
     * The F l_ a p_101.
     */
    FL_AP_101("Acceptation Suite Etude AP"),
    /**
     * The F l_ a p_102.
     */
    FL_AP_102("Acceptation Suite Etude_6 AP"),
    /**
     * The F l_ a p_103.
     */
    FL_AP_103("Acceptation Suite Etude_5 AP"),
    /**
     * The F l_ a p_104.
     */
    FL_AP_104("Acceptation Suite Etude_4 AP"),
    /**
     * The F l_ a p_105.
     */
    FL_AP_105("Acceptation Suite Etude_3 AP"),
    /**
     * The F l_ a p_106.
     */
    FL_AP_106("Acceptation Suite Etude_2 AP"),
    /**
     * The F l_ a p_107.
     */
    FL_AP_107("Autorisation AP Suite Signature"),
    /**
     * The F l_ a p_108.
     */
    FL_AP_108("Visa 6"),
    /**
     * The F l_ a p_109.
     */
    FL_AP_109("Visa 5"),
    /**
     * The F l_ a p_110.
     */
    FL_AP_110("Visa 4"),
    /**
     * The F l_ a p_111.
     */
    FL_AP_111("Visa 3"),
    /**
     * The F l_ a p_112.
     */
    FL_AP_112("Visa 2"),
    /**
     * The F l_ a p_113.
     */
    FL_AP_113("CI Suite Etude_2 AP"),
    /**
     * The F l_ a p_114.
     */
    FL_AP_114("CI Suite Etude_3 AP"),
    /**
     * The F l_ a p_115.
     */
    FL_AP_115("CI Suite Etude_4 AP"),
    /**
     * The F l_ a p_116.
     */
    FL_AP_116("CI Suite Etude_5 AP"),
    /**
     * The F l_ a p_117.
     */
    FL_AP_117("CI Suite Etude_6 AP"),
    /**
     * The F l_ a p_118.
     */
    FL_AP_118("CI Suite Etude AP"),
    /**
     * The F l_ a p_119.
     */
    FL_AP_119("Retour Visa 6 - Etude AP"),
    /**
     * The F l_ a p_120.
     */
    FL_AP_120("Retour Visa 5 - Etude AP"),
    /**
     * The F l_ a p_121.
     */
    FL_AP_121("Retour Visa 5 - Etude_6 AP"),
    /**
     * The F l_ a p_122.
     */
    FL_AP_122("Retour Visa 4 - Etude AP"),
    /**
     * The F l_ a p_123.
     */
    FL_AP_123("Retour Visa 4 - Etude_6 AP"),
    /**
     * The F l_ a p_124.
     */
    FL_AP_124("Retour Visa 4 - Etude_5 AP"),
    /**
     * The F l_ a p_125.
     */
    FL_AP_125("Retour Visa 3 - Etude AP"),
    /**
     * The F l_ a p_126.
     */
    FL_AP_126("Retour Visa 3 - Etude_6 AP"),
    /**
     * The F l_ a p_127.
     */
    FL_AP_127("Retour Visa 3 - Etude_5 AP"),
    /**
     * The F l_ a p_128.
     */
    FL_AP_128("Retour Visa 3 - Etude_4 AP"),
    /**
     * The F l_ a p_129.
     */
    FL_AP_129("Retour Visa 2 - Etude AP"),
    /**
     * The F l_ a p_130.
     */
    FL_AP_130("Retour Visa 2 - Etude_6 AP"),
    /**
     * The F l_ a p_131.
     */
    FL_AP_131("Retour Visa 2 - Etude_5 AP"),
    /**
     * The F l_ a p_132.
     */
    FL_AP_132("Retour Visa 2 - Etude_4 AP"),
    /**
     * The F l_ a p_133.
     */
    FL_AP_133("Retour Visa 2 - Etude_3 AP"),
    /**
     * The F l_ a p_134.
     */
    FL_AP_134("Retour Signature - Etude AP"),
    /**
     * The F l_ a p_135.
     */
    FL_AP_135("Retour Signature - Etude_6 AP"),
    /**
     * The F l_ a p_136.
     */
    FL_AP_136("Retour Signature - Etude_5 AP"),
    /**
     * The F l_ a p_137.
     */
    FL_AP_137("Retour Signature - Etude_4 AP"),
    /**
     * The F l_ a p_138.
     */
    FL_AP_138("Retour Signature - Etude_3 AP"),
    /**
     * The F l_ a p_139.
     */
    FL_AP_139("Retour Signature - Etude_2 AP"),
    /**
     * The F l_ a p_140.
     */
    FL_AP_140("Retrait AP"),
    /**
     * The F l_ a p_141.
     */
    FL_AP_141("Réponse CI Suite Etude AP"),
    /**
     * The F l_ a p_142.
     */
    FL_AP_142("Réponse CI Suite Etude AP"),
    /**
     * The F l_ a p_143.
     */
    FL_AP_143("Réponse CI Suite Etude AP"),
    /**
     * The F l_ a p_144.
     */
    FL_AP_144("Réponse CI Suite Etude AP"),
    /**
     * The F l_ a p_145.
     */
    FL_AP_145("Réponse CI Suite Etude AP"),
    /**
     * The F l_ a p_146.
     */
    FL_AP_146("Réponse CI Suite Etude AP"),
    /**
     * The F l_ a p_147.
     */
    FL_AP_147("Demande d'annulation"),
    /**
     * The F l_ a p_148.
     */
    FL_AP_148("Paiement coté Guce"),
    /**
     * The F l_ a p_150.
     */
    FL_AP_150("Annulation pour Signature"),
    /**
     * The F l_ a p_151.
     */
    FL_AP_151("Rejet Annulation"),
    /**
     * The F l_ a p_152.
     */
    FL_AP_152("Confirmation Annulation"),
    /**
     * The F l_ a p_153.
     */
    FL_AP_153("Refus signature - Annulation"),
    /**
     * The F l_ a p_154.
     */
    FL_AP_154("Réponse CI Suite Recevabilité"),
    /**
     * The F l_ a p_155.
     */
    FL_AP_155("Rejet cotation"),
    /**
     * The F l_ a p_156.
     */
    FL_AP_156("Rejet cotation"),
    /**
     * The F l_ a p_157.
     */
    FL_AP_157("Rejet cotation"),
    /**
     * The F l_ a p_158.
     */
    FL_AP_158("Rejet cotation"),
    /**
     * The F l_ a p_159.
     */
    FL_AP_159("Rejet cotation"),
    /**
     * The F l_ a p_160.
     */
    /* paiment */
    FL_AP_160("validation pour paiement"),
    /**
     * The F l_ a p_161.
     */
    FL_AP_161("validation pour paiement"),
    /**
     * The F l_ a p_162.
     */
    FL_AP_162("validation pour paiement"),
    /**
     * The F l_ a p_163.
     */
    FL_AP_163("validation pour paiement"),
    /**
     * The F l_ a p_164.
     */
    FL_AP_164("validation pour paiement"),
    /**
     * The F l_ a p_165.
     */
    FL_AP_165("validation pour paiement"),
    /**
     * The F l_ a p_166.
     */
    FL_AP_166("paiement"),
    // CO Flow Code
    FL_AP_167("paiement"),
    FL_AP_168("validation pour paiement"),
    FL_AP_169("Autorisation AP Suite Etude Recevabilite"),
    FL_AP_170("Autorisation AP Suite Cotation 1"),
    FL_AP_171("Autorisation AP Suite Cotation 2"),
    FL_AP_172("Autorisation AP Suite Cotation 3"),
    FL_AP_173("Autorisation AP Suite Cotation 4"),
    FL_AP_174("Autorisation AP Suite Cotation 5"),
    FL_AP_175("Rejet Suite Etude AP"),
    FL_AP_176("Complément d''information Suite Etude Recevabilité"),
    FL_AP_177("Rejet Suite Cotation"),
    FL_AP_178("Complément d''information Suite Cotation"),
    FL_AP_179("Rejet Suite Cotation"),
    FL_AP_180("Complément d''information Suite Cotation"),
    FL_AP_181("Rejet Suite Cotation"),
    FL_AP_182("Complément d''information Suite Cotation"),
    FL_AP_183("Rejet Suite Cotation"),
    FL_AP_184("Complément d''information Suite Cotation"),
    FL_AP_185("Rejet Suite Cotation"),
    FL_AP_186("Complément d''information Suite Cotation"),
    FL_AP_187("Réponse CI Suite Etude AP"),
    FL_AP_188("Réponse CI Suite Etude AP"),
    FL_AP_189("Réponse CI Suite Etude AP"),
    FL_AP_190("Réponse CI Suite Etude AP"),
    FL_AP_191("Réponse CI Suite Etude AP"),
    FL_AP_192("Réponse CI Suite Etude AP"),
    FL_AP_193("Facturation"),
    FL_AP_194("Facturation"),
    FL_AP_200("Dépôt de dossier pour amendement AP"),
    FL_AP_201("Rejet suite recevabilité amendement AP"),
    FL_AP_202("Autorisation de l'amendement AP"),
    /**
     * The F l_ a p_85.
     */
    FL_CO_85("Dépot dossier CO"),
    /**
     * The F l_ a p_86.
     */
    FL_CO_86("Complément d'information Suite Recevabilité CO"),
    /**
     * The F l_ a p_87.
     */
    FL_CO_87("Rejet Suite Recevabilité CO"),
    /**
     * The F l_ a p_88.
     */
    FL_CO_88("Validation pour cotation CO"),
    /**
     * The F l_ a p_89.
     */
    FL_CO_89("Cotation_1"),
    /**
     * The F l_ a p_90.
     */
    FL_CO_90("Cotation_2"),
    /**
     * The F l_ a p_91.
     */
    FL_CO_91("Cotation_3"),
    /**
     * The F l_ a p_92.
     */
    FL_CO_92("Cotation_4"),
    /**
     * The F l_ a p_93.
     */
    FL_CO_93("Cotation_5"),
    /**
     * The F l_ a p_94.
     */
    FL_CO_94("Cotation_6"),
    /**
     * The F l_ a p_95.
     */
    FL_CO_95("Rejet Suite Etude_2 CO"),
    /**
     * The F l_ a p_96.
     */
    FL_CO_96("Rejet Suite Etude_3 CO"),
    /**
     * The F l_ a p_97.
     */
    FL_CO_97("Rejet Suite Etude_4 CO"),
    /**
     * The F l_ a p_98.
     */
    FL_CO_98("Rejet Suite Etude_5 CO"),
    /**
     * The F l_ a p_99.
     */
    FL_CO_99("Rejet Suite Etude_6 CO"),
    /**
     * The F l_ a p_100.
     */
    FL_CO_100("Rejet Suite Etude CO"),
    /**
     * The F l_ a p_101.
     */
    FL_CO_101("Acceptation Suite Etude CO"),
    /**
     * The F l_ a p_102.
     */
    FL_CO_102("Acceptation Suite Etude_6 CO"),
    /**
     * The F l_ a p_103.
     */
    FL_CO_103("Acceptation Suite Etude_5 CO"),
    /**
     * The F l_ a p_104.
     */
    FL_CO_104("Acceptation Suite Etude_4 CO"),
    /**
     * The F l_ a p_105.
     */
    FL_CO_105("Acceptation Suite Etude_3 CO"),
    /**
     * The F l_ a p_106.
     */
    FL_CO_106("Acceptation Suite Etude_2 CO"),
    /**
     * The F l_ a p_107.
     */
    FL_CO_107("Autorisation CO Suite Signature"),
    /**
     * The F l_ a p_108.
     */
    FL_CO_108("Visa 6"),
    /**
     * The F l_ a p_109.
     */
    FL_CO_109("Visa 5"),
    /**
     * The F l_ a p_110.
     */
    FL_CO_110("Visa 4"),
    /**
     * The F l_ a p_111.
     */
    FL_CO_111("Visa 3"),
    /**
     * The F l_ a p_112.
     */
    FL_CO_112("Visa 2"),
    /**
     * The F l_ a p_113.
     */
    FL_CO_113("CI Suite Etude_2 CO"),
    /**
     * The F l_ a p_114.
     */
    FL_CO_114("CI Suite Etude_3 CO"),
    /**
     * The F l_ a p_115.
     */
    FL_CO_115("CI Suite Etude_4 CO"),
    /**
     * The F l_ a p_116.
     */
    FL_CO_116("CI Suite Etude_5 CO"),
    /**
     * The F l_ a p_117.
     */
    FL_CO_117("CI Suite Etude_6 CO"),
    /**
     * The F l_ a p_118.
     */
    FL_CO_118("CI Suite Etude CO"),
    /**
     * The F l_ a p_119.
     */
    FL_CO_119("Retour Visa 6 - Etude CO"),
    /**
     * The F l_ a p_120.
     */
    FL_CO_120("Retour Visa 5 - Etude CO"),
    /**
     * The F l_ a p_121.
     */
    FL_CO_121("Retour Visa 5 - Etude_6 CO"),
    /**
     * The F l_ a p_122.
     */
    FL_CO_122("Retour Visa 4 - Etude CO"),
    /**
     * The F l_ a p_123.
     */
    FL_CO_123("Retour Visa 4 - Etude_6 CO"),
    /**
     * The F l_ a p_124.
     */
    FL_CO_124("Retour Visa 4 - Etude_5 CO"),
    /**
     * The F l_ a p_125.
     */
    FL_CO_125("Retour Visa 3 - Etude CO"),
    /**
     * The F l_ a p_126.
     */
    FL_CO_126("Retour Visa 3 - Etude_6 CO"),
    /**
     * The F l_ a p_127.
     */
    FL_CO_127("Retour Visa 3 - Etude_5 CO"),
    /**
     * The F l_ a p_128.
     */
    FL_CO_128("Retour Visa 3 - Etude_4 CO"),
    /**
     * The F l_ a p_129.
     */
    FL_CO_129("Retour Visa 2 - Etude CO"),
    /**
     * The F l_ a p_130.
     */
    FL_CO_130("Retour Visa 2 - Etude_6 CO"),
    /**
     * The F l_ a p_131.
     */
    FL_CO_131("Retour Visa 2 - Etude_5 CO"),
    /**
     * The F l_ a p_132.
     */
    FL_CO_132("Retour Visa 2 - Etude_4 CO"),
    /**
     * The F l_ a p_133.
     */
    FL_CO_133("Retour Visa 2 - Etude_3 CO"),
    /**
     * The F l_ a p_134.
     */
    FL_CO_134("Retour Signature - Etude CO"),
    /**
     * The F l_ a p_135.
     */
    FL_CO_135("Retour Signature - Etude_6 CO"),
    /**
     * The F l_ a p_136.
     */
    FL_CO_136("Retour Signature - Etude_5 CO"),
    /**
     * The F l_ a p_137.
     */
    FL_CO_137("Retour Signature - Etude_4 CO"),
    /**
     * The F l_ a p_138.
     */
    FL_CO_138("Retour Signature - Etude_3 CO"),
    /**
     * The F l_ a p_139.
     */
    FL_CO_139("Retour Signature - Etude_2 CO"),
    /**
     * The F l_ a p_140.
     */
    FL_CO_140("Retrait CO"),
    /**
     * The F l_ a p_141.
     */
    FL_CO_141("Réponse CI Suite Etude CO"),
    /**
     * The F l_ a p_142.
     */
    FL_CO_142("Réponse CI Suite Etude CO"),
    /**
     * The F l_ a p_143.
     */
    FL_CO_143("Réponse CI Suite Etude CO"),
    /**
     * The F l_ a p_144.
     */
    FL_CO_144("Réponse CI Suite Etude CO"),
    /**
     * The F l_ a p_145.
     */
    FL_CO_145("Réponse CI Suite Etude CO"),
    /**
     * The F l_ a p_146.
     */
    FL_CO_146("Réponse CI Suite Etude CO"),
    /**
     * The F l_ a p_147.
     */
    FL_CO_147("Demande d'annulation"),
    /**
     * The F l_ a p_150.
     */
    FL_CO_150("Annulation pour Signature"),
    /**
     * The F l_ a p_151.
     */
    FL_CO_151("Rejet Annulation"),
    /**
     * The F l_ a p_152.
     */
    FL_CO_152("Confirmation Annulation"),
    /**
     * The F l_ a p_153.
     */
    FL_CO_153("Refus signature - Annulation"),
    /**
     * The F l_ a p_154.
     */
    FL_CO_154("Réponse CI Suite Recevabilité"),
    /**
     * The F l_ c o_155.
     */
    FL_CO_155("Validation Pour Paiement"),
    /**
     * The F l_ c o_156.
     */
    FL_CO_156("Validation  Paiement"),
    /**
     * The F l_ c o_157.
     */
    FL_CO_157("Validation Pour Paiement"),
    /**
     * The F l_ c o_158.
     */
    FL_CO_158("Validation  Paiement"),
    /**
     * The F l_ a m_85.
     */
    FL_AM_85("Dépot dossier AM"),
    /**
     * The F l_ a p_86.
     */
    FL_AM_86("Complément d'information Suite Recevabilité AM"),
    /**
     * The F l_ a p_87.
     */
    FL_AM_87("Rejet Suite Recevabilité AM"),
    /**
     * The F l_ a p_88.
     */
    FL_AM_88("Validation pour cotation AM"),
    /**
     * The F l_ a p_89.
     */
    FL_AM_89("Cotation_1"),
    /**
     * The F l_ a p_90.
     */
    FL_AM_90("Cotation_2"),
    /**
     * The F l_ a p_91.
     */
    FL_AM_91("Cotation_3"),
    /**
     * The F l_ a p_92.
     */
    FL_AM_92("Cotation_4"),
    /**
     * The F l_ a p_93.
     */
    FL_AM_93("Cotation_5"),
    /**
     * The F l_ a p_94.
     */
    FL_AM_94("Cotation_6"),
    /**
     * The F l_ a p_95.
     */
    FL_AM_95("Rejet Suite Etude_2 AM"),
    /**
     * The F l_ a p_96.
     */
    FL_AM_96("Rejet Suite Etude_3 AM"),
    /**
     * The F l_ a p_97.
     */
    FL_AM_97("Rejet Suite Etude_4 AM"),
    /**
     * The F l_ a p_98.
     */
    FL_AM_98("Rejet Suite Etude_5 AM"),
    /**
     * The F l_ a p_99.
     */
    FL_AM_99("Rejet Suite Etude_6 AM"),
    /**
     * The F l_ a p_100.
     */
    FL_AM_100("Rejet Suite Etude AM"),
    /**
     * The F l_ a p_101.
     */
    FL_AM_101("Acceptation Suite Etude AM"),
    /**
     * The F l_ a p_102.
     */
    FL_AM_102("Acceptation Suite Etude_6 AM"),
    /**
     * The F l_ a p_103.
     */
    FL_AM_103("Acceptation Suite Etude_5 AM"),
    /**
     * The F l_ a p_104.
     */
    FL_AM_104("Acceptation Suite Etude_4 AM"),
    /**
     * The F l_ a p_105.
     */
    FL_AM_105("Acceptation Suite Etude_3 AM"),
    /**
     * The F l_ a p_106.
     */
    FL_AM_106("Acceptation Suite Etude_2 AM"),
    /**
     * The F l_ a p_107.
     */
    FL_AM_107("Autorisation AM Suite Signature"),
    /**
     * The F l_ a p_108.
     */
    FL_AM_108("Visa 6"),
    /**
     * The F l_ a p_109.
     */
    FL_AM_109("Visa 5"),
    /**
     * The F l_ a p_110.
     */
    FL_AM_110("Visa 4"),
    /**
     * The F l_ a p_111.
     */
    FL_AM_111("Visa 3"),
    /**
     * The F l_ a p_112.
     */
    FL_AM_112("Visa 2"),
    /**
     * The F l_ a p_113.
     */
    FL_AM_113("CI Suite Etude_2 AM"),
    /**
     * The F l_ a p_114.
     */
    FL_AM_114("CI Suite Etude_3 AM"),
    /**
     * The F l_ a p_115.
     */
    FL_AM_115("CI Suite Etude_4 AM"),
    /**
     * The F l_ a p_116.
     */
    FL_AM_116("CI Suite Etude_5 AM"),
    /**
     * The F l_ a p_117.
     */
    FL_AM_117("CI Suite Etude_6 AM"),
    /**
     * The F l_ a p_118.
     */
    FL_AM_118("CI Suite Etude AM"),
    /**
     * The F l_ a p_119.
     */
    FL_AM_119("Retour Visa 6 - Etude AM"),
    /**
     * The F l_ a p_120.
     */
    FL_AM_120("Retour Visa 5 - Etude AM"),
    /**
     * The F l_ a p_121.
     */
    FL_AM_121("Retour Visa 5 - Etude_6 AM"),
    /**
     * The F l_ a p_122.
     */
    FL_AM_122("Retour Visa 4 - Etude AM"),
    /**
     * The F l_ a p_123.
     */
    FL_AM_123("Retour Visa 4 - Etude_6 AM"),
    /**
     * The F l_ a p_124.
     */
    FL_AM_124("Retour Visa 4 - Etude_5 AM"),
    /**
     * The F l_ a p_125.
     */
    FL_AM_125("Retour Visa 3 - Etude AM"),
    /**
     * The F l_ a p_126.
     */
    FL_AM_126("Retour Visa 3 - Etude_6 AM"),
    /**
     * The F l_ a p_127.
     */
    FL_AM_127("Retour Visa 3 - Etude_5 AM"),
    /**
     * The F l_ a p_128.
     */
    FL_AM_128("Retour Visa 3 - Etude_4 AM"),
    /**
     * The F l_ a p_129.
     */
    FL_AM_129("Retour Visa 2 - Etude AM"),
    /**
     * The F l_ a p_130.
     */
    FL_AM_130("Retour Visa 2 - Etude_6 AM"),
    /**
     * The F l_ a p_131.
     */
    FL_AM_131("Retour Visa 2 - Etude_5 AM"),
    /**
     * The F l_ a p_132.
     */
    FL_AM_132("Retour Visa 2 - Etude_4 AM"),
    /**
     * The F l_ a p_133.
     */
    FL_AM_133("Retour Visa 2 - Etude_3 AM"),
    /**
     * The F l_ a p_134.
     */
    FL_AM_134("Retour Signature - Etude AM"),
    /**
     * The F l_ a p_135.
     */
    FL_AM_135("Retour Signature - Etude_6 AM"),
    /**
     * The F l_ a p_136.
     */
    FL_AM_136("Retour Signature - Etude_5 AM"),
    /**
     * The F l_ a p_137.
     */
    FL_AM_137("Retour Signature - Etude_4 AM"),
    /**
     * The F l_ a p_138.
     */
    FL_AM_138("Retour Signature - Etude_3 AM"),
    /**
     * The F l_ a p_139.
     */
    FL_AM_139("Retour Signature - Etude_2 AM"),
    /**
     * The F l_ a p_140.
     */
    FL_AM_140("Retrait AM"),
    /**
     * The F l_ a p_141.
     */
    FL_AM_141("Réponse CI Suite Etude AM"),
    /**
     * The F l_ a p_142.
     */
    FL_AM_142("Réponse CI Suite Etude AM"),
    /**
     * The F l_ a p_143.
     */
    FL_AM_143("Réponse CI Suite Etude AM"),
    /**
     * The F l_ a p_144.
     */
    FL_AM_144("Réponse CI Suite Etude AM"),
    /**
     * The F l_ a p_145.
     */
    FL_AM_145("Réponse CI Suite Etude AM"),
    /**
     * The F l_ a p_146.
     */
    FL_AM_146("Réponse CI Suite Etude AM"),
    /**
     * The F l_ a p_147.
     */
    FL_AM_147("Demande d'annulation"),
    /**
     * The F l_ a p_148.
     */
    FL_AM_148("CI Suite demande d'annulation"),
    /**
     * The F l_ a p_149.
     */
    FL_AM_149("Réponse CI Suite demande d'annulation"),
    /**
     * The F l_ a p_150.
     */
    FL_AM_150("Annulation pour Signature"),
    /**
     * The F l_ a p_151.
     */
    FL_AM_151("Rejet Annulation"),
    /**
     * The F l_ a p_152.
     */
    FL_AM_152("Confirmation Annulation"),
    /**
     * The F l_ a p_153.
     */
    FL_AM_153("Refus signature - Annulation"),
    /**
     * The F l_ a p_154.
     */
    FL_AM_154("Réponse CI Suite Recevabilité"),
    /**
     * The F l_ a m_155.
     */
    FL_AM_155("Validation pour contrôle"),
    /**
     * The F l_ a m_171.
     */
    FL_AM_156("Envoi résultat du conrôle"),
    /**
     * The F l_ a m_157.
     */
    FL_AM_157("Dépot Manifeste"),
    /**
     * The F l_ f t_85.
     */
    FL_FT_85("Dépot dossier FT"),
    /**
     * The F l_ f t_86.
     */
    FL_FT_86("Complément d''information Suite Recevabilité FT"),
    /**
     * The F l_ f t_87.
     */
    FL_FT_87("Rejet Suite Recevabilité FT"),
    /**
     * The F l_ f t_88.
     */
    FL_FT_88("Autorisation FT"),
    /**
     * The F l_ f t_154.
     */
    FL_FT_154("Réponse CI Suite Recevabilité"),
    // SF Flow Code
    /**
     * The F l_ s f_85.
     */
    FL_SF_85("Dépot dossier SF"),
    /**
     * The F l_ s f_86.
     */
    FL_SF_86("Complément d'information Suite Recevabilité SF"),
    /**
     * The F l_ s f_87.
     */
    FL_SF_87("Rejet Suite Recevabilité SF"),
    /**
     * The F l_ s f_88.
     */
    FL_SF_88("Validation pour cotation SF"),
    /**
     * The F l_ s f_89.
     */
    FL_SF_89("Cotation_1"),
    /**
     * The F l_ s f_90.
     */
    FL_SF_90("Cotation_2"),
    /**
     * The F l_ s f_91.
     */
    FL_SF_91("Cotation_3"),
    /**
     * The F l_ s f_92.
     */
    FL_SF_92("Cotation_4"),
    /**
     * The F l_ s f_93.
     */
    FL_SF_93("Cotation_5"),
    /**
     * The F l_ s f_94.
     */
    FL_SF_94("Cotation_6"),
    /**
     * The F l_ s f_95.
     */
    FL_SF_95("Rejet Suite Etude_2 SF"),
    /**
     * The F l_ s f_96.
     */
    FL_SF_96("Rejet Suite Etude_3 SF"),
    /**
     * The F l_ s f_97.
     */
    FL_SF_97("Rejet Suite Etude_4 SF"),
    /**
     * The F l_ s f_98.
     */
    FL_SF_98("Rejet Suite Etude_5 SF"),
    /**
     * The F l_ s f_99.
     */
    FL_SF_99("Rejet Suite Etude_6 SF"),
    /**
     * The F l_ s f_100.
     */
    FL_SF_100("Rejet Suite Etude SF"),
    /**
     * The F l_ s f_101.
     */
    FL_SF_101("Acceptation Suite Etude SF"),
    /**
     * The F l_ s f_102.
     */
    FL_SF_102("Acceptation Suite Etude_6 SF"),
    /**
     * The F l_ s f_103.
     */
    FL_SF_103("Acceptation Suite Etude_5 SF"),
    /**
     * The F l_ s f_104.
     */
    FL_SF_104("Acceptation Suite Etude_4 SF"),
    /**
     * The F l_ s f_105.
     */
    FL_SF_105("Acceptation Suite Etude_3 SF"),
    /**
     * The F l_ s f_106.
     */
    FL_SF_106("Acceptation Suite Etude_2 SF"),
    /**
     * The F l_ s f_107.
     */
    FL_SF_107("Autorisation SF Suite Signature"),
    /**
     * The F l_ s f_108.
     */
    FL_SF_108("Visa 6"),
    /**
     * The F l_ s f_109.
     */
    FL_SF_109("Visa 5"),
    /**
     * The F l_ s f_110.
     */
    FL_SF_110("Visa 4"),
    /**
     * The F l_ s f_111.
     */
    FL_SF_111("Visa 3"),
    /**
     * The F l_ s f_112.
     */
    FL_SF_112("Visa 2"),
    /**
     * The F l_ s f_113.
     */
    FL_SF_113("CI Suite Etude_2 SF"),
    /**
     * The F l_ s f_114.
     */
    FL_SF_114("CI Suite Etude_3 SF"),
    /**
     * The F l_ s f_115.
     */
    FL_SF_115("CI Suite Etude_4 SF"),
    /**
     * The F l_ s f_116.
     */
    FL_SF_116("CI Suite Etude_5 SF"),
    /**
     * The F l_ s f_117.
     */
    FL_SF_117("CI Suite Etude_6 SF"),
    /**
     * The F l_ s f_118.
     */
    FL_SF_118("CI Suite Etude SF"),
    /**
     * The F l_ s f_119.
     */
    FL_SF_119("Retour Visa 6 - Etude SF"),
    /**
     * The F l_ s f_120.
     */
    FL_SF_120("Retour Visa 5 - Etude SF"),
    /**
     * The F l_ s f_121.
     */
    FL_SF_121("Retour Visa 5 - Etude_6 SF"),
    /**
     * The F l_ s f_122.
     */
    FL_SF_122("Retour Visa 4 - Etude SF"),
    /**
     * The F l_ s f_123.
     */
    FL_SF_123("Retour Visa 4 - Etude_6 SF"),
    /**
     * The F l_ s f_124.
     */
    FL_SF_124("Retour Visa 4 - Etude_5 SF"),
    /**
     * The F l_ s f_125.
     */
    FL_SF_125("Retour Visa 3 - Etude SF"),
    /**
     * The F l_ s f_126.
     */
    FL_SF_126("Retour Visa 3 - Etude_6 SF"),
    /**
     * The F l_ s f_127.
     */
    FL_SF_127("Retour Visa 3 - Etude_5 SF"),
    /**
     * The F l_ s f_128.
     */
    FL_SF_128("Retour Visa 3 - Etude_4 SF"),
    /**
     * The F l_ s f_129.
     */
    FL_SF_129("Retour Visa 2 - Etude SF"),
    /**
     * The F l_ s f_130.
     */
    FL_SF_130("Retour Visa 2 - Etude_6 SF"),
    /**
     * The F l_ s f_131.
     */
    FL_SF_131("Retour Visa 2 - Etude_5 SF"),
    /**
     * The F l_ s f_132.
     */
    FL_SF_132("Retour Visa 2 - Etude_4 SF"),
    /**
     * The F l_ s f_133.
     */
    FL_SF_133("Retour Visa 2 - Etude_3 SF"),
    /**
     * The F l_ s f_134.
     */
    FL_SF_134("Retour Signature - Etude SF"),
    /**
     * The F l_ s f_135.
     */
    FL_SF_135("Retour Signature - Etude_6 SF"),
    /**
     * The F l_ s f_136.
     */
    FL_SF_136("Retour Signature - Etude_5 SF"),
    /**
     * The F l_ s f_137.
     */
    FL_SF_137("Retour Signature - Etude_4 SF"),
    /**
     * The F l_ s f_138.
     */
    FL_SF_138("Retour Signature - Etude_3 SF"),
    /**
     * The F l_ s f_139.
     */
    FL_SF_139("Retour Signature - Etude_2 SF"),
    /**
     * The F l_ s f_140.
     */
    FL_SF_140("Retrait SF"),
    /**
     * The F l_ s f_141.
     */
    FL_SF_141("Réponse CI Suite Etude SF"),
    /**
     * The F l_ s f_142.
     */
    FL_SF_142("Réponse CI Suite Etude SF"),
    /**
     * The F l_ s f_143.
     */
    FL_SF_143("Réponse CI Suite Etude SF"),
    /**
     * The F l_ s f_144.
     */
    FL_SF_144("Réponse CI Suite Etude SF"),
    /**
     * The F l_ s f_145.
     */
    FL_SF_145("Réponse CI Suite Etude SF"),
    /**
     * The F l_ s f_146.
     */
    FL_SF_146("Réponse CI Suite Etude SF"),
    /**
     * The F l_ s f_147.
     */
    FL_SF_147("Demande d'annulation"),
    /**
     * The F l_ s f_150.
     */
    FL_SF_150("Annulation pour Signature"),
    /**
     * The F l_ s f_151.
     */
    FL_SF_151("Rejet Annulation"),
    /**
     * The F l_ s f_152.
     */
    FL_SF_152("Confirmation Annulation"),
    /**
     * The F l_ s f_153.
     */
    FL_SF_153("Refus signature - Annulation"),
    /**
     * The F l_ s f_154.
     */
    FL_SF_154("Réponse CI Suite Recevabilité"),
    /**
     * The F l_ s f_155.
     */
    FL_SF_155("Demande de prorogation"),
    /**
     * The F l_ s f_156.
     */
    FL_SF_156("Validation Suite Recevabilité - Demande de prorogation"),
    /**
     * The F l_ s f_157.
     */
    FL_SF_157("Prorogation"),
    /**
     * The F l_ s f_158.
     */
    FL_SF_158("Signature suite prorogation"),
    /**
     * The F l_ s f_159.
     */
    FL_SF_159("Rejet prorogation (Etude recevabilité)"),
    /**
     * The F l_ s f_160.
     */
    FL_SF_160("Rejet prorogation (Etude recevabilité)"),
    /**
     * The F l_ s f_161.
     */
    FL_SF_161("Rejet prorogation suite étude"),
    /**
     * The F l_ s f_162.
     */
    FL_SF_162("Demande de modification"),
    /**
     * The F l_ s f_163.
     */
    FL_SF_163("Validation Suite Recevabilité - Demande de modification"),
    /**
     * The F l_ s f_164.
     */
    FL_SF_164("Acceptation de la demande de modification"),
    /**
     * The F l_ s f_165.
     */
    FL_SF_165("Signature suite acceptation de la demande de modification"),
    /**
     * The F l_ s f_166.
     */
    FL_SF_166("Rejet de la demande demodification (Etude recevabilité)"),
    /**
     * The F l_ s f_167.
     */
    FL_SF_167("Rejet de la demande demodification"),
    /**
     * The F l_ s f_168.
     */
    FL_SF_168("Rejet de la demande demodification suite étude"),
    /**
     * The F l_ s f_169.
     */
    FL_SF_169("Apurement"),
    /**
     * The F l_ aler t_01.
     */
    FL_ALERT_01("Envoie Alerte"),
    //Flow code CC
    /**
     * The F l_ c c_85.
     */
    FL_CC_85("Dépot dossier CC"),
    /**
     * The F l_ a p_86.
     */
    FL_CC_86("Complément d'information Suite Recevabilité CC"),
    /**
     * The F l_ a p_87.
     */
    FL_CC_87("Rejet Suite Recevabilité CC"),
    /**
     * The F l_ a p_88.
     */
    FL_CC_88("Validation pour cotation CC"),
    /**
     * The F l_ a p_89.
     */
    FL_CC_89("Cotation_1"),
    /**
     * The F l_ a p_90.
     */
    FL_CC_90("Cotation_2"),
    /**
     * The F l_ a p_91.
     */
    FL_CC_91("Cotation_3"),
    /**
     * The F l_ a p_92.
     */
    FL_CC_92("Cotation_4"),
    /**
     * The F l_ a p_93.
     */
    FL_CC_93("Cotation_5"),
    /**
     * The F l_ a p_94.
     */
    FL_CC_94("Cotation_6"),
    /**
     * The F l_ a p_95.
     */
    FL_CC_95("Rejet Suite Etude_2 CC"),
    /**
     * The F l_ a p_96.
     */
    FL_CC_96("Rejet Suite Etude_3 CC"),
    /**
     * The F l_ a p_97.
     */
    FL_CC_97("Rejet Suite Etude_4 CC"),
    /**
     * The F l_ a p_98.
     */
    FL_CC_98("Rejet Suite Etude_5 CC"),
    /**
     * The F l_ a p_99.
     */
    FL_CC_99("Rejet Suite Etude_6 CC"),
    /**
     * The F l_ a p_100.
     */
    FL_CC_100("Rejet Suite Etude CC"),
    /**
     * The F l_ a p_101.
     */
    FL_CC_101("Acceptation Suite Etude CC"),
    /**
     * The F l_ a p_102.
     */
    FL_CC_102("Acceptation Suite Etude_6 CC"),
    /**
     * The F l_ a p_103.
     */
    FL_CC_103("Acceptation Suite Etude_5 CC"),
    /**
     * The F l_ a p_104.
     */
    FL_CC_104("Acceptation Suite Etude_4 CC"),
    /**
     * The F l_ a p_105.
     */
    FL_CC_105("Acceptation Suite Etude_3 CC"),
    /**
     * The F l_ a p_106.
     */
    FL_CC_106("Acceptation Suite Etude_2 CC"),
    /**
     * The F l_ a p_107.
     */
    FL_CC_107("Autorisation CC Suite Signature"),
    /**
     * The F l_ a p_108.
     */
    FL_CC_108("Visa 6"),
    /**
     * The F l_ a p_109.
     */
    FL_CC_109("Visa 5"),
    /**
     * The F l_ a p_110.
     */
    FL_CC_110("Visa 4"),
    /**
     * The F l_ a p_111.
     */
    FL_CC_111("Visa 3"),
    /**
     * The F l_ a p_112.
     */
    FL_CC_112("Visa 2"),
    /**
     * The F l_ a p_113.
     */
    FL_CC_113("CI Suite Etude_2 CC"),
    /**
     * The F l_ a p_114.
     */
    FL_CC_114("CI Suite Etude_3 CC"),
    /**
     * The F l_ a p_115.
     */
    FL_CC_115("CI Suite Etude_4 CC"),
    /**
     * The F l_ a p_116.
     */
    FL_CC_116("CI Suite Etude_5 CC"),
    /**
     * The F l_ a p_117.
     */
    FL_CC_117("CI Suite Etude_6 CC"),
    /**
     * The F l_ a p_118.
     */
    FL_CC_118("CI Suite Etude CC"),
    /**
     * The F l_ a p_119.
     */
    FL_CC_119("Retour Visa 6 - Etude CC"),
    /**
     * The F l_ a p_120.
     */
    FL_CC_120("Retour Visa 5 - Etude CC"),
    /**
     * The F l_ a p_121.
     */
    FL_CC_121("Retour Visa 5 - Etude_6 CC"),
    /**
     * The F l_ a p_122.
     */
    FL_CC_122("Retour Visa 4 - Etude CC"),
    /**
     * The F l_ a p_123.
     */
    FL_CC_123("Retour Visa 4 - Etude_6 CC"),
    /**
     * The F l_ a p_124.
     */
    FL_CC_124("Retour Visa 4 - Etude_5 CC"),
    /**
     * The F l_ a p_125.
     */
    FL_CC_125("Retour Visa 3 - Etude CC"),
    /**
     * The F l_ a p_126.
     */
    FL_CC_126("Retour Visa 3 - Etude_6 CC"),
    /**
     * The F l_ a p_127.
     */
    FL_CC_127("Retour Visa 3 - Etude_5 CC"),
    /**
     * The F l_ a p_128.
     */
    FL_CC_128("Retour Visa 3 - Etude_4 CC"),
    /**
     * The F l_ a p_129.
     */
    FL_CC_129("Retour Visa 2 - Etude CC"),
    /**
     * The F l_ a p_130.
     */
    FL_CC_130("Retour Visa 2 - Etude_6 CC"),
    /**
     * The F l_ a p_131.
     */
    FL_CC_131("Retour Visa 2 - Etude_5 CC"),
    /**
     * The F l_ a p_132.
     */
    FL_CC_132("Retour Visa 2 - Etude_4 CC"),
    /**
     * The F l_ a p_133.
     */
    FL_CC_133("Retour Visa 2 - Etude_3 CC"),
    /**
     * The F l_ a p_134.
     */
    FL_CC_134("Retour Signature - Etude CC"),
    /**
     * The F l_ a p_135.
     */
    FL_CC_135("Retour Signature - Etude_6 CC"),
    /**
     * The F l_ a p_136.
     */
    FL_CC_136("Retour Signature - Etude_5 CC"),
    /**
     * The F l_ a p_137.
     */
    FL_CC_137("Retour Signature - Etude_4 CC"),
    /**
     * The F l_ a p_138.
     */
    FL_CC_138("Retour Signature - Etude_3 CC"),
    /**
     * The F l_ a p_139.
     */
    FL_CC_139("Retour Signature - Etude_2 CC"),
    /**
     * The F l_ a p_140.
     */
    FL_CC_140("Retrait CC"),
    /**
     * The F l_ a p_141.
     */
    FL_CC_141("Réponse CI Suite Etude CC"),
    /**
     * The F l_ a p_142.
     */
    FL_CC_142("Réponse CI Suite Etude CC"),
    /**
     * The F l_ a p_143.
     */
    FL_CC_143("Réponse CI Suite Etude CC"),
    /**
     * The F l_ a p_144.
     */
    FL_CC_144("Réponse CI Suite Etude CC"),
    /**
     * The F l_ a p_145.
     */
    FL_CC_145("Réponse CI Suite Etude CC"),
    /**
     * The F l_ a p_146.
     */
    FL_CC_146("Réponse CI Suite Etude CC"),
    /**
     * The F l_ a p_147.
     */
    FL_CC_147("Demande d'annulation"),
    /**
     * The F l_ a p_150.
     */
    FL_CC_150("Annulation pour Signature"),
    /**
     * The F l_ a p_151.
     */
    FL_CC_151("Rejet Annulation"),
    /**
     * The F l_ a p_152.
     */
    FL_CC_152("Confirmation Annulation"),
    /**
     * The F l_ a p_153.
     */
    FL_CC_153("Refus signature - Annulation"),
    /**
     * The F l_ a p_154.
     */
    FL_CC_154("Réponse CI Suite Recevabilité"),
    /**
     * The F l_ c o_155.
     */
    FL_CC_155("Validation Pour Paiement"),
    /**
     * The F l_ c o_156.
     */
    FL_CC_156("Validation Paiement"),
    /**
     * The F l_ c o_157.
     */
    FL_CC_157("Demande d''Analyse"),
    /**
     * The F l_ c o_158.
     */
    FL_CC_158("Envoie Résultat d''Analyse"),
    /**
     * The F l_ c c_159.
     */
    FL_CC_159("Demande Contre Analyse"),
    /**
     * The F l_ c c_160.
     */
    FL_CC_160("CI Suite Contre Analyse"),
    /**
     * The F l_ c c_161.
     */
    FL_CC_161("Réponse CI Suite Contre Analyse"),
    /**
     * The F l_ c c_162.
     */
    FL_CC_162("Validation pour contre analyse"),
    /**
     * The F l_ c c_163.
     */
    FL_CC_163("Envoi Résultat de contre Analyse"),
    /**
     * The F l_ c c_164.
     */
    FL_CC_164("Rejet Intermediaire suite étude"),
    /**
     * The F l_ c c_165.
     */
    FL_CC_165("Acceptation Suite Etude CC"),
    /**
     * The F l_ c c_166.
     */
    FL_CC_166("Refus Suite Etude CC"),
    /**
     * The F l_ c c_167.
     */
    FL_CC_167("Validation pour traitement"),
    /**
     * The F l_ c c_168.
     */
    FL_CC_168("Rejet Definitif suite recevablité"),
    /**
     * The F l_ c c_169.
     */
    FL_CC_169("Rejet suite signature"),
    /**
     * The F l_ c c_170.
     */
    FL_CC_170("Autorisation CC Suite Signature"),
    /**
     * The F l_ c c_171.
     */
    FL_CC_171("Rejet Definitif suite recevablité"),
    /**
     * The F l_ c cvs_01.
     */
    FL_CT_CVS_01("Vérification approfondie"),
    /**
     * The F l_ c cvs_02.
     */
    FL_CT_CVS_02("Rejet Suite vérification Approfondie"),
    /**
     * The F l_ c cvs_03.
     */
    FL_CT_CVS_03("Signature du CCT (CVS)"),
    /**
     * The F l_ c cvs_04.
     */
    FL_CT_CVS_04("CI requis à vérification Approfondie"),
    /**
     * The F l_ c cvs_05.
     */
    FL_CT_CVS_05("Validation après vérification approfondie CVS"),
    /**
     * The F l_ c cvs_06.
     */
    FL_CT_CVS_06("Renvoi pour etude approfondie"),
    /**
     * The F l_ c cvs_07.
     */
    FL_CT_CVS_07("Signature du CCT (CVS)");


    /**
     * The label.
     */
    private final String label;

    /**
     * Instantiates a new flow code.
     *
     * @param label the label
     */
    private FlowCode(final String label) {
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

