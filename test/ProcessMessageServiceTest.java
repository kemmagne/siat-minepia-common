package org.guce.siat.common.service;

import hk.hku.cecid.piazza.commons.util.Generator;
import java.io.File;
import javax.xml.bind.JAXBException;
import junit.framework.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.guce.orchestra.util.JAXBUtil;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.doc.DOCUMENT;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.service.impl.ProcessMessageServiceImpl;
import org.guce.siat.common.utils.ParamsConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class ProcessMessageServiceTest extends TestCase {

    @Autowired
    private ParamsDao paramsDao;
    @Autowired
    private PropertiesLoader propertiesLoader;

    private Params params;

    @Before
    @Override
    public void setUp() throws Exception {
        params = new Params();
        params.setName(ParamsConstants.BACKUP_RECEVED_MESSAGES.getParamsName());
        params.setParamsCategory(ParamsCategory.GN);
    }

    @Ignore
    @Test
    public void test_process_backup_with_params_null() throws JAXBException {
        DOCUMENT document = getDocument();
        byte[] ebxmlData = JAXBUtil.marshall(document, true);
        String backupFilePath = ProcessMessageServiceImpl.backup(propertiesLoader, paramsDao, ebxmlData, document);
        Assert.assertNull(backupFilePath);
    }

    @Ignore
    @Test
    public void test_process_backup_with_params_true() throws JAXBException {
        params.setValue(Boolean.TRUE.toString());
        paramsDao.save(params);
        DOCUMENT document = getDocument();
        byte[] ebxmlData = JAXBUtil.marshall(document, true);
        String backupFilePath = ProcessMessageServiceImpl.backup(propertiesLoader, paramsDao, ebxmlData, document);
        File backupFile = new File(backupFilePath);
        Assert.assertTrue(backupFile.exists());
        backupFile.delete();
        Assert.assertFalse(backupFile.exists());
    }

    @Ignore
    @Test
    public void test_process_backup_with_params_false() throws JAXBException {
        params.setValue(Boolean.FALSE.toString());
        paramsDao.save(params);
        DOCUMENT document = getDocument();
        byte[] ebxmlData = JAXBUtil.marshall(document, true);
        String backupFilePath = ProcessMessageServiceImpl.backup(propertiesLoader, paramsDao, ebxmlData, document);
        Assert.assertNull(backupFilePath);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        paramsDao.delete(params);
    }

    private DOCUMENT getDocument() {
        DOCUMENT document = new DOCUMENT();
        document.setTYPEDOCUMENT(RandomStringUtils.randomAlphanumeric(5));
        document.setMESSAGE(new DOCUMENT.MESSAGE());
        document.getMESSAGE().setNUMEROMESSAGE(Generator.generateMessageID());
        document.setREFERENCEDOSSIER(new DOCUMENT.REFERENCEDOSSIER());
        document.getREFERENCEDOSSIER().setNUMERODOSSIER(RandomStringUtils.randomAlphabetic(9));
        document.setROUTAGE(new DOCUMENT.ROUTAGE());
        document.getROUTAGE().setEMETTEUR(RandomStringUtils.randomAlphabetic(35));
        return document;
    }

}
