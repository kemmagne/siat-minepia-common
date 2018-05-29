package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.guce.epayment.core.dao.CoreDao;
import org.guce.epayment.core.dao.PaymentDao;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.repositories.InvoiceRepository;
import org.guce.epayment.core.repositories.InvoiceVersionRepository;
import org.guce.epayment.core.repositories.PaymentModeRepository;
import org.guce.epayment.core.repositories.PaymentRepository;
import org.guce.epayment.core.utils.InvoiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.guce.epayment.core.utils.Constants;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceVersionRepository invoiceVersionRepository;
    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private CoreDao coreDao;
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public void updateInvoices(final Payment payment, final String status) {

        final List<PaymentInvoiceVersion> pivs = payment.getInvoicesVersions();

        pivs.stream().forEach(piv -> {

            final InvoiceVersion invoiceVersion = piv.getInvoiceVersion();
            final Invoice invoice = invoiceVersion.getInvoice();

            if (Invoice.INVOICE_PAID.equals(status)) {

                BigDecimal paidAmount = invoice.getPaidAmount();

                paidAmount = paidAmount.add(piv.getAmountForInvoice());
                invoice.setPaidAmount(paidAmount);
                invoiceVersion.setPaymentDate(LocalDateTime.now());
            }

            invoice.setStatus(status);
            invoiceRepository.save(invoice);
            invoiceVersionRepository.save(invoiceVersion);
        });

    }

    @Override
    public Payment init(final List<Map<String, Object>> invoices, final PaymentMode paymentMode, final Partner commiter,
            final String partnerReference, final Partner bankGateway, final int rate, final boolean rateOnInvoiceAmount) {

        final Payment payment = new Payment();

        payment.setInvoicesVersions(new ArrayList<>());
        payment.setAmount(BigDecimal.ZERO);

        invoices.forEach(invoiceInfos -> {

            final BigDecimal invoiceId = new BigDecimal(invoiceInfos.get(InvoiceConstants.INVOICE_ID).toString());
            final int invoiceVersionNumber = Integer.class.cast(invoiceInfos.get(InvoiceConstants.INVOICE_VERSION_NUMBER));
            final Invoice invoice = invoiceRepository.findById(invoiceId).get();
            final InvoiceVersion invoiceVersion = invoiceVersionRepository.findByInvoiceAndNumber(invoiceId, invoiceVersionNumber).get();

            if (!Invoice.INVOICE_PAYMENT_STARTED.equals(invoice.getStatus())) {

                registerInvoiceToPayment(payment, invoice, invoiceVersion, rate, rateOnInvoiceAmount);
                BigDecimal amountToPay = (BigDecimal) invoiceInfos.get(InvoiceConstants.INVOICE_AMOUNT_TO_PAY);
                payment.setAmount(payment.getAmount().add(amountToPay));

                invoice.getSubInvoices().stream().forEach(subInvoice -> {

                    final InvoiceVersion subInvoiceVersion = invoiceVersionRepository.findByInvoiceAndNumber(subInvoice.getId(), invoiceVersionNumber).get();
                    registerInvoiceToPayment(payment, subInvoice, subInvoiceVersion, rate, rateOnInvoiceAmount);
                });
            }
        });

        payment.setMode(paymentMode);
        payment.setCommiter(commiter);
        payment.setPartnerReference(partnerReference);
        payment.setStatus(Payment.PAYMENT_PENDING);
        payment.setBankGateway(bankGateway);

        return payment;
    }

    private BigDecimal registerInvoiceToPayment(final Payment payment, final Invoice invoice, final InvoiceVersion invoiceVersion, final int rate, final boolean rateOnInvoiceAmount) {

        BigDecimal amountToPay = invoiceVersion.getBalanceAmount();

        if (rateOnInvoiceAmount) {

            final BigDecimal invoiceAmount = invoice.getAmount();
            final BigDecimal amountClearable = invoiceAmount.multiply(new BigDecimal(rate)).divide(Constants.BIG_DECIMAL_100);
            if (amountToPay.compareTo(amountClearable) == Constants.ONE) {
                amountToPay = amountClearable;
            }
        }

        payment.getInvoicesVersions().add(new PaymentInvoiceVersion(payment, invoiceVersion, amountToPay, rate));

        invoice.setStatus(Invoice.INVOICE_PAYMENT_STARTED);
        invoiceRepository.save(invoice);

        return amountToPay;
    }

    @Override
    public void save(final Payment payment, final String seqTable, final String prefix) {

        final String reference = coreDao.generateUniqueValue(seqTable, prefix);

        payment.setReference(reference);

        paymentRepository.save(payment);
    }

    @Override
    public void update(final Payment payment) {

        paymentRepository.save(payment);
    }

    @Override
    public void setDecision(Payment payment, String status) {

        payment.setStatus(status);
        payment.setDecisionDate(LocalDateTime.now());
    }

    @Override
    public Signature buildUserSignature(final String originMessage, final String signature, final int level, final User user,
            final Step step, final Payment payment) {

        final Signature userSignature = new Signature();

        userSignature.setLevel(level);
        userSignature.setOriginMessage(originMessage);
        userSignature.setPayment(payment);
        userSignature.setSignature(signature);
        userSignature.setStep(step);
        userSignature.setUser(user);

        return userSignature;
    }

    @Override
    public List<PaymentInvoiceVersion> findPivsByBank(String bankCode, String pms, int max) {
        return paymentDao.findPivsByBank(bankCode, pms, max);
    }

    @Override
    public List<PaymentInvoiceVersion> findPivsByBenef(String benefCode, String pms, int max) {
        return paymentDao.findPivsByBenef(benefCode, pms, max);
    }

    @Override
    public List<PaymentInvoiceVersion> findPivsByDecisionMaker(final String decisionMakerLogin, final String pms,
            final int max) {
        return paymentDao.findPivsByDecisionMaker(decisionMakerLogin, pms, max);
    }

    @Override
    public List<PaymentMode> findPaymentModesByDirect(final boolean direct) {
        return paymentModeRepository.findByDirect(direct);
    }

}
