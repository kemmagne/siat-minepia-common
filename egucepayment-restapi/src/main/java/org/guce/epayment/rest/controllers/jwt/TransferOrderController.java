package org.guce.epayment.rest.controllers.jwt;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.ApplicationService;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.PartnerService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.InvoiceConstants;
import org.guce.epayment.exceptions.BadStepException;
import org.guce.epayment.exceptions.NotCreditAccountException;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.BankAccountDto;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.InvoiceDto;
import org.guce.epayment.rest.dto.PartnerDto;
import org.guce.epayment.rest.dto.TransferInitiationDto;
import org.guce.epayment.rest.dto.TransferOrderDto;
import org.guce.epayment.rest.dto.TransferValidationDto;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.models.FilterTransferOrder;
import org.guce.epayment.transfer.services.TransferOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
@RequestMapping("jwt/transfers/orders")
public class TransferOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferOrderController.class);

    @Autowired
    private TransferOrderService toService;
    @Autowired
    private CoreService coreService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private ApplicationService appService;

    @ResponseBody
    @RequestMapping(path = "initiation/{isBank}", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> init(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @PathVariable("isBank") boolean isBank, @RequestBody TransferInitiationDto transferInitiation) {

        try {
            List<InvoiceDto> invoiceDtos = transferInitiation.getInvoices();

            if (invoiceDtos.isEmpty()) {
                return ResponseEntity.ok(DefaultDto.of("3"));
            }

            final List<Map<String, Object>> invoices = Collections.emptyList();
            final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
            final Partner userPartner = connectedUser.getPartner();
            final String partnerTaxPayerNumber = userPartner.getTaxPayerNumber();
            final String partnerGroups = StringUtils
                    .collectionToDelimitedString(userPartner.getGroups().stream()
                            .map(pg -> pg.getCode()).collect(Collectors.toList()),
                            appService.getColSep());

            String invoiceTypeCode = null, beneficiaryCode = null, taxPayerNumber = null;
            PartnerDto beneficiaryDto, taxPayerDto;
            for (final InvoiceDto invoiceDto : invoiceDtos) {

                if (BigDecimal.ZERO.equals(invoiceDto.getAmountToPay())) {
                    continue;
                }

                // même type ???
                if (null == invoiceTypeCode) {
                    invoiceTypeCode = invoiceDto.getInvoiceType().getCode();
                } else if (!invoiceTypeCode.equals(invoiceDto.getInvoiceType().getCode())) {
                    return ResponseEntity.ok(DefaultDto.of("0"));
                }
                // même bénéficiaire ???
                beneficiaryDto = invoiceDto.getBeneficiary();
                if (null == beneficiaryCode && null != beneficiaryDto) {
                    beneficiaryCode = beneficiaryDto.getCode();
                } else if (null != beneficiaryCode && null != beneficiaryDto
                        && !beneficiaryCode.equals(beneficiaryDto.getCode())) {
                    return ResponseEntity.ok(DefaultDto.of("1"));
                }
                // même donneur d'ordre ???
                taxPayerDto = invoiceDto.getOwner();
                if (isBank) {
                    if (null == taxPayerNumber && null != taxPayerDto) {
                        taxPayerNumber = taxPayerDto.getTaxPayerNumber();
                    } else if (null != taxPayerNumber && null != taxPayerDto
                            && !taxPayerNumber.equals(taxPayerDto.getCode())) {
                        return ResponseEntity.ok(DefaultDto.of("2"));
                    }
                } else {
                    taxPayerNumber = taxPayerDto.getTaxPayerNumber();
                    // on s'assure que le contribuable de chaque facture est dans l'un des groupes auxquels appartiennent le commiter
                    if (!partnerService.checkTaxPayerNumberInGroups(partnerGroups, taxPayerNumber)) {
                        return ResponseEntity.ok(DefaultDto.of("4"));
                    } else if (!taxPayerNumber.equalsIgnoreCase(partnerTaxPayerNumber)) {
                        return ResponseEntity.ok(DefaultDto.of("4"));
                    }
                }

                final Map<String, Object> invoiceInfo = new HashMap<>();

                invoiceInfo.put(InvoiceConstants.INVOICE_ID, invoiceDto.getId());
                invoiceInfo.put(InvoiceConstants.INVOICE_VERSION_NUMBER, invoiceDto.getVersionNumber());
                invoiceInfo.put(InvoiceConstants.INVOICE_AMOUNT_TO_PAY, invoiceDto.getAmountToPay());

                invoices.add(invoiceInfo);
            }

            final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, userLogin, InvoiceType.class).get();

            if (isBank) {

                final List<Partner> unauthorizedBanks = invoiceType.getUnauthorizedBanks();

                if (!CollectionUtils.isEmpty(unauthorizedBanks)) {

                    boolean ok = true;
                    // on s'assure que la banque n'est pas exclue pour le type de facture
                    for (final Partner unauthorizedBank : unauthorizedBanks) {

                        final Partner parent = userPartner.getParent();
                        final String bankCode;

                        if (parent != null) {
                            bankCode = parent.getCode();
                        } else {
                            bankCode = userPartner.getCode();
                        }

                        if (bankCode.equalsIgnoreCase(unauthorizedBank.getCode())) {
                            // banque exclue
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) {
                        return ResponseEntity.ok(DefaultDto.of("4"));
                    }
                }
            }

            Partner beneficiary = null;
            if (null != beneficiaryCode) {
                beneficiary = coreService.findByUniqueKey(Constants.UK_CODE, beneficiaryCode, Partner.class).get();
            }

            final Partner taxPayer = coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER, taxPayerNumber, Partner.class).get();
            final PaymentMode paymentMode = coreService.findByUniqueKey(Constants.UK_CODE,
                    transferInitiation.getPaymentModeCode(), PaymentMode.class).get();
            final String originMessage = transferInitiation.getOriginMessage();
            final String signature = transferInitiation.getPrivateKey();
            final BankAccountDto debitAccountDto = transferInitiation.getDebitAccout();
            final BigDecimal accountId = debitAccountDto.getId();
            final BankAccount debitAccount;

            if (1 == BigDecimal.ZERO.compareTo(accountId)) {

                debitAccount = new BankAccount();

                String bankCode = debitAccountDto.getBankCode();
                final Partner theBank = coreService.findByUniqueKey(Constants.UK_CODE, bankCode, Partner.class).get();
                debitAccount.setBank(theBank);

                final Partner theAgency = coreService.findByUniqueKey(Constants.UK_CODE,
                        bankCode + Constants.GLOBAL_SEPERATOR + debitAccountDto.getAgencyCode(), Partner.class).get();
                debitAccount.setAgency(theAgency);
                debitAccount.setActive(true);
            } else {
                debitAccount = coreService.findById(accountId, BankAccount.class).get();
            }

            toService.init(connectedUser, invoiceType, invoices, beneficiary, taxPayer,
                    paymentMode, debitAccount, transferInitiation.getPartnerRef(), originMessage, signature);

            return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
        } catch (NotCreditAccountException ex) {
            LOGGER.info(ex.getMessage());
            return ResponseEntity.ok(DefaultDto.of("5"));
        }
    }

    @ResponseBody
    @RequestMapping(path = "validations", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> validate(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @RequestBody TransferValidationDto transferValidation) {

        try {

            final String originMessage = transferValidation.getOriginMessage();
            final String signature = transferValidation.getPrivateKey();
            final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();

            toService.validate(transferValidation.getReference(), connectedUser, originMessage, signature, transferValidation.isValid());

            return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
        } catch (BadStepException ex) {
            LOGGER.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @ResponseBody
    @RequestMapping(path = "{toValidate}/{start}/{end}/{count}", method = RequestMethod.GET)
    public ResponseEntity<List<TransferOrderDto>> findTransferOrders(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @RequestHeader(RestConstants.LOCALE) String locale, @PathVariable("toValidate") boolean toValidate,
            @PathVariable("start") int start, @PathVariable("end") int end, @PathVariable("count") boolean count) {

        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
        final List<TransferOrder> transferOrders;
        if (toValidate) {
            transferOrders = toService.findPartnerTransferOrders(connectedUser, toValidate, start, end, count);
        } else {
            transferOrders = toService.findPartnerTransferOrders(connectedUser, start, end, count);
        }

        return ResponseEntity.ok(transferOrders.stream().map(
                to -> RestUtils.getTransferOrderDto(to, locale)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "filter", method = RequestMethod.POST)
    public ResponseEntity filterTransferOrders(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @RequestHeader(RestConstants.LOCALE) String locale, @RequestBody FilterTransferOrder filter) {

        final int type = filter.getType();
        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();

        final String code;
        switch (type) {

            case 1: // bank

                final Partner partner = connectedUser.getPartner();
                final Partner parent = partner.getParent();
                if (null != parent) {
                    code = parent.getCode();
                } else {
                    code = partner.getCode();
                }
                break;

            case 2: // beneficiary
                code = connectedUser.getPartner().getCode();
                break;
            case 3: // decision maker
                code = userLogin;
                break;
            default:
                return ResponseEntity.ok(Collections.emptyList());
        }

        final int period = filter.getPeriod();
        long number;

        if (Constants.ZERO.equals(period)) {

            if (!filter.isCount()) {

                final List<TransferOrder> transferOrders = (List<TransferOrder>) toService.filterTransferOrders(filter, code);

                return ResponseEntity.ok(transferOrders.stream().map(
                        to -> RestUtils.getTransferOrderDto(to, locale)
                ).collect(Collectors.toList()));
            } else {

                number = (long) toService.filterTransferOrders(filter, code);

                return ResponseEntity.ok(DefaultDto.of(number + ""));
            }
        } else {

            if (!filter.isCount()) {

                final List<TransferOrder> transferOrders = (List<TransferOrder>) toService.findTransferOrdersPeriodically(type, period, code, filter.getStart(), filter.getEnd(), false);

                return ResponseEntity.ok(transferOrders.stream().map(
                        to -> RestUtils.getTransferOrderDto(to, locale)
                ).collect(Collectors.toList()));
            } else {

                number = (long) toService.findTransferOrdersPeriodically(type, period, code, 0, 0, true);

                return ResponseEntity.ok(DefaultDto.of(number + ""));
            }
        }
    }

    @ResponseBody
    @RequestMapping(path = "by-validator/{start}/{end}/{count}", method = RequestMethod.GET)
    public ResponseEntity findByUser(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @RequestHeader(RestConstants.LOCALE) String locale,
            @PathVariable("start") int start, @PathVariable("end") int end, @PathVariable("count") boolean count) {

        if (!count) {

            final List<TransferOrder> transferOrders = (List<TransferOrder>) toService.findByTosUser(userLogin, start, end, count);

            return ResponseEntity.ok(transferOrders.stream().map(
                    to -> RestUtils.getTransferOrderDto(to, locale)
            ).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(toService.findByTosUser(userLogin, 0, 0, true).toString());
        }
    }

    @ResponseBody
    @RequestMapping(path = "acknowled/{start}/{end}/{count}", method = RequestMethod.GET)
    public ResponseEntity findAcknowled(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @RequestHeader(RestConstants.LOCALE) String locale,
            @PathVariable("start") int start, @PathVariable("end") int end, @PathVariable("count") boolean count) {

        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
        final String beneficiaryCode = connectedUser.getPartner().getCode();

        if (!count) {

            final List<TransferOrder> transferOrders = (List<TransferOrder>) toService
                    .getAcknowledTransferOrders(beneficiaryCode, start, end, count);

            return ResponseEntity.ok(transferOrders.stream().map(
                    to -> RestUtils.getTransferOrderDto(to, locale)
            ).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(toService.getAcknowledTransferOrders(beneficiaryCode, 0, 0, true).toString());
        }
    }

}
