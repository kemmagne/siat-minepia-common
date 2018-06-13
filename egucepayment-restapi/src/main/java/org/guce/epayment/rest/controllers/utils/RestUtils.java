package org.guce.epayment.rest.controllers.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.utils.InvoiceConstants;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.rest.dto.BankAccountDto;
import org.guce.epayment.rest.dto.IncomingMessageDto;
import org.guce.epayment.rest.dto.InvoiceDto;
import org.guce.epayment.rest.dto.InvoiceTypeDto;
import org.guce.epayment.rest.dto.PartnerDto;
import org.guce.epayment.rest.dto.PartnerGroupDto;
import org.guce.epayment.rest.dto.PartnerTypeDto;
import org.guce.epayment.rest.dto.PaymentModeDto;
import org.guce.epayment.rest.dto.RoleDto;
import org.guce.epayment.rest.dto.TransferOrderDto;
import org.guce.epayment.rest.dto.UserDto;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.util.CipherUtils;
import org.guce.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author tadzotsa
 */
public interface RestUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(RestUtils.class);

    static PartnerDto getPartnerDto(Partner partner, boolean list) {

        if (null == partner) {
            return null;
        }

        final PartnerDto partnerDto = new PartnerDto();

        partnerDto.setId(partner.getId());

        final String code = partner.getCode();
        final Partner parent = partner.getParent();

        if (list && null != parent) {

            partnerDto.setParent(PartnerDto.of(parent.getId(), parent.getCode(), parent.getName()));

            final String[] codeParts = StringUtils.split(code, Constants.GLOBAL_SEPERATOR);

            if (Constants.TWO == codeParts.length) {
                partnerDto.setCode(codeParts[1]);
            } else {
                partnerDto.setCode(code);
            }
        } else {
            partnerDto.setCode(code);
        }

        partnerDto.setName(partner.getName());
        partnerDto.setTaxPayerNumber(partner.getTaxPayerNumber());

        if (list) {

            partnerDto.setTypes(partner.getTypes().stream().map(pt -> PartnerTypeDto.of(pt.getId(), pt.getCode(), pt.getLabel())).collect(Collectors.toList()));
            partnerDto.setGroups(partner.getGroups().stream().map(pg -> PartnerGroupDto.of(pg.getCode(), pg.getName())).collect(Collectors.toList()));
        }

        return partnerDto;
    }

    static UserDto getUserDto(User user, Optional<String> token) {

        if (user == null) {
            return null;
        }

        final UserDto userDto = new UserDto();

        userDto.setActive(user.isActive());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setId(user.getId());
        userDto.setLastName(user.getLastName());
        userDto.setLocale(user.getLocale());
        userDto.setLocked(user.isLocked());
        userDto.setLogin(user.getLogin());
        userDto.setPartner(PartnerDto.of(user.getPartner().getId(), user.getPartner().getCode(), user.getPartner().getName()));
        userDto.setResetPassword(user.isResetPassword());
        userDto.setRoleDtos(user.getRoles().stream().map(role -> RoleDto.of(role.getName(), role.getDescription())).collect(Collectors.toList()));
        userDto.setRoles(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
        userDto.setTitle(user.getTitle());
        userDto.setToken(token.orElse(null));

        return userDto;
    }

    static InvoiceTypeDto getInvoiceTypeDto(InvoiceType invoiceType, boolean list) {

        if (null == invoiceType) {
            return null;
        }

        final InvoiceTypeDto invoiceTypeDto = new InvoiceTypeDto();

        invoiceTypeDto.setId(invoiceType.getId());
        invoiceTypeDto.setCode(invoiceType.getCode());
        invoiceTypeDto.setLabel(invoiceType.getLabel());

        if (list) {

            invoiceTypeDto.setParams(invoiceType.getParameters());
            invoiceTypeDto.setStandalone(invoiceType.isStandalone());

            invoiceTypeDto.setPaymentModes(invoiceType.getPaymentModes().stream().map(pm -> PaymentModeDto.of(pm.getCode(), pm.getLabel())).collect(Collectors.toList()));
            invoiceTypeDto.setBeneficiaries(invoiceType.getBeneficiaries().stream().map(ivtb -> getPartnerDto(ivtb.getBeneficiary(), false)).collect(Collectors.toList()));
            invoiceTypeDto.setBanks(invoiceType.getUnauthorizedBanks().stream().map(bank -> getPartnerDto(bank, false)).collect(Collectors.toList()));
            invoiceTypeDto.setDecisionMakers(invoiceType.getDecisionMakers().stream().map(user -> getUserDto(user, Optional.empty())).collect(Collectors.toList()));
        }

        return invoiceTypeDto;
    }

    static InvoiceDto getInvoiceDto(Invoice invoice) {

        final InvoiceDto invoiceDto = new InvoiceDto();

        // bénéf
        invoiceDto.setBeneficiary(getPartnerDto(invoice.getBeneficiary(), false));
        // id
        invoiceDto.setId(invoice.getId());
        // n°
        invoiceDto.setInvoiceNumber(invoice.getNumber());
        // status
        invoiceDto.setStatus(invoice.getStatus());
        // type
        invoiceDto.setInvoiceType(getInvoiceTypeDto(invoice.getType(), false));
        // sub type
        invoiceDto.setSubType(getInvoiceTypeDto(invoice.getSubType(), false));
        // montants
        final BigDecimal amount = invoice.getAmount();
        invoiceDto.setTotalAmount(amount);
        final BigDecimal paidAmount = invoice.getPaidAmount();
        invoiceDto.setPaidAmount(paidAmount);
        final BigDecimal amountToPay = amount.subtract(paidAmount);
        invoiceDto.setAmountToPay(amountToPay);
        // version date
        invoiceDto.setVersionDate(invoice.getLastVersionDate().toString());
        // version number
        invoiceDto.setVersionNumber(invoice.getLastVersionNumber());
        // owner
        invoiceDto.setOwner(getPartnerDto(invoice.getOwner(), false));
        // mended
        invoiceDto.setMended(1 < invoice.getInvoiceVersions().size());
        // sub invoices
        final List<Invoice> subInvoices = invoice.getSubInvoices();
        if (!subInvoices.isEmpty()) {
            invoiceDto.setSubInvoices(subInvoices.stream().map(subInvoice -> getInvoiceDto(subInvoice)).collect(Collectors.toList()));
        } else {
            invoiceDto.setSubInvoices(new ArrayList<>());
        }

        return invoiceDto;
    }

    static BankAccountDto getBankAccountDto(BankAccount account) {

        if (null == account) {
            return null;
        }

        final BankAccountDto accountDto = new BankAccountDto();

        // id
        accountDto.setId(account.getId());
        // common infos
        accountDto.setAccountKey(account.getKey());
        accountDto.setAccountLabel(account.getLabel());
        accountDto.setAccountNumber(account.getNumber());
        // bank
        Partner bank = account.getBank();
        accountDto.setBankCode(bank.getCode());
        accountDto.setBankLabel(bank.getName());
        // agency
        Partner agency = account.getAgency();
        accountDto.setAgencyCode(StringUtils.split(agency.getCode(), Constants.GLOBAL_SEPERATOR)[1]);
        accountDto.setAgencyLabel(agency.getName());

        return accountDto;
    }

    static TransferOrderDto getTransferOrderDto(TransferOrder transferOrder, String locale) {

        final TransferOrderDto transferOrderDto = new TransferOrderDto();
        final NumberFormat nf = NumberFormat.getNumberInstance(Locale.forLanguageTag(locale));

        transferOrderDto.setReference(transferOrder.getReference());
        transferOrderDto.setPartnerReference(transferOrder.getPartnerReference());
        transferOrderDto.setAmount(nf.format(transferOrder.getAmount()));
        transferOrder.setStatus(transferOrder.getStatus());
        //
        transferOrderDto.setCommiter(getPartnerDto(transferOrder.getCommiter(), false));
        //
        transferOrderDto.setStartedDate(transferOrder.getStartedDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)));
        transferOrderDto.setDecisionDate(transferOrder.getValidationDate() != null ? transferOrder.getValidationDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)) : null);
        transferOrderDto.setSittingDate(transferOrder.getSittingDate() != null ? transferOrder.getSittingDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)) : null);
        //
        transferOrderDto.setTaxPayer(getPartnerDto(transferOrder.getTaxPayer(), false));
        //
        transferOrderDto.setBeneficiary(getPartnerDto(transferOrder.getBeneficiary(), false));
        transferOrderDto.setInvoices(transferOrder.getInvoicesVersions().stream().map(piv -> getInvoiceDto(piv.getInvoiceVersion().getInvoice())).collect(Collectors.toList()));
        transferOrderDto.setInvoiceType(Optional.of(transferOrder.getInvoiceType()).map(ivt -> getInvoiceTypeDto(ivt, false)).get());

        return transferOrderDto;
    }

    static List<Map<String, Object>> getInvoicesMap(List<InvoiceDto> invoiceDtos) {

        return invoiceDtos.stream().map(invoiceDto -> {

            final Map<String, Object> invoiceInfo = new HashMap<>();

            invoiceInfo.put(InvoiceConstants.INVOICE_ID, invoiceDto.getId());
            invoiceInfo.put(InvoiceConstants.INVOICE_VERSION_NUMBER, invoiceDto.getVersionNumber());
            invoiceInfo.put(InvoiceConstants.INVOICE_AMOUNT_TO_PAY, invoiceDto.getAmountToPay());

            return invoiceInfo;
        }).collect(Collectors.toList());
    }

    static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if (StringUtils.isBlank(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    static ResourceBundle getResourceBundle(String locale) {

        final String BUNDLE_PATH = "locale/locale";

        return ResourceBundle.getBundle(BUNDLE_PATH, new Locale(locale.toUpperCase()));
    }

    static byte[] getOriginalMessage(IncomingMessageDto messageDto, String senderPrefix) {

        try {

            final Properties props = new Properties();

            props.load(new ClassPathResource("global-config.properties").getInputStream());

            final String privateKey = FileUtil
                    .getFileContent(new ClassPathResource(props.getProperty("private.key.file")).getFile());
            final String secretKey = CipherUtils.rsaDecrypt(privateKey, messageDto.getCipheredSecretKey());
            final byte[] bytes = CipherUtils.aesDecrypt(messageDto.getCipheredOriginMessage(), secretKey);
            final String originalSignature = messageDto.getSignature();
            final String epaymentPublicKey = FileUtil
                    .getFileContent(new ClassPathResource(props.getProperty(senderPrefix + ".public.key.file"))
                            .getFile());
            if (CipherUtils.verify(bytes, originalSignature, epaymentPublicKey)) {
                return bytes;
            } else {
                return new byte[0];
            }
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }

}
