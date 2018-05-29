package org.guce.epayment.core.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary;
import org.guce.epayment.core.repositories.InvoiceTypeBeneficiaryRepository;
import org.guce.epayment.core.repositories.InvoiceTypeRepository;
import org.guce.epayment.core.repositories.PaymentModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InvoiceTypeServiceImpl implements InvoiceTypeService {

    @Autowired
    private PaymentModeRepository paymentModeRepository;
    @Autowired
    private InvoiceTypeBeneficiaryRepository ivtbRepository;
    @Autowired
    private InvoiceTypeRepository invoiceTypeRepository;

    @Autowired
    private ApplicationService appService;

    @Override
    public List<InvoiceType> findByPaymentModes(final String pms) {

        final List<InvoiceType> invoiceTypes = new ArrayList<>();

        Arrays.asList(StringUtils.split(pms, appService.getColSep())).forEach(pm -> {

            invoiceTypes.addAll(paymentModeRepository.findByCode(pm).get().getInvoiceTypes());
        });

        return invoiceTypes.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void setDebitAccount(final InvoiceTypeBeneficiary ivtb) {

        ivtb.getAccount().setOwner(ivtb.getBeneficiary());

        ivtbRepository.save(ivtb);
    }

    @Override
    public List<InvoiceType> findByStandaloneAndSubType(boolean standalone, boolean subType) {
        return invoiceTypeRepository.findByStandaloneAndSubType(standalone, subType);
    }

    @Override
    public Optional<BankAccount> findBankAccount(String invoiceTypeCode, String beneficiaryCode) {
        return ivtbRepository.findBankAccount(invoiceTypeCode, beneficiaryCode);
    }

}
