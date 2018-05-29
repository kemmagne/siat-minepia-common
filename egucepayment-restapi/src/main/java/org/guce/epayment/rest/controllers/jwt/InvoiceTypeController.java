package org.guce.epayment.rest.controllers.jwt;

import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.InvoiceTypeService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.BankAccountDto;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.InvoiceTypeDto;
import org.guce.epayment.rest.dto.PartnerDto;
import org.guce.epayment.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("jwt")
public class InvoiceTypeController {

    @Autowired
    private CoreService coreService;
    @Autowired
    private InvoiceTypeService invoiceTypeService;

    @ResponseBody
    @RequestMapping(path = "invoices/types/all", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTypeDto>> findInvoicesTypes() {

        final List<InvoiceType> allInvoiceTypes = coreService.findAll(InvoiceType.class);

        return ResponseEntity.ok(allInvoiceTypes.stream().map(
                invoiceType -> RestUtils.getInvoiceTypeDto(invoiceType, true)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "invoices/types/{standalone}/{subType}", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTypeDto>> findInvoicesTypes(@PathVariable("standalone") boolean standalone,
            @PathVariable("subType") boolean subType) {

        final List<InvoiceType> invoiceTypes = invoiceTypeService.findByStandaloneAndSubType(standalone, subType);

        return ResponseEntity.ok(invoiceTypes.stream().map(
                invoiceType -> RestUtils.getInvoiceTypeDto(invoiceType, true)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "invoices/types/by-pms/{pms}", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTypeDto>> findInvoicesTypes(@PathVariable("pms") String pms) {

        final List<InvoiceType> invoiceTypes = invoiceTypeService.findByPaymentModes(pms);

        return ResponseEntity.ok(invoiceTypes.stream().map(
                invoiceType -> RestUtils.getInvoiceTypeDto(invoiceType, true)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "admin/invoices/types/{invoiceTypeId}", method = RequestMethod.DELETE)
    public ResponseEntity<DefaultDto> deleteInvoiceType(@PathVariable("invoiceTypeId") Integer invoiceTypeId) {

        coreService.deleteById(invoiceTypeId, InvoiceType.class, Integer.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/invoices/types", method = RequestMethod.POST)
    public ResponseEntity<InvoiceTypeDto> saveInvoiceType(@RequestBody InvoiceTypeDto invoiceTypeDto) {

        final InvoiceType invoiceType = new InvoiceType();

        invoiceType.setId(invoiceTypeDto.getId());
        invoiceType.setCode(invoiceTypeDto.getCode());
        invoiceType.setLabel(invoiceTypeDto.getLabel());
        invoiceType.setStandalone(invoiceTypeDto.isStandalone());
        invoiceType.setParameters(invoiceTypeDto.getParams());

        // benef
        invoiceType.setBeneficiaries(invoiceTypeDto.getBeneficiaries().stream().map(
                benef -> new InvoiceTypeBeneficiary(invoiceType,
                        coreService.findByUniqueKey(Constants.UK_CODE, benef.getCode(), Partner.class).get())
        ).collect(Collectors.toList()));

        // payment modes
        invoiceType.setPaymentModes(invoiceTypeDto.getPaymentModes().stream().map(
                pmDto -> coreService.findByUniqueKey(Constants.UK_CODE, pmDto.getCode(), PaymentMode.class).get()
        ).collect(Collectors.toList()));

        // save
        coreService.save(invoiceType, InvoiceType.class);

        return ResponseEntity.ok(invoiceTypeDto);
    }

    @ResponseBody
    @RequestMapping(path = "admin/invoices/types/accounts/{invoiceTypeCode}/{beneficiaryCode}",
            method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> saveInvoiceType(@RequestBody BankAccountDto accountDto,
            @PathVariable("invoiceTypeCode") String invoiceTypeCode,
            @PathVariable("beneficiaryCode") String beneficiaryCode) {

        final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, invoiceTypeCode, InvoiceType.class).get();
        final Partner beneficiary = coreService.findByUniqueKey(Constants.UK_CODE, beneficiaryCode, Partner.class).get();
        final InvoiceTypeBeneficiary ivtb = new InvoiceTypeBeneficiary(invoiceType, beneficiary);
        final BankAccount account = coreService.findById(accountDto.getId(), BankAccount.class).get();

        ivtb.setAccount(account);

        invoiceTypeService.setDebitAccount(ivtb);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/invoices/types/banks/{invoiceTypeCode}",
            method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> setBanks(@RequestBody List<PartnerDto> banks,
            @PathVariable String invoiceTypeCode) {

        final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, invoiceTypeCode, InvoiceType.class).get();

        invoiceType.setUnauthorizedBanks(banks.stream().map(
                bankDto -> coreService.findById(bankDto.getId(), Partner.class).get()
        ).collect(Collectors.toList()));

        coreService.save(invoiceType, InvoiceType.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/invoices/types/decision-makers/{invoiceTypeCode}",
            method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> setDecisionMakers(@RequestBody List<UserDto> decisionMakers,
            @PathVariable String invoiceTypeCode) {

        final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, invoiceTypeCode, InvoiceType.class).get();

        invoiceType.setDecisionMakers(decisionMakers.stream().map(
                userDto -> coreService.findById(userDto.getId(), User.class).get()
        ).collect(Collectors.toList()));

        coreService.save(invoiceType, InvoiceType.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/invoices/types/accounts/{invoiceTypeCode}/{beneficiaryCode}", method = RequestMethod.GET)
    public ResponseEntity<BankAccountDto> getDebitAccount(@PathVariable("invoiceTypeCode") String invoiceTypeCode,
            @PathVariable("beneficiaryCode") String beneficiaryCode) {

        final BankAccount debitAccount = invoiceTypeService.findBankAccount(invoiceTypeCode, beneficiaryCode).orElse(null);

        return ResponseEntity.ok(RestUtils.getBankAccountDto(debitAccount));
    }

}
