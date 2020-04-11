package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.FileTypeStepDao;
import org.guce.siat.common.dao.FlowDao;
import org.guce.siat.common.dao.FlowGuceSiatDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.ParamsOrganismDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.FlowGuceSiat;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;
import org.guce.siat.common.model.ParamsOrganism;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.service.ValidationFlowService;
import org.guce.siat.common.utils.XmlXPathUtils;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.FlowCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.guce.siat.common.utils.enums.ValidationType;
import org.guce.siat.common.utils.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;

/**
 * The Class ValidationFlowServiceImpl.
 */
@Service("validationFlowService")
@Transactional(readOnly = true)
public class ValidationFlowServiceImpl implements ValidationFlowService {

    private static final List<String> INIT_MODIFICATION_FLOWS_LIST = Arrays.asList("COCACM1", "COCAFM1");

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ValidationFlowServiceImpl.class);

    /**
     * The Constant LOCAL_BUNDLE_NAME.
     */
    private static final String LOCAL_BUNDLE_NAME = "org.guce.siat.messages.locale";

    /**
     * The line number expression.
     */
    private static final String LINE_NUMBER_EXPRESSION = "/DOCUMENT/CONTENT/MARCHANDISES/MARCHANDISE/LINE_NUMBER";

    /**
     * The Constant REFERENCE_SIAT_EXPRESSION.
     */
    private static final String REFERENCE_SIAT_EXPRESSION = "/DOCUMENT/REFERENCE_DOSSIER/REFERENCE_SIAT";

    /**
     * The Constant CODE_DECISION_EXPRESSION.
     */
    private static final String CODE_DECISION_EXPRESSION = "/DOCUMENT/CONTENT/DECISION_ORGANISME/CODE";

    /**
     * The code product decision expression.
     */
    final String CODE_PRODUCT_DECISION_EXPRESSION = "/DOCUMENT/CONTENT/MARCHANDISES/MARCHANDISE/DECISION_ORGANISME/CODE";

    /**
     * The validation exception message.
     */
    private String validationExceptionMessage;

    /**
     * The file dao.
     */
    @Autowired
    private FileDao fileDao;

    /**
     * The file type step dao.
     */
    @Autowired
    private FileTypeStepDao fileTypeStepDao;

    /**
     * The flow guce siat dao.
     */
    @Autowired
    private FlowGuceSiatDao flowGuceSiatDao;

    /**
     * The file item dao.
     */
    @Autowired
    private FileItemDao fileItemDao;

    /**
     * The flow dao.
     */
    @Autowired
    private FlowDao flowDao;

    /**
     * The item flow dao.
     */
    @Autowired
    private ItemFlowDao itemFlowDao;

    /**
     * The params dao.
     */
    @Autowired
    private ParamsDao paramsDao;

    /**
     * The params organism dao.
     */
    @Autowired
    private ParamsOrganismDao paramsOrganismDao;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.ValidationFlowService#validateFlowFromGuce(java.io.Serializable)
     */
    @Override
    public boolean validateFlowFromGuce(final Element rootElement) throws ValidationException, IndexOutOfBoundsException {
        LOG.info("####### Start Validattion Module ####### ");
        if ((isCancelFlux(rootElement) && !validateCancelRequest(rootElement)) || !validateDocuments(rootElement)) {
            LOG.error("####### Validation Failed  ####### ");
            throw new ValidationException(validationExceptionMessage);
        }

        return true;

    }

    /**
     * Checks if is cancel flux.
     *
     * @param rootElement the root element
     * @return true, if is cancel flux
     */
    private boolean isCancelFlux(final Element rootElement) {
        LOG.info("####### Start isCancelFlux####### ");
        boolean result = false;
        final String flowGuce = getDocumentType(rootElement);
        if (StringUtils.isNotBlank(flowGuce)) {
            final FlowGuceSiat flowSiat = flowGuceSiatDao.findFlowGuceSiatByFlowGuce(flowGuce);
            result = flowSiat != null
                    && flowSiat.getFlowSiat() != null
                    && (flowSiat.getFlowSiat().equals(FlowCode.FL_CT_61.name())
                    || flowSiat.getFlowSiat().equals(FlowCode.FL_AP_147.name())
                    || (flowSiat.getFlowSiat().equals(FlowCode.FL_CO_147.name()))
                    || (flowSiat.getFlowSiat().equals(FlowCode.FL_SF_147.name())) || (flowSiat.getFlowSiat()
                    .equals(FlowCode.FL_CC_147.name())));
        }
        LOG.info("#######isCancelFlux result : " + result);
        return result;
    }

    /**
     * Checks if is payment request.
     *
     * @param rootElement the root element
     * @return true, if is payment request
     */
    private boolean isPaymentRequest(final Element rootElement) {
        LOG.info("####### Start isPaymentRequest####### ");
        boolean result = false;
        final String flowGuce = getDocumentType(rootElement);
        if (StringUtils.isNotBlank(flowGuce)) {
            final FlowGuceSiat flowSiat = flowGuceSiatDao.findFlowGuceSiatByFlowGuce(flowGuce);
            result = flowSiat != null
                    && flowSiat.getFlowSiat() != null
                    && (flowSiat.getFlowSiat().equals(FlowCode.FL_CT_93.name())
                    || flowSiat.getFlowSiat().equals(FlowCode.FL_AP_166.name())
                    || (flowSiat.getFlowSiat().equals(FlowCode.FL_CO_156.name()))
                    || (flowSiat.getFlowSiat()
                            .equals(FlowCode.FL_CC_156.name())));
        }
        LOG.info("#######isCancelFlux result : " + result);
        return result;
    }

    /**
     * Checks if is counter analyse request.
     *
     * @param rootElement the root element
     * @return true, if is counter analyse request
     */
    private boolean isCounterAnalyseRequest(final Element rootElement) {
        LOG.info("####### Start isCountreAnalyserRequest####### ");
        boolean result = false;
        final String flowGuce = getDocumentType(rootElement);
        if (StringUtils.isNotBlank(flowGuce)) {
            final FlowGuceSiat flowGuceSiat = flowGuceSiatDao.findFlowGuceSiatByFlowGuce(flowGuce);
            result = flowGuceSiat != null && FlowCode.FL_CC_159.name().equals(flowGuceSiat.getFlowSiat());

        }
        LOG.info("#######isCountreAnalyserRequest result : " + result);
        return result;
    }

    /**
     * Find type document.
     *
     * @param rootElement the root element
     * @return the string
     */
    private String getDocumentType(final Element rootElement) {
        final String flowExpression = "/DOCUMENT/TYPE_DOCUMENT";
        return XmlXPathUtils.findSingleValue(flowExpression, rootElement);
    }

    /**
     * Find guce reference.
     *
     * @param rootElement the root element
     * @return the string
     */
    private String findNumDossierGuce(final Element rootElement) {
        final String referenceGuceExpression = "/DOCUMENT/REFERENCE_DOSSIER/NUMERO_DOSSIER";

        return XmlXPathUtils.findSingleValue(referenceGuceExpression, rootElement);
    }

    /**
     * Validate documents.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateDocuments(final Element rootElement) {
        return validateGeneralInformations(rootElement) && validateByProcedure(rootElement);
    }

    /**
     * Validate by procedure.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateByProcedure(final Element rootElement) {

        final String flux = getDocumentType(rootElement);

        if (isInitiatorFlow(rootElement)) {
            if (flux.startsWith("AH")) {
                return contentHasBureau(rootElement);
            } else if (flux.startsWith("EH")) {
                return true;
            } else if (flux.startsWith("DIE")) {
                return validateDiMINADER(rootElement);
            }
            if (flux.startsWith("AT_MINEPIA")) {
                return validateAtMINEPIA(rootElement);
            } else if (flux.startsWith("IDI")) {
                return validateIDI(rootElement);
            } else if (flux.startsWith("IDE")) {
                return validateIDE(rootElement);
            } else if (flux.startsWith("CC_DE")) {
                return validateCCDE(rootElement);
            }

        } else if (flux.startsWith("CC_BQ_") && isCounterAnalyseRequest(rootElement)) {

            return validateCounterAnalyseRequest(rootElement);

        }
        return true;
    }

    /**
     * Validate ide.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    public boolean validateIDE(final Element rootElement) {
        boolean haveDE = false;

        final String numExportationExpression = "/DOCUMENT/CONTENT/NUMERO_DE";
        final String numExportation = XmlXPathUtils.findSingleValue(numExportationExpression, rootElement);

        final File deFile = fileDao.findByNumDossierGuce(numExportation);

        if (deFile != null && FileTypeCode.DE_MINCOMMERCE.equals(deFile.getFileType().getCode())) {
            haveDE = true;
        }

        return haveDE;
    }

    /**
     * Validate idi.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    public boolean validateIDI(final Element rootElement) {
        boolean haveDI = false;
        boolean haveFIMEX = false;
        boolean haveFIMEXWF = false;

        final String numImportationExpression = "/DOCUMENT/CONTENT/NUMERO_DI";
        final String numImportation = XmlXPathUtils.findSingleValue(numImportationExpression, rootElement);

        final String numContribuableExpression = "/DOCUMENT/CONTENT/CLIENT/NUMERO_CONTRIBUABLE";
        final String numContribuable = XmlXPathUtils.findSingleValue(numContribuableExpression, rootElement);

        final File diFile = fileDao.findByNumDossierGuce(numImportation);
        final File fimexFile = fileDao.findByNumContribuable(numContribuable);
        final File fimexWfFile = fileDao.findByNumContribuable(numContribuable);

        if (diFile != null && FileTypeCode.DI_MINCOMMERCE.equals(diFile.getFileType().getCode())) {
            haveDI = true;
        }
        if (fimexFile != null && FileTypeCode.FIMEX.equals(fimexFile.getFileType().getCode())) {
            haveFIMEX = true;
        }
        if (fimexWfFile != null && FileTypeCode.FIMEX_WF.equals(fimexFile.getFileType().getCode())) {
            haveFIMEXWF = true;
        }

        return haveDI && haveFIMEX || haveDI && haveFIMEXWF;
    }

    /**
     * Validate ccde.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    public boolean validateCCDE(final Element rootElement) {
        boolean haveDE = false;
        boolean haveFIMEX = false;

        final String numOnccExportation = "/DOCUMENT/CONTENT/AFFAIRE_ONCC/NUM_AFFAIRE_ONCC";
        final String numExportation = XmlXPathUtils.findSingleValue(numOnccExportation, rootElement);

        final String numContribuableExpression = "/DOCUMENT/CONTENT/CLIENT/NUMERO_CONTRIBUABLE";
        final String numContribuable = XmlXPathUtils.findSingleValue(numContribuableExpression, rootElement);

        final File fimexFile = fileDao.findByNumContribuable(numContribuable);

        final ItemFlowData ccDvFile = itemFlowDao.retriveItemFlowDataDatabyFileTypeAndValue(FileTypeCode.CC_DV, numExportation);

        if (ccDvFile != null) {
            haveDE = true;
        }

        if (fimexFile != null && FileTypeCode.FIMEX.equals(fimexFile.getFileType().getCode())) {
            haveFIMEX = true;
        }

        return haveDE && haveFIMEX;
    }

    /**
     * Validate general informations.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateGeneralInformations(final Element rootElement) {
        if (isCancelFlux(rootElement) || isPaymentRequest(rootElement)) {
            return true;
        }
        LOG.info("#####start validateGeneralInformations");
        //		Validation Générale
        final boolean commonValidation = contentHasDocumentType(rootElement) && contentHasGuceNumber(rootElement)
                && contentHasNumMessage(rootElement);
        if (!commonValidation) {
            return false;
        }

        LOG.info("#####validateGeneralInformations commonValidation : " + commonValidation);
        //		Validation au cours du workFlow
        //				extractFileItems(rootElement).get(0).getFile().getFileType().getCode())){
        final List<FileItem> extractFileItemsResult = extractFileItems(rootElement);
        final boolean workflowValidation = isInitiatorFlow(rootElement)
                || CollectionUtils.isNotEmpty(extractFileItemsResult)
                && (!FileTypeCode.AM_MANIFEST.equals(extractFileItemsResult.get(0).getFile().getFileType().getCode())
                && !isInitiatorFlow(rootElement) && contentHasSiatNumber(rootElement) && validateFlow(rootElement)
                && contentHasCodeDecision(rootElement) && !isCancelFlux(rootElement) && !isPaymentRequest(rootElement) && correspondenceFileAndFileItems(rootElement));
        LOG.info("#####validateGeneralInformations workflowValidation : " + workflowValidation);
        return commonValidation && workflowValidation;
    }

    /**
     * Validate_ e h_ minader.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateEhMINADER(final Element rootElement) {

        boolean haveAieMINADER = false;
        boolean haveDiMINADER = false;

        final String numAutorisationExpression = "/DOCUMENT/CONTENT/NUMERO_AIE";
        final String numAutorisation = XmlXPathUtils.findSingleValue(numAutorisationExpression, rootElement);

        final String declarationExpression = "/DOCUMENT/CONTENT/NUMERO_DI";
        final String declaration = XmlXPathUtils.findSingleValue(declarationExpression, rootElement);

        final File autorisationFile = fileDao.findByNumDossierGuce(numAutorisation);
        final File declarationFile = fileDao.findByNumDossierGuce(declaration);

        if (autorisationFile != null && FileTypeCode.AIE_MINADER.equals(autorisationFile.getFileType().getCode())) {
            haveAieMINADER = true;
        }
        if (declarationFile != null && FileTypeCode.DI_MINADER.equals(declarationFile.getFileType().getCode())) {
            haveDiMINADER = true;
        }

        return haveAieMINADER && haveDiMINADER;
    }

    /**
     * Validate_ a t_ minader.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    //TODO fix this method
    private boolean validateAtMINEPIA(final Element rootElement) {

        boolean haveCCT;

//        final String numeroCCTExpression = "/DOCUMENT/CONTENT/NUMERO_CCT";
//
//        final String numeroCCT = XmlXPathUtils.findSingleValue(numeroCCTExpression, rootElement);
//
//        final File cctFile = fileDao.findByNumDossierGuce(numeroCCT);
//
//        if (cctFile != null && FileTypeCode.CCT_CT.equals(cctFile.getFileType().getCode())) {
////            haveCCT = true;
//        }
        //FIXME remove this line ()
        haveCCT = true;
        return haveCCT;
    }

    /**
     * Validate_ ai e_ minader.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean contentHasBureau(final Element rootElement) {
        final String bureauExpression = "/DOCUMENT/CONTENT/CODE_BUREAU";
        final String bureau = XmlXPathUtils.findSingleValue(bureauExpression, rootElement);

        return StringUtils.isNotBlank(bureau);
    }

    /**
     * Validate_ d i_ minader.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateDiMINADER(final Element rootElement) {
        boolean haveAH = false;
        final String ahExpression = "/DOCUMENT/CONTENT/NUMERO_ARRETE_HOMOLOGATION";
        final String ah = XmlXPathUtils.findSingleValue(ahExpression, rootElement);

        boolean haveAS = false;
        final String asExpression = "/DOCUMENT/CONTENT/NUMERO_AUTORISATION_SPECIALE";
        final String as = XmlXPathUtils.findSingleValue(asExpression, rootElement);

        boolean haveCERTIF = false;
        final String certifExpression = "/DOCUMENT/CONTENT/NUMERO_CERTIFICATION";
        final String certif = XmlXPathUtils.findSingleValue(certifExpression, rootElement);

        if (StringUtils.isNotBlank(ah)) {
            final File file = fileDao.findByNumDossierGuce(ah);

            if (file != null && FileTypeCode.AIE_MINADER.equals(file.getFileType().getCode())) {
                haveAH = true;
            }
        }

        if (StringUtils.isNotBlank(as)) {
            final File file = fileDao.findByNumDossierGuce(as);

            if (file != null && FileTypeCode.AS_MINADER.equals(file.getFileType().getCode())) {
                haveAS = true;
            }
        }

        if (StringUtils.isNotBlank(certif)) {
            final File file = fileDao.findByNumDossierGuce(certif);

            if (file != null && FileTypeCode.CAT_MINADER.equals(file.getFileType().getCode())) {
                haveCERTIF = true;
            }
        }

        return haveAH || haveAS || haveCERTIF;
    }

    /**
     * Validate cancel request.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateCancelRequest(final Element rootElement) {
        LOG.info("####### Start validateCancelRequest ####### ");
        boolean validateCancelRequest = false;
        final List<FileTypeCode> fileTypeListMinusCT = new ArrayList<>();
        fileTypeListMinusCT.addAll(Arrays.asList(FileTypeCode.values()));
        final List<FileTypeCode> fileTypeListCT = Arrays.asList(FileTypeCode.CCT_CT, FileTypeCode.CCT_CT_E, FileTypeCode.CC_CT, FileTypeCode.CQ_CT);
        final List<String> flowCodes = Arrays.asList(FlowCode.FL_CT_61.name(), FlowCode.FL_CO_147.name(),
                FlowCode.FL_SF_147.name(), FlowCode.FL_AP_147.name(), FlowCode.FL_AM_147.name());
        final String numDossier = findNumDossierGuce(rootElement);
        if (StringUtils.isNotBlank(numDossier)) {
            final File fileToSearch = fileDao.findByNumDossierGuce(numDossier);

            if (fileToSearch != null) {
                final List<FileItem> fileItems = fileToSearch.getFileItemsList();
                if (CollectionUtils.isNotEmpty(fileItems)) {
                    final FileType fileType = fileToSearch.getFileType();
                    Step currentStep;
                    //Vérifier si le nombre maximal des demandes d'annulation a été atteint
                    final ParamsOrganism nbrCancelRequestParam = paramsOrganismDao.findParamsOrganismByOrganismAndName(fileItems
                            .get(0).getFile().getBureau().getService().getSubDepartment().getOrganism(), "MaxCancelRequest");
                    Long paramOrganismValue;
                    if (nbrCancelRequestParam != null) {
                        paramOrganismValue = Long.parseLong(nbrCancelRequestParam.getValue());
                    } else {
                        paramOrganismValue = Long.parseLong(paramsDao.findParamsByName("MaxCancelRequest").getValue());
                    }

                    fileTypeListMinusCT.removeAll(fileTypeListCT);

                    if (fileTypeListMinusCT.contains(fileType.getCode())) {
                        currentStep = fileItems.get(0).getStep();

                        final boolean isApDecision = fileTypeStepDao.isApDecisionByFileTypeAndStep(fileType, currentStep);
                        final boolean hasDraftItem = fileItems.get(0).getDraft();
                        if (isApDecision
                                && itemFlowDao.findNbrDecisionByFileItemHistory(flowCodes, fileItems.get(0)) < paramOrganismValue
                                && BooleanUtils.isNotTrue(hasDraftItem)) {
                            validateCancelRequest = true;
                        }
                    } else if (fileTypeListCT.contains(fileType.getCode())) {
                        for (final FileItem fileItem : fileItems) {
                            currentStep = fileItem.getStep();
                            if (!StepCode.ST_CT_04.equals(currentStep.getStepCode())
                                    || itemFlowDao.findNbrDecisionByFileItemHistory(flowCodes, fileItem) >= paramOrganismValue) {
                                validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                                        ValidationType.CANCEL_REQUEST_UNAUTHORIZED.getCode());
                                return false;
                            }
                        }
                        validateCancelRequest = true;
                    }
                }
            }
        }

        LOG.info("####### Start validateCancelRequest boolean : " + validateCancelRequest);
        return validateCancelRequest;
    }

    //TODO ADD Payment request validation
    /**
     * Validate counter analyse request.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateCounterAnalyseRequest(final Element rootElement) {
        LOG.info("####### Start validateCounterAnalyseRequest ####### ");
        boolean validateCARequest = false;
        final String refSiat = findSiatNumber(rootElement);
        if (StringUtils.isNotBlank(refSiat)) {
            final File fileToSearch = fileDao.findByRefSiat(refSiat);

            if (fileToSearch != null) {
                final List<FileItem> fileItems = fileToSearch.getFileItemsList();
                if (CollectionUtils.isNotEmpty(fileItems)) {
                    final FileType fileType = fileToSearch.getFileType();
                    Step currentStep;

                    if (FileTypeCode.CC_BQ.equals(fileType.getCode())) {
                        currentStep = fileItems.get(0).getStep();

                        final boolean isApDecision = fileTypeStepDao.isApDecisionByFileTypeAndStep(fileType, currentStep);
                        if (isApDecision) {
//                            validateCARequest = true;
                        }
                    }
                    validateCARequest = true;
                }
            }
        }

        LOG.info("####### validateCounterAnalyseRequest result : " + validateCARequest);
        return validateCARequest;
    }

    /**
     * Correspondence file and file items.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean correspondenceFileAndFileItems(final Element rootElement) {

        final List<FileItem> recievedFileItems = extractFileItems(rootElement);

        final String codeDecison = XmlXPathUtils.findSingleValue(CODE_DECISION_EXPRESSION, rootElement);

        if (StringUtils.isBlank(codeDecison)) {
            final List<String> stringLineNumbers = XmlXPathUtils.findSingleValuesList(LINE_NUMBER_EXPRESSION, rootElement);
            if ((stringLineNumbers == null && recievedFileItems == null) || (recievedFileItems.size() == stringLineNumbers.size())) {
                if (CollectionUtils.isNotEmpty(recievedFileItems)) {
                    for (final FileItem fileItem : recievedFileItems) {
                        if (CollectionUtils.isNotEmpty(fileItem.getStep().getRoleList())) {

                            boolean foundGuceAuthority = false;
                            for (final Authority authority : fileItem.getStep().getRoleList()) {
                                if (authority.getRole().equals(AuthorityConstants.IMPORTATEUR.getCode())
                                        || authority.getRole().equals(AuthorityConstants.CONSIGNATAIRE_NAVIRE.getCode())) {
                                    foundGuceAuthority = true;
                                    break;
                                }
                            }
                            if (!foundGuceAuthority) {
                                validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                                        ValidationType.CORRESPONDENCE_FILE_FILEITEMS.getCode());
                                return false;
                            }
                        }
                    }
                }
                return true;

            }
            validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                    ValidationType.CORRESPONDENCE_FILE_FILEITEMS.getCode());
            return false;
        }
        return true;
    }

    /**
     * Validate flow.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean validateFlow(final Element rootElement) {
        final String flowGuce = getDocumentType(rootElement);
        boolean returnedValue = false;

        if (StringUtils.isNotBlank(flowGuce)) {
            final FlowGuceSiat flowGuceSiat = flowGuceSiatDao.findFlowGuceSiatByFlowGuce(flowGuce);

            if (flowGuceSiat != null) {
                Flow toBeExecutedFlow = flowDao.findFlowByCode(flowGuceSiat.getFlowSiat());
                final List<FileItem> fileItems = extractFileItems(rootElement);
                if (toBeExecutedFlow == null) {
                    toBeExecutedFlow = flowDao.findCiResponseFlow(getDecisionCode(rootElement));
                }

                if (CollectionUtils.isNotEmpty(fileItems)) {
                    //Demande RDV Visite
                    if (toBeExecutedFlow.getCode().equals(FlowCode.FL_CT_21.name())) {
                        returnedValue = validateLastFlow(fileItems, FlowCode.FL_CT_20.name());
                    } //Confirmation Visite à Quai
                    else if (toBeExecutedFlow.getCode().equals(FlowCode.FL_CT_36.name())) {
                        returnedValue = validateLastFlow(fileItems, FlowCode.FL_CT_18.name());
                    } //Régularisation
                    else if (toBeExecutedFlow.getCode().equals(FlowCode.FL_CT_45.name())) {
                        returnedValue = validateLastFlow(fileItems, FlowCode.FL_CT_16.name());
                    } //Demande de Réexamen
                    else if (toBeExecutedFlow.getCode().equals(FlowCode.FL_CT_49.name())) {
                        returnedValue = validateLastFlow(fileItems, FlowCode.FL_CT_12.name(), FlowCode.FL_CT_73.name());
                    } //Refoulement/Destruction
                    else if (toBeExecutedFlow.getCode().equals(FlowCode.FL_CT_54.name())) {
                        returnedValue = validateLastFlow(fileItems, FlowCode.FL_CT_14.name(), FlowCode.FL_CT_74.name());
                    } // Demande de Contre Analyse CC-BQ
                    else if (toBeExecutedFlow.getCode().equals(FlowCode.FL_CC_159.name())) {
                        returnedValue = validateLastFlow(fileItems, FlowCode.FL_CC_164.name());
                    } else {
                        if (CollectionUtils.isNotEmpty(fileItems)) {
                            for (final FileItem fileItem : fileItems) {
                                if (!fileItem.getStep().equals(toBeExecutedFlow.getFromStep())) {
                                    validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                                            ValidationType.VALIDATE_LAST_FLOW.getCode());
                                    return false;

                                }
                            }
                            returnedValue = true;
                        }
                    }
                }

            }
        }
        return returnedValue;
    }

    /**
     * Validate last flow.
     *
     * @param fileItems the file items
     * @param flowCodes the flow codes
     * @return true, if successful
     */
    private boolean validateLastFlow(final List<FileItem> fileItems, final String... flowCodes) {
        ItemFlow lastItemFlow;
        Flow lastFlow;
        for (final FileItem fileItem : fileItems) {
            if (fileItem != null) {
                lastItemFlow = itemFlowDao.findLastSentItemFlowByFileItem(fileItem);
                lastFlow = lastItemFlow.getFlow();

                for (final String flowCode : flowCodes) {
                    if (lastFlow.getCode().equals(flowCode)) {
                        return true;
                    }
                }
            }
        }

        validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                ValidationType.VALIDATE_LAST_FLOW.getCode());
        return false;
    }

    /**
     * Content has siat number.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean contentHasSiatNumber(final Element rootElement) {
        final String referenceSiat = XmlXPathUtils.findSingleValue(REFERENCE_SIAT_EXPRESSION, rootElement);
        boolean hasSiatNumber = false;

        if (StringUtils.isNotBlank(referenceSiat)) {
            final File fileToSearch = fileDao.findByRefSiat(referenceSiat);
            if (fileToSearch != null) {
                hasSiatNumber = true;
            } else {
                validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                        ValidationType.CONTENT_HAS_SIAT_NUMBER.getCode());
            }
        }
        return hasSiatNumber;
    }

    /**
     * Find siat number.
     *
     * @param rootElement the root element
     * @return the string
     */
    private String findSiatNumber(final Element rootElement) {
        return XmlXPathUtils.findSingleValue(REFERENCE_SIAT_EXPRESSION, rootElement);
    }

    /**
     * Content has num message.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean contentHasNumMessage(final Element rootElement) {
        final String numMessageExpression = "/DOCUMENT/MESSAGE/NUMERO_MESSAGE";
        final String numMessage = XmlXPathUtils.findSingleValue(numMessageExpression, rootElement);

        if (StringUtils.isNotBlank(numMessage)) {
            return true;
        }
        validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                ValidationType.CONTENT_HAS_NUM_MESSAGE.getCode());
        return false;

    }

    /**
     * Content has code decision.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean contentHasCodeDecision(final Element rootElement) {

        final String codeProductDecision = XmlXPathUtils.findSingleValue(CODE_PRODUCT_DECISION_EXPRESSION, rootElement);

        String codeFileDecision = null;

        if (StringUtils.isBlank(codeProductDecision)) {
            codeFileDecision = XmlXPathUtils.findSingleValue(CODE_DECISION_EXPRESSION, rootElement);
        }

        if (StringUtils.isNotBlank(codeProductDecision) || StringUtils.isNotBlank(codeFileDecision)) {
            return true;
        }

        validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                ValidationType.CONTENT_HAS_CODE_DECISION.getCode());
        return false;
    }

    /**
     * Content has document type.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean contentHasDocumentType(final Element rootElement) {

        final String documentType = getDocumentType(rootElement);

        if (StringUtils.isNotBlank(documentType)) {
            return true;

        }
        validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                ValidationType.CONTENT_HAS_DOCUMENT_TYPE.getCode());
        return false;

    }

    /**
     * Content has guce number.
     *
     * @param rootElement the root element
     * @return true, if successful
     */
    private boolean contentHasGuceNumber(final Element rootElement) {
        final String referenceGuce = findNumDossierGuce(rootElement);

        if (StringUtils.isNotBlank(referenceGuce)) {
            return true;
        }

        validationExceptionMessage = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
                ValidationType.CONTENT_HAS_GUCE_NUMBER.getCode());
        return false;

    }

    /**
     * Gets the decision code.
     *
     * @param rootElement the root element
     * @return the decision code
     */
    private String getDecisionCode(final Element rootElement) {
        final String codeFileDecision = XmlXPathUtils.findSingleValue(CODE_DECISION_EXPRESSION, rootElement);
        if (StringUtils.isNotBlank(codeFileDecision)) {
            return codeFileDecision;
        }
        return XmlXPathUtils.findSingleValue(CODE_PRODUCT_DECISION_EXPRESSION, rootElement);

    }

    /**
     * Extracted.
     *
     * @param rootElement the root element
     * @return the list
     */
    private List<FileItem> extractFileItems(final Element rootElement) {

        List<FileItem> extractedFileItems;

        final List<String> stringLineNumbers = XmlXPathUtils.findSingleValuesList(LINE_NUMBER_EXPRESSION, rootElement);
        final String numSiat = XmlXPathUtils.findSingleValue(REFERENCE_SIAT_EXPRESSION, rootElement);
        final List<Integer> lineNumberList = new ArrayList<>();
        if (stringLineNumbers.size() <= 999) {
            for (final String lineNumber : stringLineNumbers) {
                lineNumberList.add(Integer.valueOf(lineNumber));
            }
        }

        extractedFileItems = fileItemDao.findByLineNumberAndNumSiat(lineNumberList, numSiat);

        return extractedFileItems;
    }

    /**
     * Checks if is initiator flow.
     *
     * @param rootElement the root element
     * @return true, if is initiator flow
     */
    private boolean isInitiatorFlow(final Element rootElement) {

        String numDossierGuce = findNumDossierGuce(rootElement);

        if (StringUtils.isNotBlank(numDossierGuce)) {
            File file = fileDao.findByNumDossierGuce(numDossierGuce);
            String docType = getDocumentType(rootElement);

            return file == null || (INIT_MODIFICATION_FLOWS_LIST.contains(docType));
        }

        return true;
    }

}
