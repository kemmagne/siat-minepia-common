package org.guce.siat.common.dao;

import junit.framework.TestCase;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Step;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author ht
 */
//@Transactional
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class FileTypeStepDaoTest extends TestCase {

    @Mock
    private TestDataBuilder builder;

    @Ignore
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
