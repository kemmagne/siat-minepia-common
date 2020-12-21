package org.guce.siat.common.dao;

import java.util.List;
import junit.framework.TestCase;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Transfer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class TransferDaoTest extends TestCase {

    @Autowired
    private TestDataBuilder builder;

    /**
     * Test of findByFile method, of class TransferDao.
     */
    @Test
    public void testFindByFile() {
        Transfer transfer = builder.getTransfer();

        List<Transfer> transfers = builder.getTransferDao().findByFile(transfer.getFile());

        Assert.assertFalse(transfers.isEmpty());
        Assert.assertEquals(transfer.getId(), transfers.get(0).getId());
    }

    /**
     * Test of findLastByNumeroDemandeAndBureau method, of class TransferDao.
     */
    @Test
    public void testFindLastByNumeroDemandeAndBureau() {
        Transfer transfer = builder.getTransfer();
        transfer.getAssignedUser().setAdministration(builder.getBureau());
        builder.getUserDao().update(transfer.getAssignedUser());

        Transfer found = builder.getTransferDao().findLastByNumeroDemandeAndBureau(transfer.getNumeroDemande(), (Bureau) transfer.getAssignedUser().getAdministration());
        Assert.assertNotNull(found);
        Assert.assertEquals(transfer.getId(), found.getId());
    }

}
