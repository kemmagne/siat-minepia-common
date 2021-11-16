package org.guce.siat.common.utils;

import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.model.Attachment;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlow;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlowData;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.ebms.EbmsUtility;
import org.guce.siat.common.utils.ebms.UtilitiesException;
import org.guce.siat.common.utils.enums.InformationSystemCode;
import org.guce.siat.common.utils.io.IOUtils;
import org.guce.siat.utility.jaxb.common.DECISIONORGANISME;
import org.guce.siat.utility.jaxb.common.MESSAGE;
import org.guce.siat.utility.jaxb.common.PIECESJOINTES;
import org.guce.siat.utility.jaxb.common.PIECESJOINTES.PIECEJOINTE;
import org.guce.siat.utility.jaxb.common.REFERENCEDOSSIER;
import org.guce.siat.utility.jaxb.common.ROUTAGE;

/**
 * The Class ConverterGuceSiatUtils.
 */
public final class ConverterGuceSiatUtils {

    /**
     * Instantiates a new converter guce siat utils.
     */
    private ConverterGuceSiatUtils() {
    }

    /**
     * Generate routage siat to guce.
     *
     * @param file the file
     * @return the routage
     */
    public static ROUTAGE generateRoutageSiatToGuce(final File file) {

        final ROUTAGE routage = new ROUTAGE();

        routage.setDESTINATAIRE(file.getEmetteur());
        routage.setEMETTEUR(file.getDestinataire());

        return routage;
    }

    /**
     * Generate message.
     *
     * @param numMsgOrigine the num msg origine
     * @return the message
     */
    public static MESSAGE generateMessage(final String numMsgOrigine) {

        final MESSAGE message = new MESSAGE();

        message.setNUMEROMESSAGE(IOUtils.generateMessageID());
        message.setDATEEMISSION(EbmsUtility.getCurrentUTCDateTime());
        message.setNUMEROMESSAGEORIGINE(numMsgOrigine);
        message.setDATEEMISSIONMSGORIGINE(StringUtils.EMPTY);
        message.setETAT(StringUtils.EMPTY);
        message.setTYPEMESSAGE(StringUtils.EMPTY);

        return message;
    }

    /**
     * Gets the reference dossier.
     *
     * @param file the file
     * @param isCancelResponse the is cancel response
     * @return the reference dossier
     * @throws UtilitiesException the utilities exception
     */
    public static REFERENCEDOSSIER generateReferenceDossier(final File file, final Boolean isCancelResponse) throws UtilitiesException {

        final REFERENCEDOSSIER referencedossier = new REFERENCEDOSSIER();

        String numeroDossier = FileUtils.getNumeroDossierGuce(file);

        //	reference dossier
        referencedossier.setREFERENCEGUCE(file.getReferenceGuce());
        referencedossier.setREFERENCESIAT(file.getReferenceSiat());
        referencedossier.setDATECREATION(EbmsUtility.date2UTC(file.getCreatedDate(), TimeZone.getDefault()));
        referencedossier.setNUMERODEMANDE(file.getNumeroDemande() == null ? StringUtils.EMPTY : file.getNumeroDemande());
        referencedossier.setNUMERODOSSIER(numeroDossier);
        referencedossier.setSERVICE(file.getFileTypeGuce());
        switch (file.getFileType().getCode()) {
            //CT
            case AIE_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AE_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case EH_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case VTP_MINSANTE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case VTD_MINSANTE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AI_MINSANTE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CCS_MINSANTE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AI_MINMIDT:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AE_MINMIDT:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CEA_MINMIDT:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AT_MINEPIA:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case VT_MINEPIA:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case VT_MINEPDED:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CP_MINEPDED:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AS_MINFOF:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case BSBE_MINFOF:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CO_MINFOF_FORET:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CO_MINFOF_FAUNE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CCT_CT:
            case CCT_CSV:
            case CCT_CT_E:
            case CCT_CT_E_PVI:
            case CCT_CT_E_ATP:
            case CCT_CT_E_FSTP:
            case CCT_CT_E_PVE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CC_CT:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CQ_CT:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AS_MINCOMMERCE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case FIMEX:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case FIMEX_WF:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case DI_MINCOMMERCE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case DE_MINCOMMERCE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case IDI:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case IDE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case CAT_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AS_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case PIVPSRP_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case AT_MINSANTE:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;
            case DI_MINADER:
                referencedossier.setSI(InformationSystemCode.CT.name());
                break;

            //CO
            case CO_GCO:
                referencedossier.setSI(InformationSystemCode.CO.name());
                break;
            case CO_GFC:
                referencedossier.setSI(InformationSystemCode.CO.name());
                break;
            //AM
            case AM_DOI:
                referencedossier.setSI(InformationSystemCode.AM.name());
                break;
            case AM_DOE:
                referencedossier.setSI(InformationSystemCode.AM.name());
                break;
            case AM_MANIFEST:
                referencedossier.setSI(InformationSystemCode.AM.name());
                break;
            //FT
            case FT_DF:
                referencedossier.setSI(InformationSystemCode.FT.name());
                break;
            case FT_LVI:
                referencedossier.setSI(InformationSystemCode.FT.name());
                break;
            //SF
            case SF_GVE:
                referencedossier.setSI(InformationSystemCode.SF.name());
                break;
            case SF_AF:
                referencedossier.setSI(InformationSystemCode.SF.name());
                break;
            case SF_QF:
                referencedossier.setSI(InformationSystemCode.SF.name());
                break;
            case SF_AR:
                referencedossier.setSI(InformationSystemCode.SF.name());
                break;
            //CC
            case CC_DV:
                referencedossier.setSI(InformationSystemCode.CC.name());
                break;
            case CC_DE:
                referencedossier.setSI(InformationSystemCode.CC.name());
                break;
            case CC_BQ:
                referencedossier.setSI(InformationSystemCode.CC.name());
                break;
//            case "CC_CC":
//                referencedossier.setSI(InformationSystemCode.CC.name());
//                break;
            case CC_COCAC:
                referencedossier.setSI(InformationSystemCode.CC.name());
                break;
            case CC_COCAF:
                referencedossier.setSI(InformationSystemCode.CC.name());
                break;

            default:
                break;
        }

        if (isCancelResponse) {
            referencedossier.setREFERENCEGUCE(file.getReferenceGuceAnnulation());
            referencedossier.setSERVICE(file.getFileTypeGuceAnnulation());
            referencedossier.setNUMERODEMANDE(file.getNumeroDemandeAnnulation() == null ? StringUtils.EMPTY : file
                    .getNumeroDemandeAnnulation());
        }

        return referencedossier;

    }

    /**
     * Generate decision organisme.
     *
     * @param flowToExecute the flow to execute
     * @param itemFlowDataToInsert the item flow data to insert
     * @return the decisionorganisme
     */
    public static DECISIONORGANISME generateDecisionOrganisme(final Flow flowToExecute, final ItemFlowData itemFlowDataToInsert) {

        if (itemFlowDataToInsert == null) {
            return new DECISIONORGANISME();
        }

        final DECISIONORGANISME decisionorganisme = new DECISIONORGANISME();

        decisionorganisme.setCODE(flowToExecute.getCode());
        decisionorganisme.setLIBELLE(flowToExecute.getLabelFr());
        final FileType fileType = itemFlowDataToInsert.getItemFlow().getFileItem().getFile().getFileType();
        final List<FileTypeFlow> fileTypeFlows = fileType.getFileTypeFlowList();
        for (final FileTypeFlow fileTypeFlow : fileTypeFlows) {
            if (flowToExecute.getCode().equals(fileTypeFlow.getPk().getFlow().getCode())) {
                decisionorganisme.setLIBELLE(fileTypeFlow.getLabelFr());
            }
        }
        decisionorganisme.setOBSERVATION(itemFlowDataToInsert.getValue());

        return decisionorganisme;

    }

    /**
     * Checks if is decision dossier.
     *
     * @param file the file
     * @param fileItemList the file item list
     * @return the boolean
     */
    public static Boolean isDecisionDossier(final File file, final List<FileItem> fileItemList) {
        boolean decisionDossier = false;
        if (file.getFileItemsList().size() == fileItemList.size()) {
            decisionDossier = true;
            Step currentStep = null;

            /**/
            for (final FileItem fileItem : fileItemList) {
                if (currentStep == null) {
                    currentStep = fileItem.getStep();
                } else if (!currentStep.getId().equals(fileItem.getStep().getId())) {
                    decisionDossier = false;
                }
            }
        }

        return decisionDossier;
    }

    /**
     * Generate pieces jointes.
     *
     * @param attachmentList the attachment list
     * @return the piecesjointes
     */
    public static PIECESJOINTES generatePiecesJointes(final List<Attachment> attachmentList) {

        final PIECESJOINTES piecesjointes = new PIECESJOINTES();

        for (final Attachment attachment : attachmentList) {
            final PIECEJOINTE pj = new PIECEJOINTE();

            pj.setLIBELLEPJ(attachment.getDocumentName());
            if (StringUtils.isNotBlank(attachment.getAttachmentType())) {
                pj.setTYPEPJ(attachment.getAttachmentType());
            }

            piecesjointes.getPIECEJOINTE().add(pj);
        }

        return piecesjointes;

    }

}
