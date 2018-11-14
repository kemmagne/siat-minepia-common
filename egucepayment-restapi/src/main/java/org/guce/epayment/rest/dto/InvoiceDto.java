package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class InvoiceDto extends Invoice {

    private static final long serialVersionUID = -3742872244607036201L;

    @Override
    public List<InvoiceVersion> getInvoiceVersions() {
        return super.getInvoiceVersions().stream().map(iv -> {
            final InvoiceVersionDto ivDto = RestUtils.downCast(InvoiceVersion.class, InvoiceVersionDto.class, iv);
            return ivDto;
        }).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getAmount() {
        return super.getAmount();
    }

    @Override
    public BigDecimal getAmountToPay() {
        return super.getAmount().subtract(super.getPaidAmount());
    }

    @Override
    public LocalDateTime getLastVersionDate() {
        return super.getLastVersionDate();
    }

    @Override
    public int getLastVersionNumber() {
        return super.getLastVersionNumber();
    }

    @Override
    public PartnerDto getOwner() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getOwner());
    }

    @Override
    public BigDecimal getPaidAmount() {
        return super.getPaidAmount();
    }

    @Override
    public InvoiceTypeDto getSubType() {
        return RestUtils.downCast(InvoiceType.class, InvoiceTypeDto.class, super.getSubType());
    }

    @Override
    public InvoiceTypeDto getType() {
        return RestUtils.downCast(InvoiceType.class, InvoiceTypeDto.class, super.getType());
    }

    public List<? extends InvoiceLine> getInvoiceLines() {
        final List<InvoiceVersion> invoiceVersions = getInvoiceVersions();
        final List<InvoiceLine> invoiceLines = invoiceVersions.get(0).getInvoiceLines();
        final int nbInvoiceVersions = invoiceVersions.size();
        if (nbInvoiceVersions > 1) {
            for (int i = 1; i < nbInvoiceVersions; i++) {
                for (final InvoiceLine il : invoiceVersions.get(i).getInvoiceLines()) {
                    final int index = invoiceLines.indexOf(il);
                    il.setPaidAmount(il.getInvoiceVersion().getPaymentDate() == null ? BigDecimal.ZERO : super.getAmountToPay());
                    if (-1 < index) { // il in list
                        final InvoiceLine il2 = invoiceLines.get(index);
                        il2.setAmountToPay(il2.getAmountToPay().add(il.getAmountToPay()));
                        il2.setPaidAmount(il.getPaidAmount()
                                .add(il2.getInvoiceVersion().getPaymentDate() == null ? BigDecimal.ZERO : super.getAmountToPay()));
                        invoiceLines.set(index, il2);
                    } else { // il not in list
                        invoiceLines.add(il);
                    }
                }
            }
        }

        return invoiceLines.stream().map(il -> {
            final InvoiceLineDto ilDto = RestUtils.downCast(InvoiceLine.class, InvoiceLineDto.class, il);
            return ilDto;
        }).collect(Collectors.toList());
    }

}
