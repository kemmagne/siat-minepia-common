package org.guce.epayment.rest.controllers.digest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.InvoiceService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.dto.InvoiceVersionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("digest/integration")
public class IntegrationController {

    @Autowired
    private CoreService coreService;
    @Autowired
    private InvoiceService invoiceService;

    @ResponseBody
    @RequestMapping(path = "invoices/many", method = RequestMethod.POST)
    public void integrateManyInvoices(@RequestBody List<InvoiceVersionDto> invVersDtos) {
        invVersDtos.forEach(invVersDto -> integrateInvoice(invVersDto));
    }

    @ResponseBody
    @RequestMapping(path = "invoices/one", method = RequestMethod.POST)
    public void integrateOneInvoice(@RequestBody InvoiceVersionDto invVersDto) {
        integrateInvoice(invVersDto);
    }

    private void integrateInvoice(InvoiceVersionDto invVersDto) {

        final String invoiceNumber = invVersDto.getInvoiceNumber().toUpperCase();
        final String invoiceTypeCode = invVersDto.getInvoiceTypeCode().toUpperCase();
        final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, invoiceTypeCode,
                InvoiceType.class).get();
        final Invoice principalInvoice = invoiceService.findByNumberAndType(invoiceNumber, invoiceType.getId())
                .orElse(new Invoice());
        final BigDecimal versionAmount = invVersDto.getAmount();
        final InvoiceVersion invoiceVersion = new InvoiceVersion();
        invoiceVersion.setVersionAmount(versionAmount);

        if (principalInvoice.getId() == null) {

            principalInvoice.setAmount(versionAmount);
            principalInvoice.setNumber(invoiceNumber);
            principalInvoice.setType(invoiceType);
            principalInvoice
                    .setOwner(coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER,
                            invVersDto.getTaxPayerNumber(), Partner.class).get());
            principalInvoice
                    .setSubType(coreService.findByUniqueKey(Constants.UK_CODE,
                            invVersDto.getSubTypeCode(), InvoiceType.class).get());

            invoiceVersion.setInvoice(principalInvoice);
            invoiceVersion.setNumber(1);
            invoiceVersion.setBalanceAmount(versionAmount);

            // sub invoices ???
            principalInvoice.setSubInvoices(CollectionUtils.isEmpty(invVersDto.getSubInvoices())
                    ? Arrays.asList(getSubInvoice(invVersDto, null, true, 1))
                    : invVersDto.getSubInvoices().stream()
                            .map(subInvDto -> getSubInvoice(invVersDto, subInvDto, true, 1))
                            .collect(Collectors.toList())
            );

            principalInvoice.setInvoiceVersions(Arrays.asList(invoiceVersion));
        } else {

            final int nextVersion = principalInvoice.getInvoiceVersions().size() + 1;

            invoiceVersion.setNumber(nextVersion);
            invoiceVersion.setInvoice(principalInvoice);

            final Properties ivtProps = CoreUtils.getParams(Optional.ofNullable(invoiceType.getParameters()));
            final boolean balance = null != ivtProps && Boolean.parseBoolean(ivtProps.getProperty("balance", "false"));

            if (balance) {

                principalInvoice.setAmount(principalInvoice.getAmount().add(versionAmount));
                invoiceVersion.setBalanceAmount(versionAmount);
            } else {

                final BigDecimal currInvAmount = principalInvoice.getAmount();

                invoiceVersion.setBalanceAmount(versionAmount.subtract(currInvAmount));
                principalInvoice.setAmount(versionAmount);
            }

            principalInvoice.setStatus(Invoice.INVOICE_UNPAID);
            principalInvoice.setLastVersionDate(LocalDateTime.now());
            principalInvoice.setLastVersionNumber(nextVersion);

            // sub invoices
            if (CollectionUtils.isEmpty(invVersDto.getSubInvoices())) {
                principalInvoice.setSubInvoices(Arrays.asList(getSubInvoice(invVersDto, null, balance, nextVersion)));
            } else {

                invVersDto.getSubInvoices().forEach(subInvDto -> {

                    final Invoice subInvoice = getSubInvoice(invVersDto, subInvDto, balance, nextVersion);
                    final int index = principalInvoice.getSubInvoices().indexOf(subInvoice);

                    if (index > -1) {
                        principalInvoice.getSubInvoices().set(index, subInvoice);
                    } else {
                        principalInvoice.getSubInvoices().add(subInvoice);
                    }
                });
            }

            coreService.save(invoiceVersion, InvoiceVersion.class);
        }

        coreService.save(principalInvoice, Invoice.class);
    }

    private Invoice getSubInvoice(InvoiceVersionDto parentInvVersDto, InvoiceVersionDto subInvVersDto,
            boolean balance, int versionNumber) {

        final String subInvBenefCode;
        if (subInvVersDto == null) {
            subInvBenefCode = parentInvVersDto.getBeneficiaryCode();
        } else {
            subInvBenefCode = subInvVersDto.getBeneficiaryCode();
        }
        final String parentInvTypeCode = parentInvVersDto.getInvoiceTypeCode();
        final String subInvNumber = getSubInvoiceNumber(parentInvVersDto.getInvoiceNumber(),
                parentInvTypeCode, subInvBenefCode);
        final InvoiceType subInvType = getSubInvoiceType(parentInvTypeCode, subInvBenefCode);
        final Invoice subInvoice = invoiceService.findByNumberAndType(subInvNumber, subInvType.getId())
                .orElse(new Invoice());

        final BigDecimal amount;
        final String benefReference;
        if (subInvVersDto == null) {

            amount = parentInvVersDto.getAmount();
            benefReference = parentInvVersDto.getBenefReference();
        } else {

            amount = subInvVersDto.getAmount();
            benefReference = subInvVersDto.getBenefReference();
        }

        final InvoiceVersion subInvVersion = new InvoiceVersion();
        subInvVersion.setVersionAmount(amount);

        if (subInvoice.getId() == null) {

            subInvoice.setBenefReference(benefReference);
            subInvoice.setNumber(subInvNumber);
            subInvoice.setType(subInvType);
            subInvoice.setBeneficiary(coreService.findByUniqueKey(Constants.UK_CODE, subInvBenefCode,
                    Partner.class).get());
            subInvoice.setOwner(coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER,
                    parentInvVersDto.getTaxPayerNumber(), Partner.class).get());
            subInvoice.setAmount(amount);
            //
            subInvVersion.setBalanceAmount(amount);
            subInvVersion.setInvoice(subInvoice);
            subInvVersion.setNumber(1);
            subInvVersion.setInvoice(subInvoice);
            //
            subInvoice.setInvoiceVersions(Arrays.asList(subInvVersion));
        } else {

            subInvVersion.setInvoice(subInvoice);

            if (balance) {

                subInvVersion.setBalanceAmount(amount);
                subInvoice.setAmount(subInvoice.getAmount().add(amount));
            } else {

                final BigDecimal currSubInvAmount = subInvoice.getAmount();

                subInvVersion.setBalanceAmount(amount.subtract(currSubInvAmount));
                subInvoice.setAmount(amount);
            }

            subInvoice.setStatus(Invoice.INVOICE_UNPAID);
            subInvoice.setLastVersionDate(LocalDateTime.now());
            subInvoice.setLastVersionNumber(versionNumber);

            coreService.save(subInvVersion, InvoiceVersion.class);
        }

        return subInvoice;
    }

    private InvoiceType getSubInvoiceType(String parentInvTypeCode, String subInvBenefCode) {

        final String invoiceTypeCode = String.format(RestConstants.SUB_INVOICE_TYPE_CODE_FORMAT,
                parentInvTypeCode, subInvBenefCode);
        final Optional<InvoiceType> invoiceTypeOp = coreService.findByUniqueKey(Constants.UK_CODE,
                invoiceTypeCode, InvoiceType.class);

        if (!invoiceTypeOp.isPresent()) {

            final Partner benef = coreService.findByUniqueKey(Constants.UK_CODE, subInvBenefCode, Partner.class).get();
            final InvoiceType invoiceType = new InvoiceType();

            invoiceType.setCode(invoiceTypeCode);
            invoiceType.setLabel(RestConstants.SUB_INVOICE_TYPE_NAME_PREFIX + benef.getName());

            return coreService.save(invoiceType, InvoiceType.class);
        } else {
            return invoiceTypeOp.get();
        }
    }

    private String getSubInvoiceNumber(String parentInvoiceNumber, String parentInvoiceTypeCode,
            String subInvoiceBenefCode) {
        return String.format(RestConstants.SUB_INVOICE_NUMBER_FORMAT,
                parentInvoiceNumber, parentInvoiceTypeCode, subInvoiceBenefCode);
    }

}
