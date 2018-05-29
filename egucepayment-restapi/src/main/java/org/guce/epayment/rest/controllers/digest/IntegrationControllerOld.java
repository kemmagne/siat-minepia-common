package org.guce.epayment.rest.controllers.digest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("digest/integration/old")
public class IntegrationControllerOld {

    @Autowired
    private CoreService coreService;
    @Autowired
    private InvoiceService invoiceService;

    public void integrate(InvoiceVersionDto invoiceVersionDto) {

    }

    @ResponseBody
    @RequestMapping(path = "digest/integration", method = RequestMethod.POST)
    public void integrateInvoice(@RequestBody InvoiceVersionDto invoiceVersionDto) {

        final String invoiceNumber = invoiceVersionDto.getInvoiceNumber().toUpperCase();
        final String invoiceTypeCode = invoiceVersionDto.getInvoiceTypeCode().toUpperCase();

        final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, invoiceTypeCode,
                InvoiceType.class).get();
        final Invoice principalInvoice = invoiceService.findByNumberAndType(invoiceNumber, invoiceType.getId())
                .orElse(new Invoice());
        final BigDecimal versionAmount = invoiceVersionDto.getAmount();
        if (1 == BigDecimal.ZERO.compareTo(versionAmount)) {
            //return Response.ok(Static.getDefaultDto("NEGATIVE.AMOUNT")).build();
        }

        // s'il n'y a pas de sous factures, on met une liste vide
        if (CollectionUtils.isEmpty(invoiceVersionDto.getSubInvoices())) {
            invoiceVersionDto.setSubInvoices(Collections.emptyList());
        }

        final InvoiceVersion invoiceVersion = new InvoiceVersion();
        invoiceVersion.setVersionAmount(versionAmount);
        if (principalInvoice.getId() == null) {

            if (StringUtils.isNotBlank(invoiceVersionDto.getBeneficiaryCode())) {
                principalInvoice
                        .setBeneficiary(coreService.findByUniqueKey(Constants.UK_CODE, invoiceVersionDto.getBeneficiaryCode(),
                                Partner.class).get());
            }

            principalInvoice.setAmount(versionAmount);
            principalInvoice.setNumber(invoiceNumber);
            principalInvoice.setType(invoiceType);

            principalInvoice
                    .setOwner(coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER,
                            invoiceVersionDto.getTaxPayerNumber(), Partner.class).get());

            if (StringUtils.isNotBlank(invoiceVersionDto.getSubTypeCode())) {
                principalInvoice
                        .setSubType(coreService.findByUniqueKey(Constants.UK_CODE,
                                invoiceVersionDto.getSubTypeCode(), InvoiceType.class).get());
            }

            principalInvoice.setSubInvoices(getSubInvoices(invoiceVersionDto, principalInvoice));

            invoiceVersion.setInvoice(principalInvoice);
            invoiceVersion.setNumber(1);
            invoiceVersion.setBalanceAmount(versionAmount);

            principalInvoice.setInvoiceVersions(Arrays.asList(invoiceVersion));
        } else {

            invoiceVersion.setInvoice(principalInvoice);

            final int nextVersionNumber = principalInvoice.getInvoiceVersions().size() + 1;
            invoiceVersion.setNumber(nextVersionNumber);

            final Properties ivtProps = CoreUtils.getParams(Optional.ofNullable(invoiceType.getParameters()));
            final boolean balance = null != ivtProps && Boolean.parseBoolean(ivtProps.getProperty("balance", "false"));
            if (balance) {

                principalInvoice.setAmount(principalInvoice.getAmount().add(versionAmount));
                invoiceVersion.setBalanceAmount(versionAmount);
            } else {

                final BigDecimal invoiceAmount = principalInvoice.getAmount();
                if (1 == invoiceAmount.compareTo(versionAmount)) {
//                    return Response.ok(Static.getDefaultDto("INCOHERENT.AMOUNT")).build();
                }

                invoiceVersion.setBalanceAmount(versionAmount.subtract(invoiceAmount));
                principalInvoice.setAmount(versionAmount);
            }

            principalInvoice.setStatus(Invoice.INVOICE_UNPAID);
            principalInvoice.setLastVersionDate(LocalDateTime.now());
            principalInvoice.setLastVersionNumber(nextVersionNumber);

            // on met à jour les sous factures et leurs version
            invoiceVersionDto.getSubInvoices().forEach(subIvDto -> {

                final String subIvNumber = getSubInvoiceNumber(invoiceNumber, invoiceTypeCode,
                        subIvDto.getBeneficiaryCode());
                final InvoiceType subIvType = getSubInvoiceType(invoiceTypeCode, subIvDto.getBeneficiaryCode());
                final Invoice subIv = invoiceService.findByNumberAndType(subIvNumber, subIvType.getId())
                        .orElse(new Invoice());

                if (subIv.getId() != null) {

                } else {

                }

                final InvoiceVersion subIvVersion = new InvoiceVersion();
                final BigDecimal subIvVersionAmount = subIvDto.getAmount();

                subIvVersion.setVersionAmount(subIvVersionAmount);
                subIvVersion.setNumber(nextVersionNumber);
                subIvVersion.setInvoice(subIv);

                if (subIv.getId() == null) {
                    subIv.setInvoiceVersions(Arrays.asList(subIvVersion));
                }
            });

            coreService.save(invoiceVersion, InvoiceVersion.class);
        }

        coreService.save(principalInvoice, Invoice.class);
    }

    private List<Invoice> getSubInvoices(InvoiceVersionDto parentInvoiceVersionDto, Invoice parentInvoice) {

        final List<Invoice> subInvoices = Collections.emptyList();
        final List<InvoiceVersionDto> subInvoiceVersionDtos = parentInvoiceVersionDto.getSubInvoices();

        subInvoiceVersionDtos.forEach(subInvoiceVersionDto -> {

            final Invoice subInvoice = new Invoice();
            final String subInvoiceBenefCode = subInvoiceVersionDto.getBeneficiaryCode();
            final String parentInvoiceNumber = parentInvoiceVersionDto.getInvoiceNumber();
            final String parentInvoiceTypeCode = parentInvoiceVersionDto.getInvoiceTypeCode();
            final BigDecimal amount = subInvoiceVersionDto.getAmount();

            subInvoice.setNumber(getSubInvoiceNumber(parentInvoiceNumber, parentInvoiceTypeCode, subInvoiceBenefCode));
            subInvoice.setType(getSubInvoiceType(parentInvoiceTypeCode, subInvoiceBenefCode));
            subInvoice.setBeneficiary(coreService.findByUniqueKey(Constants.UK_CODE, subInvoiceBenefCode,
                    Partner.class).get());
            subInvoice.setOwner(coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER,
                    parentInvoiceVersionDto.getTaxPayerNumber(), Partner.class).get());
            subInvoice.setAmount(amount);
            subInvoice.setParent(parentInvoice);

            final InvoiceVersion subInvoiceVersion = new InvoiceVersion();

            subInvoiceVersion.setBalanceAmount(amount);
            subInvoiceVersion.setInvoice(subInvoice);
            subInvoiceVersion.setNumber(1);
            subInvoiceVersion.setVersionAmount(amount);

            subInvoice.setInvoiceVersions(Arrays.asList(subInvoiceVersion));

            subInvoices.add(subInvoice);
        });

        return subInvoices;
    }

    private InvoiceType getSubInvoiceType(String parentInvoiceTypeCode, String subIvBenefCode) {

        final String invoiceTypeCode = String.format(RestConstants.SUB_INVOICE_TYPE_CODE_FORMAT,
                parentInvoiceTypeCode, subIvBenefCode);
        final Optional<InvoiceType> invoiceTypeOp = coreService.findByUniqueKey(Constants.UK_CODE,
                invoiceTypeCode, InvoiceType.class);

        if (!invoiceTypeOp.isPresent()) {

            final Partner benef = coreService.findByUniqueKey(Constants.UK_CODE, subIvBenefCode, Partner.class).get();
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
