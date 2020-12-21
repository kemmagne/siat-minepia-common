package org.guce.siat.common.dao;

import junit.framework.TestCase;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Step;
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
public class FileTypeStepDaoTest extends TestCase {

    @Autowired
    private TestDataBuilder builder;

    @Test
    public void test_findFileTypeStepByFileTypeAndStep() {
        Step step = builder.getStep();
        FileType fileType = builder.getFileType();
        FileTypeStep fts = new FileTypeStep();
        fts.setFileType(fileType);
        fts.setStep(step);
        builder.getFileTypeStepDao().save(fts);
        FileTypeStep fts2 = builder.getFileTypeStepDao().findFileTypeStepByFileTypeAndStep(fileType, step);
        Assert.assertNotNull(fts2);
        Assert.assertEquals(fileType, fts2.getFileType());
        Assert.assertEquals(step, fts2.getStep());
    }

}
