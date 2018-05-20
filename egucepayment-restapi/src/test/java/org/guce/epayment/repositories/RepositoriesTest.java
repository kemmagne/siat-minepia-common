package org.guce.epayment.repositories;

import java.math.BigDecimal;
import org.guce.epayment.core.repositories.ParamsRepository;
import org.guce.epayment.core.repositories.UserRepository;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.repositories.InvoiceRepository;
import org.guce.epayment.core.repositories.InvoiceVersionRepository;
import org.guce.epayment.core.repositories.PartnerRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tadzotsa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestConfig.xml")
public class RepositoriesTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParamsRepository paramsRepository;
    @Autowired
    private InvoiceVersionRepository invoiceVersionRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    @Ignore
    @Test
    public void testParams() {

        Assert.assertNotNull(paramsRepository);
        Assert.assertFalse(paramsRepository.findAll().isEmpty());
    }

    @Ignore
    @Test
    public void testUsers() {
        Assert.assertNotNull(userRepository);
    }

    @Ignore
    @Test
    public void testInvoices() {

        final Partner partner = new Partner();

        partner.setCode("code");
        partner.setName("name");

        partnerRepository.save(partner);

        final Invoice invoice = new Invoice();

        invoice.setNumber("number");
        invoice.setAmount(BigDecimal.ONE);
        invoice.setOwner(partner);
        invoiceRepository.save(invoice);

        Assert.assertFalse(invoiceVersionRepository.findByInvoiceAndNumber(invoice.getId(), 1).isPresent());
    }

}
