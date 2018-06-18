package org.guce.epayment.transfer.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.core.entities.SittingDate;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.core.repositories.InvoiceTypeBeneficiaryRepository;
import org.guce.epayment.core.repositories.PartnerRepository;
import org.guce.epayment.core.repositories.PaymentRepository;
import org.guce.epayment.core.repositories.SittingDateRepository;
import org.guce.epayment.core.repositories.StepRepository;
import org.guce.epayment.core.services.ApplicationService;
import org.guce.epayment.core.services.PartnerStepService;
import org.guce.epayment.core.services.PaymentService;
import org.guce.epayment.core.services.UserStepService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.exceptions.BadStepException;
import org.guce.epayment.exceptions.NotCreditAccountException;
import org.guce.epayment.transfer.dao.TransferOrderDao;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.models.FilterTransferOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TransferOrderServiceImpl implements TransferOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferOrderServiceImpl.class);

    private static final String TO_REF_PREFIX = "TO".intern();
    private static final String TO_SEQ = "TRANSFER_ORDER_SEQ".intern();
    private static final String IVT_LAST_STEP_PARAM_KEY = "transfer.last.step".intern();
    private static final String IVT_BENEFICIARY_AGENCY_PARAM_KEY = "beneficiary.agency".intern();

    @Autowired
    private SittingDateRepository sittingDateRepository;
    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private InvoiceTypeBeneficiaryRepository ivtbRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TransferOrderDao transferOrderDao;

    @Autowired
    private PartnerStepService partnerStepService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ApplicationService appService;
    @Autowired
    private UserStepService userStepService;

    @Override
    public void init(final User connectedUser, final InvoiceType invoiceType, final List<Map<String, Object>> invoices,
            final Partner beneficiary, final Partner taxPayer, final PaymentMode paymentMode, final BankAccount debitAccount,
            final String partnerReference, final String originMessage, final String signature) {

        final Partner userPartner = connectedUser.getPartner();
        final Partner bankGateway = debitAccount.getBank();

        final TransferOrder transferOrder = (TransferOrder) paymentService.init(invoices, paymentMode, userPartner,
                partnerReference, bankGateway, Constants.HUNDRED, false);

        // debit account
        transferOrder.setDebitAccount(debitAccount);
        // invoice type
        transferOrder.setInvoiceType(invoiceType);
        // donneur d'ordre
        transferOrder.setTaxPayer(taxPayer);
        // agence réceptrice du virement
        final Partner receptiveAgency = debitAccount.getAgency();
        transferOrder.setReceptiveAgency(receptiveAgency);
        // compte bancaire à créditer
        BankAccount creditAccount = null;
        if (null != beneficiary) {
            creditAccount = ivtbRepository.findBankAccount(invoiceType.getCode(), beneficiary.getCode()).orElse(null);
            transferOrder.setCreditAccount(creditAccount);
        }
        //
        final Properties ivtProps = CoreUtils.getParams(Optional.ofNullable(invoiceType.getParameters()));
        // recherche du premier status suivant le mode de payment et le type de facture
        final String globalFlow = paymentMode.getGlobalFlow();
        final String ivtLastStepCode = ivtProps.getProperty(IVT_LAST_STEP_PARAM_KEY, "");
        final String subFlow = globalFlow.substring(0, globalFlow.indexOf(ivtLastStepCode) + ivtLastStepCode.length());
        final String transferFlow = subFlow.isEmpty() ? globalFlow : subFlow;
        // flux à prendre en compte
        transferOrder.setFlowInUse(transferFlow);

        // pour qu'il n'y ait pas de compte à créditer et donc d'agence bénéficiaire,
        // il faudrait que la dernière étape dans le flow du type de facture soit la validation par la banque du bénéficiare
        // NB : le code application d'une agence est constitué en concatenant le code de la banque au code interne à la banque de celle-ci
        String beneficiaryAgencyCode = ivtProps.getProperty(IVT_BENEFICIARY_AGENCY_PARAM_KEY, "");
        Partner beneficiaryAgency, beneficiaryBank;
        if (!beneficiaryAgencyCode.isEmpty()) {
            beneficiaryAgency = partnerRepository.findByCode(beneficiaryAgencyCode).get();
            beneficiaryBank = beneficiaryAgency.getParent();
        } else {
            beneficiaryAgency = null != creditAccount ? creditAccount.getAgency() : null;
            beneficiaryBank = null != creditAccount ? creditAccount.getBank() : null;
        }
        if (null == beneficiaryAgency && transferFlow.contains(Step.STEP_TO_BENEFICIARY_BANK)) {
            throw new NotCreditAccountException("A bank account for invoice type " + invoiceType.getCode() + " hasn't been set !");
        }
        //
        transferOrder.setBeneficiaryAgency(beneficiaryAgency);
        transferOrder.setBeneficiaryBank(beneficiaryBank);
        // signature
        final String[] flowTab = StringUtils.split(transferFlow, appService.getColSep());
        final String firstStepCode = flowTab[0];
        final Step firstStep = stepRepository.findByCode(firstStepCode).get();
        final Signature userSignature = paymentService.buildUserSignature(originMessage, signature, 0, connectedUser, firstStep, transferOrder);
        transferOrder.setSignatures(Arrays.asList(userSignature));
        /**
         * il est possible qu'à ce niveau les factures soient marquées payées
         * pour deux cas :
         *
         * pour le type de facture c'est le dernier statut et le plus haut
         * niveau pour la banque c'est 1
         *
         * la banque est à la fois banque donneur d'ordre et banque bénéficiaire
         * et le niveau de validation le plus élevé c'est 1
         */
        boolean canValidate = canValidate(transferOrder, firstStep, userPartner, Constants.ZERO);
        if (canValidate) {
            paymentService.updateInvoices(transferOrder, Invoice.INVOICE_PAID);
            paymentService.setDecision(transferOrder, Payment.PAYMENT_VALIDATED);
            setSittingDate(transferOrder);
        }
        // save
        paymentService.save(transferOrder, TO_SEQ, TO_REF_PREFIX);

    }

    @Override
    public void validate(final String toReference, final User connectedUser,
            final String originMessage, final String signature, final boolean valid) {

        final TransferOrder transferOrder = (TransferOrder) paymentRepository.findByReference(toReference).get();
        final List<Signature> signatures = transferOrder.getSignatures();
        final Partner userPartner = connectedUser.getPartner();
        final Signature userSignature;

        if (valid) {

            // on veut le statut pour lequel l'utilisateur doit signer ainsi que le niveau
            final PaymentMode paymentMode = transferOrder.getMode();
            final Signature lastSignature = signatures.get(0);
            final Step lastStep = lastSignature.getStep();
            Step step = null;
            final int lastLevel = lastSignature.getLevel(), level;
            // on doit récupérer le status de l'utilisateur
            // soit il a le même que le dernier avec niveau + 1 soit il a le suivant avec niveau 1
            // ça peut aussi être le premier validateur de banque du coup même status mais avec niveau 1
            Optional<UserStep> userStep = userStepService.find(connectedUser, lastStep, paymentMode, lastLevel + 1);

            if (userStep.isPresent()) {

                step = lastStep;
                level = lastLevel + 1;
            } else {

                boolean bankFist = false;
                level = 1;

                if (!userPartner.getChildren().isEmpty()) {

                    userStep = userStepService.find(connectedUser, lastStep, paymentMode, 1);
                    if (userStep.isPresent()) {

                        step = lastStep;
                        bankFist = true;
                    }
                }

                if (!bankFist) {

                    String transferFlow = transferOrder.getFlowInUse();
                    List<String> stepsList = Arrays.asList(StringUtils.split(transferFlow, appService.getColSep()));
                    int indexOfLastStep = stepsList.indexOf(lastStep.getCode());
                    String nextStepCode = stepsList.get(indexOfLastStep + 1);
                    step = stepRepository.findByCode(nextStepCode).get();
                    userStep = userStepService.find(connectedUser, step, paymentMode, 1);
                }
            }

            if (!userStep.isPresent()) {
                throw new BadStepException("The user " + connectedUser.getLogin() + " doesn't have step " + (null != step ? step.getCode() : "") + " and validation level " + level + " and is trying to validate a transfer order");
            }

            userSignature = paymentService.buildUserSignature(originMessage, signature, level, connectedUser, step, transferOrder);

            if (canValidate(transferOrder, step, userPartner, level)) {

                paymentService.updateInvoices(transferOrder, Invoice.INVOICE_PAID);
                paymentService.setDecision(transferOrder, Payment.PAYMENT_VALIDATED);
                setSittingDate(transferOrder);
            }
        } else {

            String partnerCode = userPartner.getCode();

            if (partnerCode.equalsIgnoreCase(transferOrder.getCommiter().getCode())) {

                // annulation
                final Step cancellationStep = stepRepository.findByCode(Step.STEP_CANCELED).get();

                userSignature = paymentService.buildUserSignature(originMessage, signature, 0, connectedUser, cancellationStep, transferOrder);

                paymentService.updateInvoices(transferOrder, Invoice.INVOICE_PAYMENT_CANCELED);
                paymentService.setDecision(transferOrder, Payment.PAYMENT_CANCELED);
            } else {

                // rejet
                final Step rejectionStep = stepRepository.findByCode(Step.STEP_REJECTED).get();

                userSignature = paymentService.buildUserSignature(originMessage, signature, 0, connectedUser, rejectionStep, transferOrder);

                paymentService.updateInvoices(transferOrder, Invoice.INVOICE_PAYMENT_REJECTED);
                paymentService.setDecision(transferOrder, Payment.PAYMENT_REJECTED);
            }
        }

        transferOrder.getSignatures().add(userSignature);
        paymentService.update(transferOrder);
    }

    private boolean canValidate(TransferOrder transferOrder, Step step, Partner partner, int currentLevel) {

        if (Step.STEP_TO_PRINCIPAL.equalsIgnoreCase(step.getCode())) {
            // si c'est un donneur d'ordre pas la peine de continuer
            return false;
        }

        final Optional<PartnerStep> partnerStep = partnerStepService.find(partner, step, transferOrder.getMode(), currentLevel + 1);

        if (partnerStep.isPresent()) {
            // il y'a au moins un niveau de validation chez ce partenaire
            return false;
        }

        // il peut ne pas y avoir un niveau défini plutôt au niveau de la banque
        final Partner parentPartner = partner.getParent();
        if (null != parentPartner && partnerStepService.find(parentPartner, step, transferOrder.getMode()).isPresent()) {
            // il y a au moins un niveau de validation défini au niveau de la banque
            return false;
        }

        // on doit maintenant savoir s'il y a d'autres étapes dans le flux de paiement
        final String stepCode = step.getCode();
        final String transferFlow = transferOrder.getFlowInUse();
        final List<String> stepsList = Arrays.asList(StringUtils.split(transferFlow, appService.getColSep()));
        final String lastStepCode = stepsList.get(stepsList.size() - 1);

        if (stepCode.equals(lastStepCode)) {
            // le status courant est le dernier status
            return true;
        }

        // le statut courant n'est pas le dernier
        // la banque courante est-elle banque donneur d'ordre et banque bénéficiaire ???
        return transferOrder.getBankGateway().equals(transferOrder.getBeneficiaryBank());
    }

    private void setSittingDate(TransferOrder transferOrder) {
        List<SittingDate> sittingDates = sittingDateRepository.findAll();
        if (!sittingDates.isEmpty()) {
            transferOrder.setSittingDate(DateUtils.getSittingDate(sittingDates.get(0).getMinTime(), sittingDates.get(0).getMaxTime()));
        }
    }

    @Override
    public List<TransferOrder> findPartnerTransferOrders(User connectedUser, int start, int end, boolean count) {
        return transferOrderDao.findPartnerTransferOrders(connectedUser, start, end, count);
    }

    @Override
    public List<TransferOrder> findPartnerTransferOrders(User connectedUser, boolean toValidate, int start, int end, boolean count) {
        return transferOrderDao.findPartnerTransferOrders(connectedUser, toValidate, start, end, count);
    }

    @Override
    public Object filterTransferOrders(FilterTransferOrder filter, String code) {
        return transferOrderDao.filterTransferOrders(filter, code);
    }

    @Override
    public Object findTransferOrdersPeriodically(int type, int period, String code, int start, int end, boolean count) {
        return transferOrderDao.findTransferOrdersPeriodically(type, period, code, start, end, count);
    }

    @Override
    public List<TransferOrder> findLastTransferOrders(int type, String code, int number) {
        return transferOrderDao.findLastTransferOrders(type, code, number);
    }

    @Override
    public Object findByTosUser(String userLogin, int start, int end, boolean count) {
        return transferOrderDao.findByTosUser(userLogin, start, end, count);
    }

    @Override
    public List getTosStats(int type, boolean bank) {
        return transferOrderDao.getTosStats(type, bank);
    }

    @Override
    public Object getAcknowledTransferOrders(String benefCode, int start, int end, boolean count) {
        return transferOrderDao.getAcknowledTransferOrders(benefCode, start, end, count);
    }

}
